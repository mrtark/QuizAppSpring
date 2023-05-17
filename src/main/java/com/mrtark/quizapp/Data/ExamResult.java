package com.mrtark.quizapp.Data;

import lombok.*;
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
@Table(name = "examResult")
public class ExamResult extends BaseEntity implements Serializable {
    public static final Long serialVersionUID=1L;
    private int examId;
    private String studentNumber;
    private int totalCorrect = 0;
}
