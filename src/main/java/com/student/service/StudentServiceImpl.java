package com.student.service;

import com.student.entity.Student;
import com.student.repo.StudentRepo;
import com.student.util.AverageAgeResult;
import com.student.util.CountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
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

        if (student.getFirstName() != null) {
            student1.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            student1.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            student1.setEmail(student.getEmail());
        }
        studentRepo.save(student1);
        return student1;
    }

    @Override
    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }
    @Override
    public List<Student> findUsersByAgeGreaterThan(int age) {
        return studentRepo.findByAgeGreaterThan(age);
    }
    @Transactional
    @Override
    public void updateUserAge(String email, int newAge) {
        Student student = studentRepo.findByEmail(email).get();
        student.setAge(newAge);
        studentRepo.save(student);
    }
    @Override
    public List<Student> findByDegree(String degree){
        return studentRepo.findByDegreeIgnoreCase(degree);
    }
    @Override
    public List<Student> findByName(String name){
        return studentRepo.findByFirstName(name);
    }
    @Override
    public List<AggregationResults> performAggregation() {
        Aggregation aggregation = newAggregation(
                Aggregation.group("degree").count().as("totalStudents"),
                Aggregation.project("totalStudents").and("id").as("degree")
        );

        AggregationResults<AggregationResults> results = mongoTemplate.aggregate(
                aggregation, "students", AggregationResults.class
        );
        return results.getMappedResults();
    }
   /* public Map<String, Object> getByDegree(String[] degrees){
        Map<String,Object> response = new HashMap<>();
        List<Student> studentList = studentRepo.findByDegreeWithNumbers(degrees);
        response.put("data",studentList);
        response.put("Number of books", studentList.size());
        return response;
    }*/
   @Override
    public long countStudent(String degree){
        return studentRepo.countStudentsByDegree(degree);
    }
    @Override
    public List<Student> findByUniAndDegree(String uni, String degree){
       return studentRepo.findByUniAndDegree(uni,degree);
    }
    @Override
    public long countStudentsByDegree(String degree) {
        Aggregation aggregation = newAggregation(
                match(where("degree").is(degree)),
                count().as("totalCount")
        );

        AggregationResults<CountResult> results = mongoTemplate.aggregate(aggregation, "boys", CountResult.class);
        CountResult countResult = results.getUniqueMappedResult();
        return countResult != null ? countResult.getTotalCount() : 0;
    }

        @Override
        public double getAverageAge() {
            Aggregation aggregation = newAggregation(
                    group().avg("age").as("averageAge")
            );

            AggregationResults<AverageAgeResult> results = mongoTemplate.aggregate(aggregation, "boys", AverageAgeResult.class);
            AverageAgeResult averageAgeResult = results.getUniqueMappedResult();

            return averageAgeResult != null ? averageAgeResult.getAverageAge() : 0.0;
        }

}
