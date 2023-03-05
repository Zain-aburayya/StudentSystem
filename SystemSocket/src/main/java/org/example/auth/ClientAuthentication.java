package org.example.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClientAuthentication {
    private int id;
    private String password;
    private boolean isAuthenticated = false;
    private Scanner scanner = new Scanner(System.in);

    public int getId() {
        return id;
    }

    private void getStudentInfo() {
        System.out.println("- Please enter your student ID -> ");
        id = scanner.nextInt();
        System.out.println("- Please enter your student PASSWORD -> ");
        password = scanner.next();
    }

    public void check(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        while (!isAuthenticated) {
            getStudentInfo();
            output.writeInt(id); output.flush();
            output.writeUTF(password); output.flush();
            System.out.println(input.readObject());
            isAuthenticated = input.readBoolean();
        }
    }
}
