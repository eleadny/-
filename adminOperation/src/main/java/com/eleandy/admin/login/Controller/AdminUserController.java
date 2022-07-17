package com.eleandy.admin.login.Controller;

import com.eleandy.admin.login.Pojo.AdminUserInfoPojo;
import com.eleandy.admin.login.Service.AdminUserLoginService;
import com.eleandy.admin.login.log.OperLog;
import com.eleandy.admin.login.vo.IpUtils;
import com.eleandy.admin.login.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminUserLoginService adminUserLoginService;
    @OperLog(operDesc = "管理员登录",operModul = "用户管理")
    @PostMapping("/login")
    public SysResult authentication(@RequestBody AdminUserInfoPojo adminUserInfoPojo){
        String s = adminUserLoginService.verifyAdministratorIdentity(adminUserInfoPojo);
        if(s.split(",")[0].equals("ERROR")){
            return SysResult.falied(s);
        }
        return SysResult.success(s);
    }
//    查询管理员用户自动
    @GetMapping("/selectuser/{session}")
    public SysResult selectUserInfo(@PathVariable String session){
        AdminUserInfoPojo adminUserInfoPojo = adminUserLoginService.selectUserInfo(session);
        return SysResult.success("200",adminUserInfoPojo);
    }
//    管理员登出
    @PostMapping("/loginout")
    public SysResult adminLoginOut(@RequestBody AdminUserInfoPojo adminUserInfoPojo, HttpServletRequest request){
        String ipAddr = IpUtils.getIpAddr(request);
        adminUserLoginService.adminUserLoginOut(adminUserInfoPojo,ipAddr);
        return SysResult.success("200");
    }
}
