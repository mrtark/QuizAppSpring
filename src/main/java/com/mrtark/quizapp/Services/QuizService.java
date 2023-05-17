package com.mrtark.quizapp.Services;

import com.mrtark.quizapp.Data.ExamResult;
import com.mrtark.quizapp.Data.Question;
import com.mrtark.quizapp.Data.QuestionForm;
import com.mrtark.quizapp.Repository.IExamResultRepository;
import com.mrtark.quizapp.Repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class QuizService {

    private final Question question;
    private final QuestionForm qForm;
    private final IQuestionRepository iQuestionRepository;
    private final ExamResult result;
    private final IExamResultRepository iExamResultRepository;



    public QuestionForm getQuestions() {
        List<Question> allQues = iQuestionRepository.findAll();
        List<Question> qList = new ArrayList<Question>();

        Random random = new Random();
        //data.sql dosyamızdaki soru havuzumuzdan *random şekilde soruları *belirli adette getirtebiliriz.
        for(int i=0; i<20; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        qForm.setQuestions(qList);

        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for(Question q: qForm.getQuestions())
            if(question.getAns() == q.getChose())
                correct++;

        return correct;
    }

    public void saveScore(ExamResult result) {
        ExamResult saveResult = new ExamResult();
        saveResult.setStudentNumber(result.getStudentNumber());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        iExamResultRepository.save(saveResult);
    }

    public List<ExamResult> getTopScore() {
        List<ExamResult> sList = iExamResultRepository.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        return sList;
    }
}
