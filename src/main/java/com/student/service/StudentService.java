package com.student.service;

import com.student.entity.Student;
import com.student.repo.StudentRepo;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student addAStudent(Student student);
    Student updateStudent(int id, Student student);
    void deleteStudent(int id);

    List<Student> findUsersByAgeGreaterThan(int age);

    @Transactional
    void updateUserAge(String email, int newAge);

    List<Student> findByDegree(String degree);

    List<Student> findByName(String name);

    List<AggregationResults> performAggregation();

    long countStudent(String degree);

    List<Student> findByUniAndDegree(String uni, String degree);

    long countStudentsByDegree(String degree);

    double getAverageAge();
}
