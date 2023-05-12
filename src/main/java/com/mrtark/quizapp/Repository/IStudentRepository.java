package com.mrtark.quizapp.Repository;

import com.mrtark.quizapp.Data.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity,Long> {

    StudentEntity findByStudentNumber(String studentNumber);
}
