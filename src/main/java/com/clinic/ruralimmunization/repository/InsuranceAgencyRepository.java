package com.clinic.ruralimmunization.repository;
import com.clinic.ruralimmunization.entity.InsuranceAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InsuranceAgencyRepository
        extends JpaRepository<InsuranceAgency, Long> {
}