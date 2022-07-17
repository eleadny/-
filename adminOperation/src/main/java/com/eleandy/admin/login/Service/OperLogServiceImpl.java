package com.eleandy.admin.login.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.admin.login.Mapper.OperLogMapper;
import com.eleandy.admin.login.log.OperLogPojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OperLogServiceImpl implements OperLogService{
    @Autowired
    private OperLogMapper operLogMapper;
    @Override
    public void insertOperLog(OperLogPojo operLogPojo) {
        QueryWrapper<OperLogPojo> insertLog = new QueryWrapper<>();
        operLogMapper.insert(operLogPojo);
    }

    @Override
    public Page<OperLogPojo> selectOperLog(Page<OperLogPojo> page, String dateTime, String userName) {
        QueryWrapper<OperLogPojo> selectLogs = new QueryWrapper<>();
        boolean timeBool = true;
        boolean nameBool = true;
        if(dateTime.equals("all")){
            timeBool = false;
            dateTime = new Date()+","+new Date();
        }
        if(userName.equals("all"))
            nameBool = false;
        String[] time = dateTime.split(",");
        selectLogs.select("id","user_name","oper_info","oper_module","oper_method","oper_desp","oper_url","address_ip","oper_time").
                eq(nameBool,"user_name",userName).between(timeBool,"oper_time",time[0],time[1]);
        return operLogMapper.selectPage(page,selectLogs);
    }
}
