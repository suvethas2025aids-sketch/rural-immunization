package com.clinic.ruralimmunization.repository;

import com.clinic.ruralimmunization.entity.DosageSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DosageScheduleRepository
        extends JpaRepository<DosageSchedule, Long> {
}