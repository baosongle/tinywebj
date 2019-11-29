package com.baosongle.tinywebj.core;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Slf4j
class SocketThread {
    private Socket socket;

    SocketThread(Socket socket) {
        this.socket = socket;
    }

    void start() {
        Thread thread = new Thread(() -> {
            Request request = null;
            Response response = new Response();
            HttpHandler httpHandler;
            try {
                InputStream inputStream = socket.getInputStream();
                request = RequestParser.parse(inputStream);
                log.info("Received: {}", request.toString());
                httpHandler = HttpRouteRegistry.getInstance().getHandler(request.getMethod(), request.getUri());
                if (httpHandler == null) {
                    httpHandler = new HttpNotFoundHandler();
                }
            } catch (HttpParseException e) {
                httpHandler = new HttpBadRequestHandler("Error on paring HTTP request: " + e.getMessage());
            } catch (IOException e) {
                httpHandler = new HttpIntervalErrorHandler(e.getMessage());
            }
            httpHandler.handle(request, response);
            try {
                log.info("Response with: {}", response.toString());
                String responseText = ResponseParser.parse(response);
                socket.getOutputStream().write(responseText.getBytes());
                socket.close();
            } catch (IOException e) {
                log.error("IO Error on writing response to client", e);
            }
        });
        thread.start();
    }
}
