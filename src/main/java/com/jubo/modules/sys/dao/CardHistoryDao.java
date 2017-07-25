package com.jubo.modules.sys.dao;

import com.jubo.modules.sys.entity.CardHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-25 12:54:27
 */
@Mapper
public interface CardHistoryDao extends BaseDao<CardHistoryEntity> {

    CardHistoryEntity queryObjectByCode(String code);

}
