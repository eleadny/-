package com.eleandy.customer.info.Controller;

import com.eleandy.customer.info.Service.UserInfoService;
import com.eleandy.customer.info.vo.OnlionNumbersPojo;
import com.eleandy.customer.info.vo.SysResult;
import com.eleandy.customer.info.vo.WorkerInfoProPojo;
import com.eleandy.customer.info.vo.WorksProportionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/webstate")
public class WebStateController {
    @Autowired
    private UserInfoService userInfoService;
    //    查询在线用户数据
    @GetMapping("/userstate")
    public SysResult selectOnlionUser(){
        OnlionNumbersPojo userProportion = userInfoService.selectOnlionUser();
        return SysResult.success("200",userProportion);
    }
    @GetMapping("/workers")
    public SysResult selectWokers(){
        WorksProportionPojo worksProportionPojo = userInfoService.selectWorksproportion();
        return SysResult.success("200",worksProportionPojo);
    }
    @GetMapping("/workersinfo")
    public SysResult selectWokersInfo(){
        WorkerInfoProPojo workerInfoProPojo = userInfoService.selectWorksInfoProportion();
        return SysResult.success("200",workerInfoProPojo);
    }
    @GetMapping("/bosses")
    public SysResult selectBoss(){
        return SysResult.success("200");
    }
}
