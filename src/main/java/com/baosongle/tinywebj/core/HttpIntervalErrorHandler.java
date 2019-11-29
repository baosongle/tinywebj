package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpIntervalErrorHandler implements HttpHandler {
    private String errorMessage;

    @Override
    public void handle(Request request, Response response) {
        response.setStatusCode(HttpStatusCode.IntervalServerError);
        response.setVersion(HttpVersion.HTTP_1_1);
        response.setBody(errorMessage.getBytes());
    }
}
