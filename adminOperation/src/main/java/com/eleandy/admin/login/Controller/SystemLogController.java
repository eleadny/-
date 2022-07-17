package com.eleandy.admin.login.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.admin.login.Service.OperLogService;
import com.eleandy.admin.login.log.OperLogPojo;
import com.eleandy.admin.login.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/log")
public class SystemLogController {
    @Autowired
    private OperLogService operLogService;
    @GetMapping("/selectlog/{current}/{userName}/{dateTime}")
    public SysResult selectSystemLog(@PathVariable Integer current,@PathVariable String userName,@PathVariable String dateTime){
        Page<OperLogPojo> logPage = new Page<>(current,10);
        Page<OperLogPojo> operLogPojoPage = operLogService.selectOperLog(logPage, dateTime, userName);
        return SysResult.success("200",operLogPojoPage);
    }
}
