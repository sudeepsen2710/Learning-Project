package com.sanmatibanne.StudentManagement.service.impl;

import com.sanmatibanne.StudentManagement.dao.StudentDto;
import com.sanmatibanne.StudentManagement.entity.Student;
import com.sanmatibanne.StudentManagement.mapper.StudentMapper;
import com.sanmatibanne.StudentManagement.repository.StudentRepository;
import com.sanmatibanne.StudentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository repository;

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student=StudentMapper.mapToStudent(studentDto);
        repository.save(student);

    }

    @Override
    public List<StudentDto>  getStudents() {
       List< Student> studentList = repository.findAll();
       List<StudentDto> studentDtosList=
               studentList.stream().map((student) -> StudentMapper.mapToStudentDto(student)).
                       collect(Collectors.toList());
        return studentDtosList;
    }

    @Override
    public StudentDto getStudentById(Long id) {
       Student student =repository.findById(id).get();
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void updateStudentData(StudentDto studentDto) {
        repository.saveAndFlush(StudentMapper.mapToStudent(studentDto));

    }
}
