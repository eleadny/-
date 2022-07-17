package com.eleandy.customer.info.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.SubjectWorksPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import com.eleandy.customer.info.Service.WorkStateService;
import com.eleandy.customer.info.inspect.InspectAnnotation;
import com.eleandy.customer.info.log.OperLog;
import com.eleandy.customer.info.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/work")
public class WorkSxamineController {
    @Autowired
    private WorkStateService workStateService;
    @GetMapping("/worksxamine/{current}/{workState}/{state}")
    public SysResult changeWorkSxamine(@PathVariable Integer current, @PathVariable Integer workState,@PathVariable String state){
        Page<SubjectWorksPojo> page = new Page<>(current,7);
        Page<SubjectWorksPojo> subjectWorksPojoPage = workStateService.selectUndeterminedOrDetermineWorks(page, workState, state);
        return SysResult.success("200",subjectWorksPojoPage);
    }
    @GetMapping("/viewwork/{publisher}")
    public SysResult viewWorkInfo(@PathVariable Integer publisher){
        UserInfoPojo userInfoPojo = workStateService.viewWorkInfo(publisher);
        if(userInfoPojo == null)
            return SysResult.falied("未查询到用户数据");
        return SysResult.success("200",userInfoPojo);
    }
    @GetMapping("/changeshelves/{workId}/{shelves}/{passState}")
    @OperLog(operDesc = "资源管理",operModul = "审查资源")
    @InspectAnnotation
    public SysResult workUpdatesOrDown(@PathVariable Integer workId,@PathVariable Integer shelves,@PathVariable String passState){
        int i = workStateService.workUpdatesOrDown(workId, shelves, passState);
        if (i>0)
            return SysResult.success("200",shelves);
        return SysResult.falied("修改失败");
    }
}
