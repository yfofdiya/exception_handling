package com.practice.service;

import com.practice.entity.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student stu);

    List<Student> getAllStudents();

    Student getStudent(int id);

    void deleteStudent(int id);

    Student updateStudent(int id, Student std);

}
