package com.jubo.modules.sys.dao;

import com.jubo.modules.sys.entity.CardEntity;
;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
@Mapper
public interface CardDao extends BaseDao<CardEntity> {

    /**
     * 根据ID卡号查询信息
     *
     */
    CardEntity queryObjectByCode(String code);
}
