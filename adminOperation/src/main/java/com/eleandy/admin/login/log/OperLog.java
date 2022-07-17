package com.eleandy.admin.login.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    String operModul() default ""; // 操作模块
    String operDesc() default "";  // 操作说明
}
