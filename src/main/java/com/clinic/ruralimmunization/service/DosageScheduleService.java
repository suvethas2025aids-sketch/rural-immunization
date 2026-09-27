package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.entity.DosageSchedule;
import com.clinic.ruralimmunization.repository.DosageScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DosageScheduleService {

    private final DosageScheduleRepository repository;

    public DosageScheduleService(DosageScheduleRepository repository) {
        this.repository = repository;
    }

    // Create a dosage schedule
    public DosageSchedule createSchedule(DosageSchedule schedule) {
        return repository.save(schedule);
    }

    // Get all dosage schedules
    public List<DosageSchedule> getAllSchedules() {
        return repository.findAll();
    }

    // Update a dosage schedule
    public DosageSchedule updateSchedule(
            Long id, DosageSchedule updatedSchedule) {

        DosageSchedule existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dosage schedule not found"));

        existing.setVaccineName(updatedSchedule.getVaccineName());
        existing.setDoseNumber(updatedSchedule.getDoseNumber());
        existing.setRecommendedAgeMonths(
                updatedSchedule.getRecommendedAgeMonths());
        existing.setIntervalFromPreviousDoseDays(
                updatedSchedule.getIntervalFromPreviousDoseDays());

        return repository.save(existing);
    }

    // Delete a dosage schedule
    public void deleteSchedule(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Dosage schedule not found");
        }

        repository.deleteById(id);
    }
}