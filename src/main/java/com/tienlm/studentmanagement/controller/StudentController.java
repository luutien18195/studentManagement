package com.tienlm.studentmanagement.controller;

import com.tienlm.studentmanagement.model.Student;
import com.tienlm.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(value = "/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> getAll(){
        List<Student> students = this.studentRepository.findAll();

        if(students.isEmpty()) {
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value = "/students")
    @PreAuthorize("hasAuthority('student:create')")
    public ResponseEntity<?> createNew(@RequestBody Student student){
        this.studentRepository.save(student);

        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/students/{id}")
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        Optional<Student> student = this.studentRepository.findById(id);

        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/students/{id}")
    @PreAuthorize("hasAuthority('student:update')")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Student currentStudent){
        this.studentRepository.save(currentStudent);

        Optional<Student> student = this.studentRepository.findById(id);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{id}")
    @PreAuthorize("hasAuthority('student:delete')")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id, @RequestBody Student currentStudent){
        this.studentRepository.delete(currentStudent);
        Optional<Student> student = this.studentRepository.findById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
