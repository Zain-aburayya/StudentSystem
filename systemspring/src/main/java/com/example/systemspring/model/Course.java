package com.example.systemspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Course implements java.io.Serializable {
    private String name;
    private Double mark;
}
