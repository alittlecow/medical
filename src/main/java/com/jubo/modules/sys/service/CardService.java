package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.CardEntity;

import java.util.List;
import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface CardService {

    /**
     * 绑卡
     */
    void bind(String code, Long userId);

    CardEntity queryObjectByCode(String code);

    CardEntity queryObject(String id);

    List<CardEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(CardEntity card);

    void update(CardEntity card);

    void delete(String id);

    void deleteBatch(String[] ids);
}
