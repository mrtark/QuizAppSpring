package com.mrtark.quizapp.Repository;

import com.mrtark.quizapp.Data.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamResultRepository extends JpaRepository<ExamResult,Long> {
}
