package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.ApplyCardDao;
import com.jubo.modules.sys.entity.ApplyCardEntity;
import com.jubo.modules.sys.service.ApplyCardService;


@Service("applyCardService")
public class ApplyCardServiceImpl implements ApplyCardService {
    @Autowired
    private ApplyCardDao applyCardDao;

    @Override
    public ApplyCardEntity queryObject(String id) {
        return applyCardDao.queryObject(id);
    }

    @Override
    public List<ApplyCardEntity> queryList(Map<String, Object> map) {
        return applyCardDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return applyCardDao.queryTotal(map);
    }

    @Override
    public void save(ApplyCardEntity applyCard) {
        applyCardDao.save(applyCard);
    }

    @Override
    public void update(ApplyCardEntity applyCard) {
        //更新更新时间
        applyCard.setUpdateTime(new Date());
        applyCardDao.update(applyCard);
    }

    @Override
    public void delete(String id) {
        applyCardDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        applyCardDao.deleteBatch(ids);
    }

}
