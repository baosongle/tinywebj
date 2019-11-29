package com.baosongle.tinywebj.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketThread {
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            Request request = null;
            Response response = new Response();
            HttpHandler httpHandler;
            try {
                InputStream inputStream = socket.getInputStream();
                request = RequestParser.parse(inputStream);
                httpHandler = HttpRouteRegistry.getInstance().getHandler(request.getMethod(), request.getUri());
                if (httpHandler == null) {
                    httpHandler = new HttpNotFoundHandler();
                }
            } catch (HttpParseException e) {
                httpHandler = new HttpBadRequestHandler("Error parse HTTP request: " + e.getMessage());
            } catch (IOException e) {
                httpHandler = new HttpIntervalErrorHandler(e.getMessage());
            }
            httpHandler.handle(request, response);
            try {
                String responseText = ResponseParser.parse(response);
                socket.getOutputStream().write(responseText.getBytes());
                socket.close();
            } catch (IOException e) {
                // TODO 改成打印日志
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
