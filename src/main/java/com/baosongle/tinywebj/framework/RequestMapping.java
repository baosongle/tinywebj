package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@interface RequestMapping {
    HttpMethod method();
}
