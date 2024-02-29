package com.student.controller;

import com.student.entity.Student;
import com.student.repo.StudentRepo;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;
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
    @GetMapping("/year/{age}")
    public List<Student> findStudents(@PathVariable int age){
        return studentService.findUsersByAgeGreaterThan(age);
    }
    @PutMapping("/updateAge/{email}/{age}")
    public String updateAge(@PathVariable String email,@PathVariable int age){
        studentService.updateUserAge(email,age);
        return  "age updated";
    }
    @GetMapping("/findDegree/{degree}")
    public List<Student> findDegree(@PathVariable String degree){
        return studentService.findByDegree(degree);
    }
    @GetMapping("/findByName/{firstName}")
    public List<Student> findByFirstName(@PathVariable String firstName){
        return studentService.findByName(firstName);
    }
    @GetMapping("/aggregate")
    public List<AggregationResults> performAggregation() {
        return studentService.performAggregation();
    }
    @GetMapping("/findTotalWithoutAgg/{degree}")
    public long total(@PathVariable String degree){
        return studentService.countStudent(degree);
    }
    @GetMapping("findTotalWithAgg/{degree}")
    public List<Student> totall(@PathVariable String degree){
        return studentRepo.getTotalStudentsByDegree(degree);
    }
    @GetMapping("/findByUniAndDegree/{uni}/{degree}")
    public List<Student> find(@PathVariable String uni,@PathVariable String degree){
        return studentService.findByUniAndDegree(uni,degree);
    }
    @GetMapping("/findCount/{degree}")
    public long findCount(@PathVariable String degree){
        return studentService.countStudentsByDegree(degree);
    }
    @GetMapping("/avgAge")
    public double avgAge(){
        return studentService.getAverageAge();
    }
    @GetMapping("/search")
    public List<Student> searchProducts(@RequestParam String keyword) {
        return studentRepo.findByBioContaining(keyword);
    }
    @GetMapping("/skills")
    public List<Student> skill(@RequestParam String skill){
        return studentRepo.findBySkills(skill);
    }

}
