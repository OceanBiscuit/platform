package com.management.platform.modular.rest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.management.platform.modular.rest.entity.BusCostAccount;

import java.util.List;

public interface RestBusCostAccountMapper  extends BaseMapper<BusCostAccount> {

    List<BusCostAccount> selectAllBusCostAccount();
}
