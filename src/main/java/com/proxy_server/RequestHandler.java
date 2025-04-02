package com.proxy_server;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class RequestHandler implements Runnable {
    private final Socket clientSocket;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String requestUrl = in.readLine();
            String response = fetchWebsiteContent(requestUrl);
            out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fetchWebsiteContent(String requestUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(requestUrl, String.class);
    }
}
