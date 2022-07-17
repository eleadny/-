package com.eleandy.customer.info.Service;

import com.eleandy.customer.info.log.OperLogPojo;

//记录管理员操作
public interface OperLogService {
    void insertOperLog(OperLogPojo operLogPojo);
}
