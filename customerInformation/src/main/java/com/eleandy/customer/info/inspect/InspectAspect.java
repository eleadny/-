package com.eleandy.customer.info.inspect;

import com.eleandy.customer.info.Service.AdminUserInfo;
import com.eleandy.customer.info.vo.StaticAdminInfoPojo;
import com.eleandy.customer.info.vo.SysResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class InspectAspect {

    @Autowired
    private AdminUserInfo adminUserInfo;

    @Pointcut("@annotation(com.eleandy.customer.info.inspect.InspectAnnotation)")
    public void inspectPointCut(){}

    @Around(value = "inspectPointCut()")
    public SysResult inspectUserType(ProceedingJoinPoint pjd){
        Object result = null;
        if (StaticAdminInfoPojo.getTokens() == null || StaticAdminInfoPojo.getAdminName() == null){
            return SysResult.falied("刷新");
        }
        String userType = adminUserInfo.examinationUsertype(StaticAdminInfoPojo.getTokens(), StaticAdminInfoPojo.getAdminName());
        if (userType.equals("正式") || StaticAdminInfoPojo.getAdminName().equals("")){
            try {
                Object proceed = pjd.proceed();
                return SysResult.success("200",proceed);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return SysResult.falied("权限不足");
    }
}
