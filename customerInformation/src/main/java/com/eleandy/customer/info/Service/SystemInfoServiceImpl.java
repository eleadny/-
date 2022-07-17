package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eleandy.customer.info.Mapper.wangge.SystemInfoMapper;
import com.eleandy.customer.info.Pojo.SystemInfoUserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SystemInfoServiceImpl implements SystemInfoService{
    @Autowired
    private SystemInfoMapper systemInfoMapper;
    @Override
    public boolean sendSystemInfo(SystemInfoUserPojo user) {
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        user.setInfoTime(format);
        QueryWrapper<SystemInfoUserPojo> infoSend = new QueryWrapper<>();
        int insert = systemInfoMapper.insert(user);
        return insert > 0;
    }
}
