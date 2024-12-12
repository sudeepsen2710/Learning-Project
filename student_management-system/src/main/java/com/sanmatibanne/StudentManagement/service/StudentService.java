package com.sanmatibanne.StudentManagement.service;


import com.sanmatibanne.StudentManagement.dao.StudentDto;

import java.util.List;

public interface StudentService {

    void createStudent(StudentDto studentDto);

    List<StudentDto> getStudents();

    StudentDto getStudentById(Long id);
//
    void deleteStudent(Long id);
//
    void updateStudentData(StudentDto studentDto);
}
