package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eleandy.customer.info.Mapper.admin.AdminUserInfoMapper;
import com.eleandy.customer.info.Pojo.AdminUserInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserInfoImpl implements AdminUserInfo{

    @Autowired
    private AdminUserInfoMapper adminUserInfoMapper;

    @Override
    public String examinationUsertype(String token, String userName) {
        QueryWrapper<AdminUserInfoPojo> admin = new QueryWrapper<>();
        admin.select("account_type").eq("user_number",userName).eq("token",token);
        AdminUserInfoPojo adminUserInfoPojo = adminUserInfoMapper.selectOne(admin);
        if (adminUserInfoPojo == null){
            return "无效";
        }
        return adminUserInfoPojo.getAccountType();
    }
}
