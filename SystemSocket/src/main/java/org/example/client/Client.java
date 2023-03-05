package org.example.client;

import org.example.auth.ClientAuthentication;
import org.example.communication.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static ClientAuthentication authentication;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost" , 8080);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            authentication = new ClientAuthentication();
            authentication.check(input,output);
            int id = authentication.getId();

            Request request = new Request(id);
            request.send(input,output);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
