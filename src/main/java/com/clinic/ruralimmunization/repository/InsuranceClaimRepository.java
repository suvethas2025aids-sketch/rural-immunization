package com.clinic.ruralimmunization.repository;

import com.clinic.ruralimmunization.entity.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceClaimRepository
        extends JpaRepository<InsuranceClaim, Long> {
}
