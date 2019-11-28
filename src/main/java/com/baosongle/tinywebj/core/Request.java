package com.baosongle.tinywebj.core;


import lombok.Data;

@Data
public class Request {
    private HttpMethod method;
    private String uri;
    private HttpVersion version;
}
