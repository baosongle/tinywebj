package com.baosongle.tinywebj.core;

public class HttpNotFoundHandler implements HttpHandler {
    @Override
    public void handle(Request request, Response response) {
        response.setStatusCode(HttpStatusCode.NotFound);
        response.setVersion(HttpVersion.HTTP_1_1);
        request.setBody("The requested uri " + request.getUri() + " does not exists");
    }
}
