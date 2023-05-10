package com.mrtark.quizapp.Services;

import com.mrtark.quizapp.Data.AdminEntity;
import com.mrtark.quizapp.Model.AdminsDto;
import com.mrtark.quizapp.Repository.IAdminRepository;
import com.mrtark.quizapp.bean.ModelMapperBean;
import com.mrtark.quizapp.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class AdminService implements AdminServiceImp{
    private final IAdminRepository iAdminRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    @Autowired
    IAdminRepository AdminRepositoryAut;

    @Override
    public AdminsDto EntitytoDto(AdminEntity adminEntity) {
        return modelMapperBean.modelMapperMethod().map(adminEntity, AdminsDto.class);
    }

    @Override
    public AdminEntity DtoToEntity(AdminsDto adminsDto) {
        return modelMapperBean.modelMapperMethod().map(adminsDto, AdminEntity.class);
    }

    @Override
    public AdminsDto createAdmin(AdminsDto adminsDto) {
        adminsDto.setPassword(passwordEncoderBean.passwordEncoderBeanMethod().encode(adminsDto.getPassword()));
        iAdminRepository.save(DtoToEntity(adminsDto));
        return adminsDto;
    }

    @Override
    public List<AdminsDto> getAdminList() {
        List<AdminsDto> adminsDtoList = new ArrayList<>();
        List<AdminEntity> adminEntityList = iAdminRepository.findAll();
        for (AdminEntity adminEntityfnd:adminEntityList) {
            AdminsDto adminsDto = EntitytoDto(adminEntityfnd);
            adminsDtoList.add(adminsDto);
        }
        return adminsDtoList;
    }

    @Override
    public AdminsDto searchEmail(String email) {
        return EntitytoDto(iAdminRepository.findByEmail(email));
    }

    @Override
    public AdminsDto findAdminById(Long id) {
        Optional<AdminEntity> findAdminByIdE = iAdminRepository.findById(id);
        AdminsDto adminDto = EntitytoDto(findAdminByIdE.get());
        if (findAdminByIdE.isPresent())
            return adminDto;
        else System.out.println(findAdminByIdE + ": ID'ye ait veri bulunamadı.");
        return null;
    }

    @Override
    public void deleteAll() {
        iAdminRepository.deleteAll();
    }

    @Override
    public Map<String, Boolean> deleteAdmin(Long id) {
        AdminsDto adminDtoI = findAdminById(id);
        Map<String,Boolean> adminDeleted = new LinkedHashMap<>();
        if (adminDtoI!=null){
            iAdminRepository.delete(DtoToEntity(adminDtoI));
            adminDeleted.put(adminDtoI + " Verisi Silindi.",Boolean.TRUE);
        }
        return adminDeleted;
    }


}
