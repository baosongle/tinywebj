package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.HttpMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = HttpMethod.GET)
public @interface GetMapping {
    String value();
}
