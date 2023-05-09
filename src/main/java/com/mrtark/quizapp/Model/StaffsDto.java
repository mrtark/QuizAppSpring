package com.mrtark.quizapp.Model;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
@Getter
@Setter

public class StaffsDto {
    private Long id;
    @NotEmpty(message = "Öğretmen Adı Boş Geçilemez!")
    private String name;
    @NotEmpty(message = "Öğretmen Soyadı Boş Geçilemez!")
    private String surName;
    @NotEmpty(message = "Öğretmen Telefon Numarası Boş Geçilemez!")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", message = "Uygun Formatta Telefon Numarası Giriniz!")
    private String telephoneNumber;
    @NotEmpty(message = "Bölüm Bilgisi Boş Geçilemez!")
    private String department;
    @NotEmpty(message = "Email Bilgisi Boş Geçilemez!")
    @Email(message = "Uygun Formatta Email Girilmedi!")
    @Size(max = 150,message = "Email Bilgisi 150 Karakterden Fazla Olamaz!")
    private String email;
    @NotEmpty(message = "Bir Şifre Belirlemelisiniz!")
    private String password;

}
