package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.AccountInfoDao;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.service.AccountInfoService;


@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;


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
