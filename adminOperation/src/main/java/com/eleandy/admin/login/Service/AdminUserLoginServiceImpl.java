package com.eleandy.admin.login.Service;

import com.eleandy.admin.login.Mapper.AdminUserInfoMapper;
import com.eleandy.admin.login.Mapper.UserExitMapper;
import com.eleandy.admin.login.Pojo.AdminUserInfoPojo;
import com.eleandy.admin.login.Pojo.RecordSheetPojo;
import com.eleandy.admin.login.vo.HashSha;
import com.eleandy.admin.login.vo.JwtUtils;
import com.eleandy.admin.login.vo.StaticAdminInfoPojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdminUserLoginServiceImpl implements AdminUserLoginService {
    @Autowired
    private AdminUserInfoMapper adminUserInfoMapper;
    @Autowired
    private UserExitMapper userExitMapper;
    @Override
    public String verifyAdministratorIdentity(AdminUserInfoPojo adminUserInfoPojo) {
        HashSha hashSha = new HashSha();
        String hashPassword = hashSha.UserHash(adminUserInfoPojo.getUserPassword());
        QueryWrapper<AdminUserInfoPojo> selectUser = new QueryWrapper<>();
        StaticAdminInfoPojo.setAdminName(adminUserInfoPojo.getUserNumber());
        selectUser.select("id","user_number","user_password","login_time","error_number","user_static","login_static","account_type").
        eq("user_number",adminUserInfoPojo.getUserNumber());
        AdminUserInfoPojo userNameBool = adminUserInfoMapper.selectOne(selectUser);
        if (userNameBool == null){
            return "ERROR,用户或密码不正确";
        }
        selectUser.eq("user_password",hashPassword);
        AdminUserInfoPojo userpassBool = adminUserInfoMapper.selectOne(selectUser);
        if (userNameBool.getUserStatic() == 0){
            return "ERROR,账号状态异常请联系管理员处理";
        }
        if(userNameBool.getErrorNumber() >=3){
            return "ERROR,用户错误次数上限，请联系管理员处理";
        }
        if (userpassBool == null){
            selectUser.clear();
            selectUser.select("error_number").eq("user_number",adminUserInfoPojo.getUserNumber());
            userNameBool.setErrorNumber(userNameBool.getErrorNumber()+1);
            adminUserInfoMapper.update(userNameBool,selectUser);
            return "ERROR,用户或密码不正确";
        }
        JwtUtils jwtUtils = new JwtUtils();
        Map<String,Object> dataMap = new ConcurrentHashMap<>();
        dataMap.put("adminName",userpassBool.getUserNumber());
        String token = jwtUtils.createJwt(userpassBool.getId(),userpassBool.getUserNumber(),dataMap);
        userpassBool.setLoginTime(new Date().getTime());
        adminUserInfoMapper.insertUserToken(userpassBool.getUserNumber(),token, userpassBool.getLoginTime(),1);
        return token;
    }

    @Override
    public void adminUserLoginOut(AdminUserInfoPojo adminUserInfoPojo, String ipAddr) {
        QueryWrapper<AdminUserInfoPojo> exit = new QueryWrapper<>();
        exit.select("login_static").eq("user_number",adminUserInfoPojo.getUserNumber());
        AdminUserInfoPojo exitUser = new AdminUserInfoPojo();
        exitUser.setUserNumber(adminUserInfoPojo.getUserNumber()).setLoginStatic(0);
        adminUserInfoMapper.update(exitUser,exit);
        RecordSheetPojo userExitInfo = new RecordSheetPojo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userExitInfo.setUserName(adminUserInfoPojo.getUserNumber()).setExitTime(sdf.format(new Date())).setAddressId(ipAddr);
        userExitMapper.insert(userExitInfo);
    }

    @Override
    public AdminUserInfoPojo selectUserInfo(String session) {
        QueryWrapper<AdminUserInfoPojo> selectUser = new QueryWrapper<>();
        selectUser.select("id","user_number","user_password","login_time","error_number","user_static","login_static").
        eq("token",session);
        return adminUserInfoMapper.selectOne(selectUser);
    }
}
