package com.clinic.ruralimmunization.repository;
import com.clinic.ruralimmunization.entity.VaccineBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VaccineBatchRepository
        extends JpaRepository<VaccineBatch, Long> {

    boolean existsByBatchNumber(String batchNumber);

    List<VaccineBatch> findByExpiryDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}