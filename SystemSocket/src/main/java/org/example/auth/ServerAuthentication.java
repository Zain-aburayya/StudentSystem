package org.example.auth;

import org.example.server.ServerDB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ServerAuthentication {
    private ServerDB serverDB = new ServerDB();
    public void check(ObjectInputStream input , ObjectOutputStream output) throws IOException {
        while (true) {
            int id = input.readInt();
            String password = input.readUTF();
            if (!serverDB.isRegisteredStudent(id)) {
                output.writeObject("Incorrect student id ..."); output.flush();
                output.writeBoolean(false); output.flush();
            }
            else if (!serverDB.isValidPassword(id, password)) {
                output.writeObject("Incorrect student password ..."); output.flush();
                output.writeBoolean(false); output.flush();
            }
            else {
                output.writeObject("Logged in successfully ..."); output.flush();
                output.writeBoolean(true); output.flush();
                break;
            }
        }
    }
}
