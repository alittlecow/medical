package com.jubo.modules.sys.service.impl;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.Constant;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.common.validator.Assert;
import com.jubo.modules.sys.dao.CardDao;
import com.jubo.modules.sys.entity.CardEntity;
import com.jubo.modules.sys.entity.CardHistoryEntity;
import com.jubo.modules.sys.service.CardHistoryService;
import com.jubo.modules.sys.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("cardService")
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;

    @Autowired
    private CardHistoryService cardHistoryService;

    @Override
    public void bind(Map map) {

        map.put("isBind", Constant.CommonStatus.TRUE.getValue());

        cardDao.updateCardByCode(map);
    }

    @Override
    public void active(Map map) {

        map.put("isActive", Constant.CommonStatus.TRUE.getValue());

        cardDao.updateCardByCode(map);
    }

    /**
     * 使用卡 times 次
     *
     * @param code
     * @param times
     */
    @Override
    public void changeCardTimes(String code, int times) {
        CardEntity card = cardDao.queryObjectByCode(code);
        Assert.isNull(card, "ID卡不存在");

        int leftCount = card.getCount() - times;
        if (leftCount < 0) {
            throw new RRException("消费次数不足");
        }

        //消费次数减一
        card.setCount(leftCount);
        cardDao.update(card);

        CardHistoryEntity cardHistory = new CardHistoryEntity();
        cardHistory.setId(UUIDUtil.getUUId());
        cardHistory.setCode(code);
        cardHistory.setAdjustCount(times);
        cardHistory.setCount(leftCount - times);
        cardHistory.setType(Constant.CardAdjustType.USE.getValue());
        cardHistory.setCreateTime(new Date());
        cardHistoryService.save(cardHistory);

    }

    @Override
    public CardEntity queryObject(String id) {
        return cardDao.queryObject(id);
    }

    @Override
    public CardEntity queryObjectByCode(String code) {
        return cardDao.queryObjectByCode(code);
    }

    @Override
    public CardEntity queryObjectByUserId(Long userId) {
        return cardDao.queryObjectByUserId(userId);
    }

    @Override
    public List<CardEntity> queryList(Map<String, Object> map) {
        return cardDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cardDao.queryTotal(map);
    }

    //ID卡入库
    @Override
    public void save(CardEntity card) {
        String id = UUIDUtil.getUUId();
        Date nowTime = new Date();
        card.setLastUseTime(nowTime);
        card.setCreateTime(nowTime);
        card.setIsBind(Constant.CommonStatus.FALSE.getValue());
        card.setIsActive(Constant.CommonStatus.FALSE.getValue());
        card.setId(id);
        card.setCount(0);
        cardDao.save(card);
    }

    @Override
    public void update(CardEntity card) {
        card.setLastUseTime(new Date());
        cardDao.update(card);
    }

    @Override
    public void delete(String id) {
        cardDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        cardDao.deleteBatch(ids);
    }

}
