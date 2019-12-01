package com.baosongle.tinywebj.core;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private HttpStatusCode statusCode;
    private HttpVersion version;

    private Map<HttpHeader, String> headers = new HashMap<>();

    @ToString.Exclude
    private byte[] body;

    @ToString.Include
    public int getContentLength() {
        return body == null ? 0 : body.length;
    }
}
