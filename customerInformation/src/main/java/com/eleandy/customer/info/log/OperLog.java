package com.eleandy.customer.info.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    String operModul() default ""; // 操作模块
    String operInfo() default "";   //操作详细报告
    String operDesc() default "";  // 操作说明
}
