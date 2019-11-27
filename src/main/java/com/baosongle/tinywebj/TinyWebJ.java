package com.baosongle.tinywebj;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
public class TinyWebJ {
    private int port;

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }
    }
}
