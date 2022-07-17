package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eleandy.customer.info.Mapper.admin.OperLogMapper;
import com.eleandy.customer.info.log.OperLogPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperLogServiceImpl implements OperLogService{
    @Autowired
    private OperLogMapper operLogMapper;
    @Override
    public void insertOperLog(OperLogPojo operLogPojo) {
        QueryWrapper<OperLogPojo> insertLog = new QueryWrapper<>();
        operLogPojo.setId(null);
        operLogMapper.insert(operLogPojo);
    }
}
