package com.management.platform.modular.rest.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.management.platform.modular.rest.entity.BusCostAccount;
import com.management.platform.modular.rest.service.RestBusCostAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest/cost")
public class RestBusCostAccountController extends BaseController {

    @Autowired
    private RestBusCostAccountService busCostAccountService;

    @RequestMapping("/list")
    public ResponseData detail() {
        List<BusCostAccount> list = this.busCostAccountService.selectAllBusCostAccount();
        return ResponseData.success(list);
    }

}
