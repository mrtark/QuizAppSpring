package com.mrtark.quizapp.Controllers;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Data.StaffEntity;
import com.mrtark.quizapp.Data.StudentEntity;
import com.mrtark.quizapp.Model.AdminsDto;
import com.mrtark.quizapp.Model.StudentsDto;
import com.mrtark.quizapp.Repository.IAdminRepository;
import com.mrtark.quizapp.Repository.IStaffRepository;
import com.mrtark.quizapp.Repository.IStudentRepository;
import com.mrtark.quizapp.Services.AdminService;
import com.mrtark.quizapp.Services.AdminServiceImp;
import com.mrtark.quizapp.Services.StudentServiceImp;
import com.mrtark.quizapp.bean.ModelMapperBean;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2

@Controller
@RequestMapping("admin")
public class AdminsController {
    private final IAdminRepository iAdminRepository;
    private final IStudentRepository iStudentRepository;
    private final IStaffRepository iStaffRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    private final AdminServiceImp adminServiceImp;
    private final StudentServiceImp studentServiceImp;
//    private final ExamsServiceImp examsServiceImp;

    @GetMapping("giriskpA")
    public String adminGiris(HttpSession session, Model model){
        return "kpA";
    }

    //database create tests
    @GetMapping("testA")
    public String createAdminTest() {
        for (int i = 0; i < 1; i++) {
            AdminEntity adminTest = AdminEntity.builder()
                    .username("root")
                    .email("root@rootmail.com")
                    .password("root1")
                    .build();
            iAdminRepository.save(adminTest);
        }
        return "redirect:/admin/giris";
    }
    @GetMapping("testP")
    public String createStaffTest() {
        for (int i = 0; i < 1; i++) {
            StaffEntity staffEntity = StaffEntity.builder()
                    .name("Celal")
                    .surName("Şengör")
                    .telephoneNumber("+91 (123) 456-7890")
                    .department("Jeoloji mühendisliği")
                    .email("sengor@itu.edu.tr")
                    .password("1234")
                    .build();
            iStaffRepository.save(staffEntity);
        }
        return "redirect:/admin/giris";
    }

    //end database create tests

    //login
    @GetMapping("")
    public String adminHome(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("admin")== null){
            return "redirect:/admin/giris";
        }else {
            model.addAttribute("admin",httpSession.getAttribute("admin"));
            model.addAttribute("ogrenciler",studentServiceImp.getStudentList());
            model.addAttribute("sinavlar");
            return "admin";
        }
    }
    @GetMapping("giris")
    public String signin(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("admin")!=null){
            model.addAttribute("admin",httpSession.getAttribute("admin"));
            return "redirect:/admin";
        }else{
            return "kpA";
        }
    }
    @PostMapping({"/giris"})
    public String signinPost(@Valid @ModelAttribute AdminEntity admin, Model model, HttpSession session, BindingResult bindingResult) {
        AdminEntity user = adminServiceImp.searchUsername(admin.getUsername());
        if (bindingResult.hasErrors())
            log.error(bindingResult.hasErrors() + " :------------------");
        if(user == null) {
            return "redirect:/admin/giris?kullacici=bulunamadi";
        }
        if(!(user.getPassword().equals(admin.getPassword()))) {
            return "redirect:/admin/giris?sifre=yanlis";
        }
        session.setAttribute("admin", user);
        model.addAttribute("admin", user);
        return "redirect:/admin";
    }
    @GetMapping("cikis")
    public String cikis(HttpSession session, Model model){
        session.removeAttribute("admin");
        return "redirect:/admin/giris";
    }

    //end login

    @GetMapping("ogrenciler")
    public String getAllStudentList(Model model) {
        List<StudentEntity> studentEntityList = iStudentRepository.findAll();
        model.addAttribute("student_list_key", studentEntityList);
        return "adminOgr";
    }



    @GetMapping("personeller")
    public String getAllStaff(Model model) {
        List<StaffEntity> staffEntities = iStaffRepository.findAll();
        model.addAttribute("staff_list_key", staffEntities);
        return "adminPsnl";
    }

    @GetMapping("bölümler")
    public String getAllDepartment(Model model) {

        return "adminBlm";
    }

    @GetMapping("sinavlar")
    public String getAllExams(Model model) {

        return "adminSnv";
    }

}
