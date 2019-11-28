package com.baosongle.tinywebj;


import lombok.Data;

@Data
public class Request {
    private HttpMethod method;
    private String uri;
    private HttpVersion version;
}
