package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpInternalErrorHandler implements HttpHandler {
    private String errorMessage;

    @Override
    public void handle(Request request, Response response) {
        response.setStatusCode(HttpStatusCode.InternalServerError);
        response.setVersion(HttpVersion.HTTP_1_1);
        response.setBody(errorMessage.getBytes());
    }
}
