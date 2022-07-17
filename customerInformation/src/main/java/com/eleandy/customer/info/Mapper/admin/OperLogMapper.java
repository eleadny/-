package com.eleandy.customer.info.Mapper.admin;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleandy.customer.info.log.OperLogPojo;
import org.springframework.stereotype.Repository;

@Repository
@DS("admin")
public interface OperLogMapper extends BaseMapper<OperLogPojo> {
}
