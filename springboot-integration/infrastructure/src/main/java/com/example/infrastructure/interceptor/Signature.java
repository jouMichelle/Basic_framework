package com.example.infrastructure.interceptor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Signature {
    String value() default "aiui()";
}
