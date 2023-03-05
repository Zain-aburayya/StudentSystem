package org.example.server;

import org.example.auth.ServerAuthentication;
import org.example.communication.Response;
import org.example.model.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket socket = serverSocket.accept();
                ServerDB server = new ServerDB();
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ServerAuthentication authentication = new ServerAuthentication();
                Response response = new Response();
                authentication.check(input,output);
                response.receive(input,output);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
