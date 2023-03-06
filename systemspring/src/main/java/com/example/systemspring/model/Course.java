package com.example.systemspring.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course implements java.io.Serializable {
    private String name;
    private Double mark;

    public Course(String math, double math1, String physics, double physics1, String arabic, double arabic1) {
    }
}
