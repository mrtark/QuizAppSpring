package com.mrtark.quizapp.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

@Component
@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quesId;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private int ans;
    private int chose;

    @Override
    public String toString() {
        return "Sorular{" +
                "Soru ID =" + quesId +
                ", Başlık ='" + title + '\'' +
                ", Seçenek A ='" + optionA + '\'' +
                ", Seçenek B ='" + optionB + '\'' +
                ", Seçenek C ='" + optionC + '\'' +
                ", Seçenek D ='" + optionD + '\'' +
                ", Seçenek E ='" + optionE + '\'' +
                ", Doğru Seçenek =" + ans +
                ", Kullanıcı Seçeneği =" + chose +
                '}';
    }

}
