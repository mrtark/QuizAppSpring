package com.mrtark.quizapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("ogrenci")
public class StudentController {
    @GetMapping("giris")
    public String ogrenciGiris(HttpSession session, Model model){
        return "kpOgr";
    }
}
