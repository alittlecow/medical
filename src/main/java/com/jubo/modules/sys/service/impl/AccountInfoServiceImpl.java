package com.jubo.modules.sys.service.impl;

import cn.beecloud.BCPay;
import cn.beecloud.bean.BCAuth;
import cn.beecloud.bean.BCException;
import com.jubo.common.exception.RRException;
import com.jubo.common.utils.Constant;
import com.jubo.common.utils.ErrorMessage;
import com.jubo.common.utils.R;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.modules.sys.dao.AccountInfoDao;
import com.jubo.modules.sys.dao.SysUserDao;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.AccountTransactionHistoryEntity;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import com.jubo.modules.sys.service.AccountTransactionHistoryService;
import com.jubo.modules.sys.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private AccountTransactionHistoryService historyService;


    @Override
    @Transactional
    public R pay(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return R.error(ErrorMessage.ORDER_NOT_BLANK);
        }

        OrderEntity order = orderService.queryObject(orderId);
        if (order == null) {
            return R.error(ErrorMessage.ORDER_NOT_EXIST);
        }

        //订单已支付
        if (Constant.PayStatus.SUCCESS.getValue().compareTo(order.getPayStatus()) == 0) {
            return R.error(ErrorMessage.ORDER_ALREADY_PAY);
        }

        //检查余额
        AccountInfoEntity account = accountInfoDao.queryObjectByUserId(order.getUserId());
        double orderMoney = order.getOrderMoney().doubleValue();
        double oldBalance = account.getBalance().doubleValue();
        double newBalance = oldBalance - orderMoney;
        if (newBalance < 0) {
            return R.error(ErrorMessage.MONEY_NOT_ENOUGH);
        }


        //账户更新 生成流水
        accountMoneyChangeRec(orderId, orderMoney, account, oldBalance, newBalance,
                Constant.AccountAdjustType.USER_CONSUME.getValue());
        // TODO: 2017/7/23 支付成功回调业务
        return R.ok();
    }

    // 通用方法 记录 账户资金变动
    private void accountMoneyChangeRec(String orderId, double adjustMoney, AccountInfoEntity account, double oldBalance, double newBalance, Byte adjustType) {
        //记录本账户余额变动
        updateSelfAccount(account, newBalance);
        //记录本账户历史
        accountTransactionHistoryRec(orderId, adjustMoney, account, oldBalance, newBalance, adjustType);

    }




    private void accountTransactionHistoryRec(String orderId, Double adjustMoney, AccountInfoEntity account, Double oldBalance, Double newBalance, Byte adjustType) {
        AccountTransactionHistoryEntity history = new AccountTransactionHistoryEntity();
        history.setId(UUIDUtil.getUUId());
        history.setAccountId(account.getId());
        history.setAdjustMoney(new BigDecimal(-adjustMoney));
        history.setCreateTime(new Date());
        history.setAdjustType(adjustType);
        history.setBeforeAdjustMoney(new BigDecimal(oldBalance));
        history.setAfterAdjustMoney(new BigDecimal(newBalance));
        history.setOrderId(orderId);
        historyService.save(history);
    }

    private void updateSelfAccount(AccountInfoEntity account, double newBalance) {
        account.setBalance(new BigDecimal(newBalance));
        account.setUpdateTime(new Date());
        accountInfoDao.update(account);
    }


    @Override
    public AccountInfoEntity queryObjectByUserId(Long userId) {
        return accountInfoDao.queryObjectByUserId(userId);
    }

    @Override
    public AccountInfoEntity queryObject(String id) {
        return accountInfoDao.queryObject(id);
    }

    @Override
    public List<AccountInfoEntity> queryList(Map<String, Object> map) {
        return accountInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return accountInfoDao.queryTotal(map);
    }

    @Override
    public void save(AccountInfoEntity accountInfo) {
        accountInfoDao.save(accountInfo);
    }

    @Override
    public void update(AccountInfoEntity accountInfo) {
        accountInfoDao.update(accountInfo);
    }

    @Override
    public void delete(String id) {
        accountInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        accountInfoDao.deleteBatch(ids);
    }

    @Override
    public void auth(Map<String, String> params, SysUserEntity userEntity) {
        if(new Byte("1").equals(userEntity.getIsAuth())){
            throw new RRException("帐号已通过实名认证");
        }
        String name = params.get("name");
        String idNo = params.get("idNo");
        String cardNo = params.get("cardNo");
        BCAuth auth = new BCAuth(name, idNo, cardNo);
        String mobile = params.get("mobile");
        auth.setMobile(mobile);
        try {
            auth = BCPay.startBCAuth(auth);
            if(auth.isAuthResult()){
                userEntity.setRealName(name);
                userEntity.setIdCard(idNo);
                userEntity.setIsAuth(new Byte("1"));
                sysUserDao.update(userEntity);
            }else{
                throw new RRException("认证信息不匹配",500);
            }
        } catch (BCException e) {
           throw new RRException("实名认证接口调用失败",e);
        }
    }

}
