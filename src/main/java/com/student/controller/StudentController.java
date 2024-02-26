package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student> getAll(){
        return studentService.getAll();
    }
    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentService.addAStudent(student);
    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable int id){
        return studentService.updateStudent(id,student);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return "Student deleted";
    }
}
