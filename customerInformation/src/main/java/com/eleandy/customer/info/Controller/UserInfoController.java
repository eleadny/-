package com.eleandy.customer.info.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.QuestionsPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import com.eleandy.customer.info.Service.QuestionsService;
import com.eleandy.customer.info.Service.UserInfoService;
import com.eleandy.customer.info.inspect.InspectAnnotation;
import com.eleandy.customer.info.log.OperLog;
import com.eleandy.customer.info.vo.StaticAdminInfoPojo;
import com.eleandy.customer.info.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private QuestionsService questionsService;
    @GetMapping("/select/{page}/{userName}")
//    查询普通用户信息
    public SysResult selectUserInfo(@PathVariable Integer page, @PathVariable String userName){
        Page<UserInfoPojo> selectUser = new Page<>(page,5);
        Page<UserInfoPojo> userInfoPojoPage = userInfoService.selectUserInfo(selectUser,userName);
        return SysResult.success("200",userInfoPojoPage);
    }
//    改变用户状态
    @PostMapping("/changeStatus")
    @OperLog(operDesc = "普通用户状态管理",operModul = "用户管理")
    @InspectAnnotation
    public SysResult changeStatus(@RequestBody UserInfoPojo userInfoPojo){
        userInfoService.changeUserStatus(userInfoPojo);
        return SysResult.success("200",userInfoPojo);
    }
//    查询用户提交的建议
    @GetMapping("/advance/{pageNumber}/{userName}")
    public SysResult selectUserAdvance(@PathVariable Integer pageNumber,@PathVariable String userName){
        Page<QuestionsPojo> page = new Page<>(pageNumber,5);
        Page<QuestionsPojo> advancePage = questionsService.selectUserAdvance(page, userName);
        return SysResult.success("200",advancePage);
    }
//    注入当前用户名信息
    @GetMapping("/adminname/{userName}/{token}")
    public void insertAdminName(@PathVariable String userName,@PathVariable String token){
        StaticAdminInfoPojo.setAdminName(userName);
        StaticAdminInfoPojo.setToken(token);
    }
}
