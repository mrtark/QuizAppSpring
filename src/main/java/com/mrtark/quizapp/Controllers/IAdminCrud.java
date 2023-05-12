package com.mrtark.quizapp.Controllers;

import com.mrtark.quizapp.Model.StudentsDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IAdminCrud {
    String createPostStudent(StudentsDto studentsDto, BindingResult bindingResult, Model model);
    String createGetStudent(Model model);

    String deleteByIdStudent(Long id, Model model);
}
