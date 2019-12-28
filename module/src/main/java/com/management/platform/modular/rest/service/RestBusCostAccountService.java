package com.management.platform.modular.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.management.platform.modular.rest.entity.BusCostAccount;

import java.util.List;

public interface RestBusCostAccountService extends IService<BusCostAccount> {
    List<BusCostAccount> selectAllBusCostAccount();
}
