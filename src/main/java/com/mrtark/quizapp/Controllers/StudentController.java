package com.mrtark.quizapp.Controllers;

import com.mrtark.quizapp.Data.ExamResult;
import com.mrtark.quizapp.Data.QuestionForm;
import com.mrtark.quizapp.Data.StudentEntity;
import com.mrtark.quizapp.Model.StudentsDto;
import com.mrtark.quizapp.Repository.IExamResultRepository;
import com.mrtark.quizapp.Repository.IStudentRepository;
import com.mrtark.quizapp.Services.QuizService;
import com.mrtark.quizapp.Services.StudentServiceImp;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("ogrenci")
public class StudentController {
    private final IStudentRepository iStudentRepository;
    private final PasswordEncoderBean passwordEncoderBean;
    private final StudentServiceImp studentServiceImp;
    private final ExamResult examResult;
    private final QuizService quizService;

    @GetMapping("test")
    public String createStudentTest() {
        for (int i = 0; i < 1; i++) {
            StudentEntity studentTest = StudentEntity.builder()
                    .studentNumber("1234567890")
                    .name("Recep")
                    .surName("Gül")
                    .department(i + "İ.İ.B.F.")
                    .email(i + "reco_25@gmail.com")
                    .password("1234q")
                    .build();
            iStudentRepository.save(studentTest);
        }
        return "redirect:/ogrenci/giris";
    }

    @GetMapping({""})
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("ogrenci") == null) {
            return "redirect:/ogrenci/giris";
        } else {
//            model.addAttribute("sinavlar", examsService.getAllExams());
            return "ogrenci";
        }
    }

    @GetMapping({"giris"})
    public String giris(HttpSession session, Model model) {
        if (session.getAttribute("ogrenci") != null) {
            model.addAttribute("ogrenci", session.getAttribute("ogrenci"));
            return "redirect:/ogrenci";
        } else {
            return "kpOgr";
        }
    }

    @PostMapping({"/giris"})
    public String signinPost(@Valid @ModelAttribute StudentEntity student, Model model, HttpSession session, BindingResult bindingResult) {
        StudentEntity user = studentServiceImp.searchStudentNumber(student.getStudentNumber());
        if (bindingResult.hasErrors())
            log.error(bindingResult.hasErrors() + " :------------------");
        if (user == null) {
            return "redirect:/ogrenci/giris?kullacici=bulunamadi";
        }
        if (!(user.getPassword().equals(student.getPassword()))) {
            return "redirect:/ogrenci/giris?sifre=yanlis";
        }
        session.setAttribute("ogrenci", user);
        model.addAttribute("ogrenci", user);
        return "redirect:/ogrenci";

    }

    @GetMapping("sinavlar")
    public String getExams() {
        return "ogrenciSnvL";
    }

    @GetMapping("cikis")
    public String cikis(HttpSession session, Model model) {
        session.removeAttribute("ogrenci");
        return "redirect:/ogrenci/giris";
    }

    @PostMapping("/sinav")
    public String quiz(@RequestParam String studentNumber, Model model, RedirectAttributes ra, HttpSession httpSession) {

        StudentEntity user = studentServiceImp.searchStudentNumber(studentNumber);

        if (user != null){
            if (studentNumber.equals("")) {
                ra.addFlashAttribute("bilgi", "Öğrenci Numaranızı Girmeniz Gerekmektedir!");
                return "redirect:/ogrenci/sinavlar?ogrenciNo=girilmedi";
            } else if (user.equals(httpSession.getAttribute("ogrenci"))) {
                System.out.println("oturum açan öğrenci no ile sınava giren öğrenci no eşleşti");
                submitted = false;
                examResult.setStudentNumber(studentNumber.toString());

                QuestionForm qForm = quizService.getQuestions();
                model.addAttribute("qForm", qForm);

                return "sinav";
            }
        }
        return "redirect:/ogrenci/sinavlar?OturumAcanOgrenciNo=null";
    }


    Boolean submitted = false;

    @PostMapping("submit")
    public String submit(@ModelAttribute QuestionForm qForm) {
        if (!submitted) {
            examResult.setTotalCorrect(quizService.getResult(qForm));
            quizService.saveScore(examResult);
            submitted = true;
        }

        return "redirect:/ogrenci";
    }

//    @ModelAttribute("hızlısonuc")
//    public ExamResult getResult() {
//        return examResult;
//    }

}
