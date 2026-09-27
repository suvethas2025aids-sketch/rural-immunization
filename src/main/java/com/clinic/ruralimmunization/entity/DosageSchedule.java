package com.clinic.ruralimmunization.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dosage_schedules")
public class DosageSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vaccineName;
    private Integer doseNumber;
    private Integer recommendedAgeMonths;
    private Integer intervalFromPreviousDoseDays;

    public Long getId() {
        return id;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Integer getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(Integer doseNumber) {
        this.doseNumber = doseNumber;
    }

    public Integer getRecommendedAgeMonths() {
        return recommendedAgeMonths;
    }

    public void setRecommendedAgeMonths(Integer recommendedAgeMonths) {
        this.recommendedAgeMonths = recommendedAgeMonths;
    }

    public Integer getIntervalFromPreviousDoseDays() {
        return intervalFromPreviousDoseDays;
    }

    public void setIntervalFromPreviousDoseDays(Integer intervalFromPreviousDoseDays) {
        this.intervalFromPreviousDoseDays = intervalFromPreviousDoseDays;
    }
}