package com.mrtark.quizapp.Data;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "staffs")
public class StaffEntity extends BaseEntity implements Serializable {
    public static final Long serialVersionUID=1L;
    private String name;
    private String surName;
    private String telephoneNumber;
    private String department;
    private String email;
    @Column(nullable = false)
    private String password;
}
