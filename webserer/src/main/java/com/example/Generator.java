package com.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Generator {
    public static void main(String[] args) throws IOException {
        int port = Integer.valueOf(args[0]);

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        ExecutorService executors = Executors.newFixedThreadPool(100);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            executors.submit(new RequestHandler(clientSocket));
        }
    }

    private static AtomicInteger counter = new AtomicInteger(0);

    private static class RequestHandler implements Runnable {
        private final Socket clientSocket;

        public RequestHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String content = "Hello";
                int contentLength = content.getBytes().length;


                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/plain");
                writer.println("Content-Length: " + contentLength);
                writer.println();
                writer.println(content);

                clientSocket.close();
//                if (counter.get() % 100 == 0) {
                    System.out.println(counter.incrementAndGet());
//                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
