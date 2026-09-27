package com.clinic.ruralimmunization.service;
import com.clinic.ruralimmunization.dto.AdministerRequest;
import com.clinic.ruralimmunization.entity.Supplier;
import com.clinic.ruralimmunization.entity.VaccineBatch;
import com.clinic.ruralimmunization.repository.SupplierRepository;
import com.clinic.ruralimmunization.repository.VaccineBatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineBatchService {

    private final VaccineBatchRepository batchRepository;
    private final SupplierRepository supplierRepository;

    public VaccineBatchService(
            VaccineBatchRepository batchRepository,
            SupplierRepository supplierRepository) {
        this.batchRepository = batchRepository;
        this.supplierRepository = supplierRepository;
    }

    // Register a new vaccine batch
    public VaccineBatch createBatch(VaccineBatch batch) {

        if (batch.getQuantity() == null || batch.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        if (batch.getExpiryDate() == null ||
                !batch.getExpiryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Expiry date must be in the future");
        }

        if (batch.getBatchNumber() == null ||
                batch.getBatchNumber().isBlank()) {
            throw new IllegalArgumentException(
                    "Batch number is required");
        }

        if (batchRepository.existsByBatchNumber(batch.getBatchNumber())) {
            throw new IllegalArgumentException(
                    "Batch number already exists");
        }

        if (batch.getSupplier() == null ||
                batch.getSupplier().getId() == null) {
            throw new IllegalArgumentException(
                    "Supplier ID is required");
        }

        Supplier supplier = supplierRepository
                .findById(batch.getSupplier().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Supplier not found"));

        batch.setSupplier(supplier);
        return batchRepository.save(batch);
    }

    // Get all batches
    public List<VaccineBatch> getAllBatches() {
        return batchRepository.findAll();
    }

    // Get batches expiring within the specified number of days
    public List<VaccineBatch> getExpiringBatches(int withinDays) {

        if (withinDays < 1) {
            throw new IllegalArgumentException(
                    "withinDays must be at least 1");
        }

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(withinDays);

        return batchRepository.findByExpiryDateBetween(today, endDate);
    }

    // Record administration and reduce available stock
    @Transactional
    public VaccineBatch administerDoses(AdministerRequest request) {

        if (request == null ||
                request.getBatchId() == null ||
                request.getDoses() == null ||
                request.getDoses() <= 0) {
            throw new IllegalArgumentException(
                    "Valid batch ID and positive dose count are required");
        }

        VaccineBatch batch = batchRepository
                .findById(request.getBatchId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vaccine batch not found"));

        if (batch.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Cannot administer an expired vaccine batch");
        }

        if (batch.getQuantity() < request.getDoses()) {
            throw new IllegalArgumentException(
                    "Not enough doses available in this batch");
        }

        batch.setQuantity(batch.getQuantity() - request.getDoses());

        return batchRepository.save(batch);
    }
}