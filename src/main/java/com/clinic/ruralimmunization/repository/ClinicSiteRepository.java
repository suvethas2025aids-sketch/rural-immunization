package com.clinic.ruralimmunization.repository;

import com.clinic.ruralimmunization.entity.ClinicSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicSiteRepository
        extends JpaRepository<ClinicSite, Long> {
}