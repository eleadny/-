package com.eleandy.customer.info.log;

import com.alibaba.druid.support.json.JSONUtils;
import com.eleandy.customer.info.Service.OperLogService;
import com.eleandy.customer.info.vo.IpUtils;
import com.eleandy.customer.info.vo.StaticAdminInfoPojo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OperLogAspect {
    @Autowired
    private OperLogService operLogService;
    OperLogPojo operLogPojo = new OperLogPojo();
    //    设置操作日志切入点 记录操作日志 在注解的位置切入代码
    @Pointcut("@annotation(com.eleandy.customer.info.log.OperLog)")
    public void operLogPoinCut() {}
    @AfterReturning(value = "operLogPoinCut()",returning = "keys")
    public void saveOperLog(JoinPoint joinPoint,Object keys){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperLog opLog = method.getAnnotation(OperLog.class);
        if (opLog != null){
            String operModul = opLog.operModul();
            String operDesc = opLog.operDesc();
            operLogPojo.setOperDesp(operDesc);
            operLogPojo.setOperModule(operModul);
        }
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        operLogPojo.setOperMethod(methodName);
        Map<String, String> rtnMap = converMap(request.getParameterMap());
        // 将参数所在的数组转换成json
        String params = JSONUtils.toJSONString(rtnMap);
        operLogPojo.setOperTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        operLogPojo.setAddressIp(IpUtils.getIpAddr(request));//抓取IP
        operLogPojo.setUserName(StaticAdminInfoPojo.getAdminName());
        operLogPojo.setOperInfo(keys.toString());
        operLogPojo.setOperUrl(request.getRequestURI());
        operLogService.insertOperLog(operLogPojo);
    }
//    paramMap request获取的参数数组
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }
}
