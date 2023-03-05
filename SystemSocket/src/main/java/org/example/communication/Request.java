package org.example.communication;

import org.example.model.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Request {
    private int studentId;
    private Scanner scanner = new Scanner(System.in);
    public Request(int id) {
        studentId = id;
    }

    private void printList() {
        System.out.println("\nWelcome to the grade viewer!");
        System.out.println("Please choose an option by typing the corresponding number:");
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. View your marks in all courses");
        System.out.println("2. View your mark in a particular course");
        System.out.println("3. View the average mark in a particular course");
        System.out.println("4. View the maximum mark in a particular course");
        System.out.println("5. View the minimum mark in a particular course");
        System.out.println("6. View the median mark in a particular course");
    }

    private void printCourses() {
        System.out.println("\n- Please select a course by entering its number:");
        System.out.println("1. Math - covers topics such as algebra, geometry, and calculus");
        System.out.println("2. Physics - covers topics such as mechanics, thermodynamics, and electricity");
        System.out.println("3. Arabic - covers topics such as grammar, vocabulary, and literature");
    }

    private String getCourseName() {
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1)
                return "math";
            else if (choice == 2)
                return "physics";
            else if (choice == 3)
                return "arabic";
        }
    }

    public void send(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        active:
        while (true) {
            printList();
            int requestType = scanner.nextInt();
            output.writeInt(requestType);
            output.flush();

            switch(requestType) {
                case 1: {
                    output.writeInt(studentId);
                    output.flush();
                    ArrayList<Course> courses = (ArrayList<Course>) input.readObject();
                    for (Course c : courses) {
                        System.out.println(c);
                    }
                }
                break;
                case 2: {
                    printCourses();
                    String courseName = getCourseName();
                    output.writeInt(studentId); output.flush();
                    output.writeUTF(courseName); output.flush();
                    System.out.println(input.readObject());
                }
                break;

                case 3, 6, 5, 4: {
                    printCourses();
                    String courseName = getCourseName();
                    output.writeUTF(courseName); output.flush();
                    System.out.println( "\n" + input.readUTF() + input.readDouble());
                }
                break;

                case 0:
                    break active;
                default:
                    System.out.println("Invalid option , try again ...");
            }
        }
    }
}
