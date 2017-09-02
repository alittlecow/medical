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
import com.jubo.modules.sys.entity.*;
import com.jubo.modules.sys.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private AccountTransactionHistoryService historyService;

    @Autowired
    private SettlementRuleService settlementRuleService;

    @Autowired
    private OrderCallBackService orderCallBackService;

    private static final String MERCHANT = "merchant";
    private static final String PROVINCE_DEALER = "provinceDealer";
    private static final String CITY_DEALER = "cityDealer";

    @Override
    public R pay(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return R.error(ErrorMessage.ORDER_NOT_BLANK);
        }

        OrderEntity order = orderService.queryObject(orderId);
        if (order == null) {
            return R.error(ErrorMessage.ORDER_NOT_EXIST);
        }

        //订单已支付
        if (Constant.PayStatus.SUCCESS.getValue().equals(order.getPayStatus())) {
            return R.error(ErrorMessage.ORDER_ALREADY_PAY);
        }

        //获取调整类型
        Byte adjustType = getAdjustType(order.getOrderType());

        //支付
        changeAccount(order.getUserId(), adjustType, order.getOrderMoney().doubleValue(), orderId);

        //支付成功回调
        orderCallBackService.handleSettlementOrder(orderId, Constant.PayType.ACCOUNT.getValue());


        return R.ok();
    }

    @Override
    public void settlement(Long merchantId, double money, String orderId) {
        if (merchantId == null) return;

        Map<String, Long> userMap = getAllDealerAccount(merchantId);

        SettlementRuleEntity rule = settlementRuleService.queryObject(new Long("0"));

        double city = rule.getCityDealer() != null ? rule.getCityDealer().doubleValue() / 100 : 0;
        double province = rule.getProvinceDealer() != null ? rule.getProvinceDealer().doubleValue() / 100 : 0;
        double merchant = rule.getMerchant() != null ? rule.getMerchant().doubleValue() / 100 : 0;
        //商户账户
        changeAccount(userMap.get(MERCHANT), Constant.AccountAdjustType.DEALER_SETTLEMENT.getValue(),
                -money * merchant,
                orderId);

        //市级分销商
        changeAccount(userMap.get(CITY_DEALER), Constant.AccountAdjustType.DEALER_SETTLEMENT.getValue(),
                -money * city,
                orderId);
        //省级分销商
        changeAccount(userMap.get(PROVINCE_DEALER), Constant.AccountAdjustType.DEALER_SETTLEMENT.getValue(),
                -money * province,
                orderId);
    }

    /**
     * 根据商户ID获取分成账户
     *
     * @param merchantId
     * @return
     */
    private Map<String, Long> getAllDealerAccount(Long merchantId) {
        Map<String, Long> dealerAccountMap = new HashMap<>();

        //商户
        SysDeptEntity dealer = sysDeptService.queryObject(merchantId);
        dealerAccountMap.put(MERCHANT, dealer.getUserId());

        //市级分销商
        dealer = sysDeptService.queryObject(dealer.getParentId());
        dealerAccountMap.put(CITY_DEALER, dealer.getUserId());

        //省级分销商
        dealer = sysDeptService.queryObject(dealer.getParentId());
        dealerAccountMap.put(PROVINCE_DEALER, dealer.getUserId());

        return dealerAccountMap;
    }

    /**
     * 账户变动方法
     *
     * @param userId
     * @param adjustType  调整类型（10 用户充值   11分成  20 ID卡充值 21使用设备 22 商户提现）
     * @param adjustMoney >0 表示扣款 <0 表示收钱
     * @param orderId
     */
    @Override
    public void changeAccount(Long userId, Byte adjustType, double adjustMoney, String orderId) {

        //检查余额
        AccountInfoEntity account = accountInfoDao.queryObjectByUserId(userId);

        double oldBalance = account.getBalance().doubleValue();
        double newBalance = oldBalance - adjustMoney;
        if (newBalance < 0) {
            throw new RRException("账户余额不足");
        }
        //账户更新 生成流水
        accountMoneyChangeRec(orderId, adjustMoney, account, oldBalance, newBalance, adjustType);
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

    /**
     * 根据订单类型 生成账户调整类型
     *
     * @param orderType
     * @return
     */
    private Byte getAdjustType(Byte orderType) {
        //ID卡充值订单
        if (Constant.OrderType.ID_RECHARGE.getValue().compareTo(orderType) == 0) {
            return Constant.AccountAdjustType.CARD_RECHARGE.getValue();
        } else {
            //设备使用订单
            return Constant.AccountAdjustType.USE_DEVICE.getValue();
        }
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


}
