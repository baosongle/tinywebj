package com.baosongle.tinywebj.core;

import lombok.Data;

@Data
public class Response {
    private HttpStatusCode statusCode;
    private HttpVersion version;

    private String body;

    public int getContentLength() {
        return body == null ? 0 : body.length();
    }
}
