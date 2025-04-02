package com.proxy_server;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class ProxyServer {
    private static final int PORT = 9090;

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new RequestHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
