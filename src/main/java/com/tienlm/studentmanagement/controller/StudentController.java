package com.tienlm.studentmanagement.controller;

import com.tienlm.studentmanagement.model.Student;
import com.tienlm.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    private
    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        List<Student> students = this.studentRepository.findAll();

        if(students.isEmpty()) {
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<?> createNew(@RequestBody Student student){
        this.studentRepository.save(student);

        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        Optional<Student> student = this.studentRepository.findById(id);

        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Student currentStudent){
        Optional<Student> student = this.studentRepository.findById(id);

        this.studentRepository.save(currentStudent);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") String id, @RequestBody Student currentStudent){
        Optional<Student> student = this.studentRepository.findById(id);

        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.studentRepository.delete(student.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
