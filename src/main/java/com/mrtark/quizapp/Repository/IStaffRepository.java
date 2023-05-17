package com.mrtark.quizapp.Repository;

import com.mrtark.quizapp.Data.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStaffRepository extends JpaRepository<StaffEntity,Long> {
}
