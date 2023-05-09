package com.mrtark.quizapp.Data;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

//@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity implements Serializable {
    public static final Long serialVersionUID=1L;
    @Column(nullable = false)
    private String studentNumber;
    private String name;
    private String surName;
    private String department;
    private String email;
    @Column(nullable = false)
    private String password;
}
