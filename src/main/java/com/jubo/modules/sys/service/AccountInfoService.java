package com.jubo.modules.sys.service;


import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface AccountInfoService {


    /**
     * 支付订单
     *
     * @param orderId
     */
    R pay(String orderId);

    AccountInfoEntity queryObjectByUserId(Long userId);

    AccountInfoEntity queryObject(String id);

    List<AccountInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(AccountInfoEntity accountInfo);

    void update(AccountInfoEntity accountInfo);

    void delete(String id);

    void deleteBatch(String[] ids);

    void auth(Map<String, String> params, SysUserEntity userEntity);
}
