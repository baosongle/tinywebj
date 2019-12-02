package com.baosongle.tinywebj.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value();
    boolean required() default true;
    String defaultValue() default ValueConstants.DEFAULT_NONE;
}
