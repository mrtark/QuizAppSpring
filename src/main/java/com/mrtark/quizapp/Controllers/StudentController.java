package com.mrtark.quizapp.Controllers;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Data.StudentEntity;
import com.mrtark.quizapp.Repository.IStudentRepository;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("ogrenci")
public class StudentController {
    private final IStudentRepository iStudentRepository;
    private final PasswordEncoderBean passwordEncoderBean;

    @GetMapping("giris")
    public String ogrenciGiris(HttpSession session, Model model){
        return "kpOgr";
    }
    @GetMapping("test")
    public String createStudentTest(Model model){
        for (int i = 0; i < 5; i++) {
            StudentEntity studentTest = StudentEntity.builder()
                    .studentNumber("4951978789966")
                    .name("Murat")
                    .surName("Arık")
                    .department(i + "İ.İ.B.F.")
                    .email(i + "mrtark@gmail.com")
                    .password(passwordEncoderBean.passwordEncoderBeanMethod().encode(i + "1598741m"))
                    .build();
            iStudentRepository.save(studentTest);
        }
        return "redirect:index";
    }
}
