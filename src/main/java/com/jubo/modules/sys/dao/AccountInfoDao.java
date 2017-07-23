package com.jubo.modules.sys.dao;

import com.jubo.modules.sys.entity.AccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@Mapper
public interface AccountInfoDao extends BaseDao<AccountInfoEntity> {


    AccountInfoEntity queryObjectByUserId(Long userId);

}
