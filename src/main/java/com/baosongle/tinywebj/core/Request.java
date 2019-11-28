package com.baosongle.tinywebj.core;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {
    private HttpMethod method;
    private String uri;
    private HttpVersion version;

    // could be null
    private String body;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<String, String> headers = new HashMap<>();

    public String getHeader(String name) {
        return headers.get(name);
    }

    void setHeader(String name, String value) {
        headers.put(name, value);
    }
}
