package com.mrtark.quizapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminsController {
//    @GetMapping("1")
//    @ResponseBody
//    public String test(){
//        return "Hello Admin";
//    }

    @GetMapping("giris")
    public String adminGiris(HttpSession session, Model model){
        return "kpA";
    }
}
