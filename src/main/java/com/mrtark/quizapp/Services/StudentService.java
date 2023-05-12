package com.mrtark.quizapp.Services;


import com.mrtark.quizapp.Data.StudentEntity;
import com.mrtark.quizapp.Model.StudentsDto;
import com.mrtark.quizapp.Repository.IStudentRepository;
import com.mrtark.quizapp.bean.ModelMapperBean;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class StudentService implements StudentServiceImp{
    private final IStudentRepository iStudentRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    @Autowired
    IStudentRepository studentRepositoryAut;

    @Override
    public StudentsDto EntitytoDto(StudentEntity studentEntity) {
        return modelMapperBean.modelMapperMethod().map(studentEntity, StudentsDto.class);
    }

    @Override
    public StudentEntity DtoToEntity(StudentsDto studentsDto) {
        return modelMapperBean.modelMapperMethod().map(studentsDto, StudentEntity.class);
    }

    @Override
    public StudentsDto createStudent(StudentsDto studentsDto) {
        studentsDto.setPassword(passwordEncoderBean.passwordEncoderBeanMethod().encode(studentsDto.getPassword()));
        iStudentRepository.save(DtoToEntity(studentsDto));
        return studentsDto;
    }

    @Override
    public List<StudentsDto> getStudentList() {
        List<StudentsDto> studentsDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = iStudentRepository.findAll();
        for (StudentEntity studentEntityFnd:studentEntityList) {
            StudentsDto studentsDto = EntitytoDto(studentEntityFnd);
            studentsDtoList.add(studentsDto);
        }
        return studentsDtoList;
    }

    @Override
    public StudentsDto updateStudent(Long id, StudentsDto studentsDto) {
        StudentsDto dto = findStudentById(id);
        if (dto!=null){
            dto.setStudentNumber(dto.getStudentNumber());
            dto.setName(dto.getName());
            dto.setSurName(dto.getSurName());
            dto.setDepartment(dto.getDepartment());
            dto.setEmail(dto.getEmail());
            dto.setPassword(passwordEncoderBean.passwordEncoderBeanMethod().encode(dto.getPassword()));
            StudentEntity studentEntity = iStudentRepository.save(DtoToEntity(dto));
            iStudentRepository.save(studentEntity);
            dto.setId(studentEntity.getId());
        }
        return null;
    }

    @Override
    public StudentsDto findStudentById(Long id) {
        Optional<StudentEntity> findStudentByIdE = iStudentRepository.findById(id);
        StudentsDto studentsDto = EntitytoDto(findStudentByIdE.get());
        if (findStudentByIdE.isPresent())
            return studentsDto;
        else System.out.println(findStudentByIdE + ": ID'ye ait veri bulunamadı.");
        return null;
    }
    @Override
    public Map<String, Boolean> deleteStudent(Long id) {
        StudentsDto studentsDtoo = findStudentById(id);
        Map<String,Boolean> studentDelete = new LinkedHashMap<>();
        if (studentsDtoo!=null){
            iStudentRepository.delete(DtoToEntity(studentsDtoo));
            studentDelete.put(studentsDtoo + ": Data Silindi.",Boolean.TRUE);
        }
        return studentDelete;
    }

    @Override
    public StudentEntity searchStudentNumber(String studentNumber) {
        StudentEntity studentEntity = iStudentRepository.findByStudentNumber(studentNumber);
        return studentEntity;
    }
}
