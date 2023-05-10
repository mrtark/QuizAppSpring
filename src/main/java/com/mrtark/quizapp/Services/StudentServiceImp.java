package com.mrtark.quizapp.Services;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Data.StudentEntity;
import com.mrtark.quizapp.Model.AdminsDto;
import com.mrtark.quizapp.Model.StudentsDto;

import java.util.List;
import java.util.Map;

public interface StudentServiceImp {
    public StudentsDto EntitytoDto(StudentEntity studentEntity);
    public StudentEntity DtoToEntity(StudentsDto studentsDto);

    public StudentsDto createStudent(StudentsDto studentsDto);
    public List<StudentsDto> getStudentList();
    public StudentsDto updateStudent(Long id, StudentsDto studentsDto);
    public StudentsDto findStudentById(Long id);
    public Map<String,Boolean> deleteStudent(Long id);

    public StudentsDto searchStudentNumber(String studentNumber);
}
