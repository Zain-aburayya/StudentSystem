package org.example.communication;

import org.example.server.ServerDB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Response {

    public void receive(ObjectInputStream input , ObjectOutputStream output) throws IOException {
        ServerDB server = new ServerDB();
        active:
        while (true) {
            int requestType = input.readInt();
            switch (requestType) {
                case 1: {
                    int id = input.readInt();
                    output.writeObject(server.getAllCourses(id));
                    output.flush();
                }
                break;

                case 2: {
                    int id = input.readInt();
                    String courseName = input.readUTF();
                    output.writeObject(server.getCourse(courseName, id));
                    output.flush();
                }
                break;

                case 3:{
                    String courseName = input.readUTF();
                    output.writeUTF("Average -> ");
                    output.writeDouble(server.getAvg(courseName));
                    output.flush();
                }
                break;

                case 4:{
                    String courseName = input.readUTF();
                    output.writeUTF("Maximum -> ");
                    output.writeDouble(server.getMax(courseName));
                    output.flush();
                }
                break;

                case 5:{
                    String courseName = input.readUTF();
                    output.writeUTF("Minimum -> ");
                    output.writeDouble(server.getMin(courseName));
                    output.flush();
                }
                case 6:{
                    String courseName = input.readUTF();
                    output.writeUTF("Median -> ");
                    output.writeDouble(server.getMedian(courseName));
                    output.flush();
                }
                break;

                case 0:
                    break active;

                default:
                    throw new IllegalStateException("Operation is not supported from the server");
            }
        }
    }
}
