package com.mrtark.quizapp.bean;

import com.mrtark.quizapp.Model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBean {
    @Bean
    public AdminsDto adminsDto(){
        return AdminsDto.builder()
                .id(1L)
                .username("ROOT")
                .email("root@edu.admin.com")
                .password("root")
                .build();
    }
    @Bean
    public StaffsDto staffDto(){
        return StaffsDto.builder()
                .id(1L)
                .name("Ömer Faruk")
                .surName("İşcan")
                .telephoneNumber("+91 (123) 456-7890")
                .email("omerfrk25@gmail.com")
                .department("İ.İ.B.F.")
                .password("omr1234")
                .build();
    }
    @Bean
    public StudentsDto studentDto(){
        return StudentsDto.builder()
                .id(1L)
                .studentNumber("080520231723")
                .name("Mehmet")
                .surName("Saklıoğlu")
                .email("mhmtskl@hotmail.com")
                .password("mhmt1234")
                .department("İ.İ.B.F.")
                .build();
    }
    @Bean
    public ExamsDto examsDto(){
        return ExamsDto.builder()
                .courseName("Yöneylem")
                .examclass("A3")
                .courseExamDate("08.05.2023")
                .build();
    }

    public static void main(String[] args) {
        DataBean dataBean = new DataBean();
        System.out.println(dataBean.staffDto() + "\n" +
                            dataBean.studentDto());
    }
}
