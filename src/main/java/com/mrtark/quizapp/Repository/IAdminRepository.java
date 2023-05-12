package com.mrtark.quizapp.Repository;

import com.mrtark.quizapp.Data.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<AdminEntity,Long> {
    AdminEntity findByEmail(String email);
    AdminEntity findByUsername(String username);
}
