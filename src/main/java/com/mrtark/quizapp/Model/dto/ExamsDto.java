package com.mrtark.quizapp.Model.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
@Getter
@Setter
public class ExamsDto {
    @NotEmpty(message = "Sınavı Olacak Dersin Adı Boş Geçilemez!")
    private String courseName;
    @NotEmpty(message = "Sınavı Olacak Dersin Sınıf Bilgisi Boş Geçilemez!")
    private String examclass;
    @NotEmpty(message = "Dersin Sınav Tarihi Bilgisi Boş Geçilemez!")
    private String courseExamDate;
}
