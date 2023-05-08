package com.mrtark.quizapp.Model.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
@Getter
@Setter
public class StudentsDto {
    private Long id;
    @NotEmpty(message = "Okul Numarası Boş Geçilemez!")
    private String studentNumber;
    @NotEmpty(message = "Öğrenci Adı Boş Geçilemez!")
    private String name;
    @NotEmpty(message = "Öğrenci Soyadı Boş Geçilemez!")
    private String surName;
    @NotEmpty(message = "Bölüm Bilgisi Boş Geçilemez!")
    private String department;
    @NotEmpty(message = "Email Bilgisi Boş Geçilemez!")
    @Email(message = "Uygun Formatta Email Girilmedi!")
    @Size(max = 150,message = "Email Bilgisi 150 Karakterden Fazla Olamaz!")
    private String email;
    @NotEmpty(message = "Bir Şifre Belirlemelisiniz!")
    private String password;



}
