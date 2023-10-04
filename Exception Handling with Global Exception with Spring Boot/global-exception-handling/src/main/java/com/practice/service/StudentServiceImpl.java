package com.practice.service;

import com.practice.entity.Student;
import com.practice.exception.BlankFieldException;
import com.practice.exception.DataNotFoundException;
import com.practice.exception.EmptyListException;
import com.practice.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student stu) {
        if (StringUtils.isBlank(stu.getName()) || StringUtils.isBlank(stu.getAddress())) {
            throw new BlankFieldException("Input field(s) are blank");
        }
        Student addedStudent = studentRepository.save(stu);
        return addedStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        if (allStudents.isEmpty()) {
            throw new EmptyListException("Table has no data");
        }
        return allStudents;
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new DataNotFoundException("No data found for provided id : " + id);
        }
        return student.get();
    }

    @Override
    public void deleteStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new DataNotFoundException("No data found for provided id : " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(int id, Student stu) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new DataNotFoundException("No data found for provided id : " + id);
        }
        if (StringUtils.isBlank(stu.getName()) || StringUtils.isBlank(stu.getAddress())) {
            throw new BlankFieldException("Input field(s) are blank");
        }
        Student oldStudent = student.get();
        oldStudent.setName(stu.getName());
        oldStudent.setAddress(stu.getAddress());
        Student updatedStudent = oldStudent;
        studentRepository.save(updatedStudent);
        return updatedStudent;
    }
}
