package com.student.service;

import com.student.entity.Student;
import com.student.repo.StudentRepo;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student addAStudent(Student student);
    Student updateStudent(int id, Student student);
    void deleteStudent(int id);
}
