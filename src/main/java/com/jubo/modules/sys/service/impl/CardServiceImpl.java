package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.UUIDUtil;
import com.jubo.modules.sys.dao.CardDao;
import com.jubo.modules.sys.entity.CardEntity;
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

    @Override
    public void bind(String code, Long userId) {

        CardEntity card = new CardEntity();
        card.setId(UUIDUtil.getUUId());
        card.setCode(code);
        card.setUserId(userId);
        card.setCount(0);
        card.setCreateTime(new Date());
        cardDao.save(card);
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
    public List<CardEntity> queryList(Map<String, Object> map) {
        return cardDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cardDao.queryTotal(map);
    }

    @Override
    public void save(CardEntity card) {
        String id = UUIDUtil.getUUId();
        Date nowTime = new Date();
        card.setLastUseTime(nowTime);
        card.setCreateTime(nowTime);
        card.setId(id);
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
