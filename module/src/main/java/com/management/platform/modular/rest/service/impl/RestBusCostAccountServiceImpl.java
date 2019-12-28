package com.management.platform.modular.rest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.management.platform.modular.rest.entity.BusCostAccount;
import com.management.platform.modular.rest.mapper.RestBusCostAccountMapper;
import com.management.platform.modular.rest.service.RestBusCostAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestBusCostAccountServiceImpl extends ServiceImpl<RestBusCostAccountMapper, BusCostAccount> implements RestBusCostAccountService {

    @Autowired
    @SuppressWarnings("all")
    private RestBusCostAccountMapper busCostAccountMapper;

    @Override
    public List<BusCostAccount> selectAllBusCostAccount() {
        return busCostAccountMapper.selectAllBusCostAccount();
    }
}
