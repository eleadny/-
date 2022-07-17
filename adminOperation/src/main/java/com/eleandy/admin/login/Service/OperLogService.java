package com.eleandy.admin.login.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.admin.login.log.OperLogPojo;

//记录管理员操作
public interface OperLogService {
    void insertOperLog(OperLogPojo operLogPojo);
    Page<OperLogPojo> selectOperLog(Page<OperLogPojo> page,String dateTime,String userName);
}
