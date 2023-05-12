package com.mrtark.quizapp.Services;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Model.AdminsDto;
import java.util.List;
import java.util.Map;

public interface AdminServiceImp {
    public AdminsDto EntitytoDto(AdminEntity adminEntity);
    public AdminEntity DtoToEntity(AdminsDto adminsDto);
    public AdminsDto createAdmin(AdminsDto adminsDto);
    public List<AdminsDto> getAdminList();
    public AdminsDto searchEmail(String email);
    public AdminEntity searchUsername(String username);
    public AdminsDto findAdminById(Long id);
    public void deleteAll();
    public Map<String,Boolean> deleteAdmin(Long id);


}
