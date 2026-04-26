package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Transactional(readOnly = true)
    public List<StudentCourse> getAllStudentsWithCourses() {
        return studentCourseRepository.findAllWithStudentAndCourse();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        List<Student> students = studentRepository.findAll();
        Student highestGpaStudent = null;
        double highestGpa = 0.0;
        for (Student student : students) {
            if (student.getGpa() > highestGpa) {
                highestGpa = student.getGpa();
                highestGpaStudent = student;
            }
        }
        return Optional.ofNullable(highestGpaStudent);
    }

    @Transactional(readOnly = true)
    public String joinStudentNames() {
        List<String> studentNames = studentRepository.findAllNames();
        return String.join(", ", studentNames);
    }
}

