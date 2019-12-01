package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@AllArgsConstructor
public enum HttpHeader {
    ContentLength("Content-Length"),
    ContentType("Content-Type");

    @Getter
    private String header;
}
