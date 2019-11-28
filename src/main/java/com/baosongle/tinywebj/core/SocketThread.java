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
            Response response;
            try {
                InputStream inputStream = socket.getInputStream();
                Request request = RequestParser.parse(inputStream);
                ResponseProcessor responseProcessor = new ResponseProcessor(request);
                response = responseProcessor.process();
            } catch (HttpParseException e) {
                response = new Response(HttpStatusCode.BadRequest, HttpVersion.HTTP_1_1);
                response.setBody(e.getMessage());
            } catch (IOException e) {
                response = new Response(HttpStatusCode.IntervalServerError, HttpVersion.HTTP_1_1);
                response.setBody(e.getMessage());
            }
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
