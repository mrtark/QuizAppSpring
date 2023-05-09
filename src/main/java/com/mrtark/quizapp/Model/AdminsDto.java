package com.mrtark.quizapp.Model;

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
public class AdminsDto {
    private Long id;
    @NotEmpty(message = "Kullanıcı Adı Bilgisi Boş Geçilemez!")
    private String userName;
    @NotEmpty(message = "Email Bilgisi Boş Geçilemez!")
    @Email(message = "Uygun Formatta Email Girilmedi!")
    @Size(max = 150,message = "Email Bilgisi 150 Karakterden Fazla Olamaz!")
    private String email;
    @NotEmpty(message = "Bir Şifre Belirlemelisiniz!")
    private String password;
}
