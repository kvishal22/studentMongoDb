package com.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection ="boys")
public class Student {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String degree;
    private String uni;
    private int age;

}
