package com.eleandy.customer.info.inspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InspectAnnotation {
    String userType() default "";
}
