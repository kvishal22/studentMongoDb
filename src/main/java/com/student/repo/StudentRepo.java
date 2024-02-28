package com.student.repo;

import com.student.entity.Student;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student,Integer> {
    List<Student> findByAgeGreaterThan(int age);
    Optional<Student> findByEmail(String email);
    List<Student> findByDegreeIgnoreCase(String degree);
    @Query("{'firstName': {$regex: ?0, $options: 'i'}}")
    List<Student> findByFirstName(String firstName);

    @Query("{'degree' : ?0}")
    long countStudentsByDegree(String degree);
    @Query("{'uni':?0,'degree':?1}")
    List<Student> findByUniAndDegree(String uni,String degree);

    @Aggregation(pipeline = {"{$match:{'degree': ?0}}", "{$count: 'totalCount'}"})
    List<Student> getTotalStudentsByDegree(String degree);
}
