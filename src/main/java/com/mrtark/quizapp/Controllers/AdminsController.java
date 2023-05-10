package com.mrtark.quizapp.Controllers;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Repository.IAdminRepository;
import com.mrtark.quizapp.Services.AdminService;
import com.mrtark.quizapp.Services.AdminServiceImp;
import com.mrtark.quizapp.bean.ModelMapperBean;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2

@Controller
@RequestMapping("admin")
public class AdminsController {
    private final IAdminRepository iAdminRepository;
    private final ModelMapperBean modelMapperBean;
    private final AdminService adminService;
    private final PasswordEncoderBean passwordEncoderBean;

    @GetMapping("giris")
    public String adminGiris(HttpSession session, Model model){
        return "kpA";
    }

    //database create test
    @GetMapping("test")
    public String createAdminTest(Model model) {
        for (int i = 0; i < 5; i++) {
            AdminEntity adminTest = AdminEntity.builder()
                    .userName("root" + i)
                    .email(i +"root@rootmail.com")
                    .password(passwordEncoderBean.passwordEncoderBeanMethod().encode(i + "root_123456789"))
                    .build();
            iAdminRepository.save(adminTest);
        }
        return "redirect:index";
    }
}
