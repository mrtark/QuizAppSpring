package com.mrtark.quizapp.Repository;

import com.mrtark.quizapp.Data.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<StaffEntity,Long> {
}
