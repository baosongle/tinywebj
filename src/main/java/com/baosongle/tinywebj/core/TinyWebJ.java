package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class TinyWebJ {
    private int port;

    public void run() throws IOException {
        log.info("TinyWebJ running at 0.0.0.0:" + port);
        HttpRouteRegistry.getInstance().register(HttpMethod.GET, "/index.html", new StaticResourceHandler());
        HttpRouteRegistry.getInstance().register(HttpMethod.GET, "/tinywebj.png", new StaticResourceHandler());

        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            if (log.isDebugEnabled()) {
                log.debug("Incoming socket at {}", socket.getRemoteSocketAddress().toString());
            }
            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }
    }
}
