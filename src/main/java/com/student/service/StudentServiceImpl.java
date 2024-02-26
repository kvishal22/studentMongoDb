package com.student.service;

import com.student.entity.Student;
import com.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Override
    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student addAStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(int id,Student student) {
        Student student1 = studentRepo.findById(id).orElseThrow();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setDegree(student.getDegree());
        student1.setEmail(student.getEmail());
        student1.setUni(student.getUni());
        studentRepo.save(student1);
        return student1;
    }

    @Override
    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }
}
