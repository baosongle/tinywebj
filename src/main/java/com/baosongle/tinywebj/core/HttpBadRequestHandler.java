package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpBadRequestHandler implements HttpHandler {
    private String errorMessage;

    @Override
    public void handle(Request request, Response response) {
        response.setStatusCode(HttpStatusCode.BadRequest);
        response.setVersion(HttpVersion.HTTP_1_1);
        response.setBody(errorMessage);
    }
}
