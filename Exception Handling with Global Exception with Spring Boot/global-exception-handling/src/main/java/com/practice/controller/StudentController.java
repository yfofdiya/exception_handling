package com.practice.controller;

import com.practice.entity.Student;
import com.practice.service.StudentService;
import com.practice.util.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student stu) {
        return new ResponseEntity<Student>(studentService.addStudent(stu), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        return new ResponseEntity<Student>(studentService.getStudent(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        SuccessResponse successResponse = new SuccessResponse("Data is deleted for provided id : " + id);
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student stu) {
        return new ResponseEntity<Student>(studentService.updateStudent(id, stu), HttpStatus.CREATED);
    }
}
