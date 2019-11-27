package com.baosongle.tinywebj;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@AllArgsConstructor
public class SocketThread {
    private Socket socket;

    public void start() throws IOException {
        InputStream inputStream = socket.getInputStream();
        Request request = RequestParser.parse(inputStream);

    }


}
