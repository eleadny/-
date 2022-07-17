package com.eleandy.customer.info.Controller;

import com.eleandy.customer.info.Pojo.SystemInfoUserPojo;
import com.eleandy.customer.info.Service.SystemInfoService;
import com.eleandy.customer.info.inspect.InspectAnnotation;
import com.eleandy.customer.info.log.OperLog;
import com.eleandy.customer.info.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/info")
public class SystemInfoController {
    @Autowired
    private SystemInfoService systemInfoService;
    @PostMapping("/sendSystemInfo")
    @OperLog(operDesc = "发送用户系统信息",operModul = "用户管理")
    @InspectAnnotation
    public SysResult sendSystemInfo(@RequestBody SystemInfoUserPojo systemInfoUserPojo){
        boolean b = systemInfoService.sendSystemInfo(systemInfoUserPojo);
        if(b)
            return SysResult.success("200",systemInfoUserPojo);
        return SysResult.falied("身份错误");
    }
}
