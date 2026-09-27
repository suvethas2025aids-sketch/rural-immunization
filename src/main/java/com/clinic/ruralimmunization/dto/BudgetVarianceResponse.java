package com.clinic.ruralimmunization.dto;

import java.math.BigDecimal;

public class BudgetVarianceResponse {

    private Long budgetId;
    private String clinicName;
    private String district;
    private Integer fiscalYear;
    private BigDecimal allocatedGrant;
    private BigDecimal actualProcurementExpense;
    private BigDecimal remainingGrant;

    public BudgetVarianceResponse(
            Long budgetId,
            String clinicName,
            String district,
            Integer fiscalYear,
            BigDecimal allocatedGrant,
            BigDecimal actualProcurementExpense,
            BigDecimal remainingGrant) {

        this.budgetId = budgetId;
        this.clinicName = clinicName;
        this.district = district;
        this.fiscalYear = fiscalYear;
        this.allocatedGrant = allocatedGrant;
        this.actualProcurementExpense = actualProcurementExpense;
        this.remainingGrant = remainingGrant;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public BigDecimal getAllocatedGrant() {
        return allocatedGrant;
    }

    public BigDecimal getActualProcurementExpense() {
        return actualProcurementExpense;
    }

    public BigDecimal getRemainingGrant() {
        return remainingGrant;
    }
}