package com.tienlm.studentmanagement.repository;

import com.tienlm.studentmanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
