package com.baosongle.tinywebj;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketThread {
    private Socket socket;

    private Thread thread;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        thread = new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                Request request = RequestParser.parse(inputStream);
            } catch (HttpParseException e) {

            } catch (IOException e) {

            }
        });
        thread.start();
    }
}
