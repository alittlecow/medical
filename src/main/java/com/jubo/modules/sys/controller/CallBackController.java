package com.jubo.modules.sys.controller;

import com.jubo.modules.sys.bean.PayNoticeParam;
import com.jubo.modules.sys.service.impl.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjp
 * @version 1.0.0
 */
@RestController
@RequestMapping("beecloud")
public class CallBackController {
    @Autowired
    private CallBackService callBackService;

    /**
     * 列表
     */
    @RequestMapping("/callback")
    public String payNotice(@RequestParam PayNoticeParam payNoticeParam){
        return callBackService.payNotice(payNoticeParam);
    }
}
