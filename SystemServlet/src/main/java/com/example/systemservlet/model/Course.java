package com.example.systemservlet.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course implements java.io.Serializable {
    private String name;
    private Double mark;
}