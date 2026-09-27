package com.clinic.ruralimmunization.repository;

import com.clinic.ruralimmunization.entity.ClinicBudget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicBudgetRepository
        extends JpaRepository<ClinicBudget, Long> {
}