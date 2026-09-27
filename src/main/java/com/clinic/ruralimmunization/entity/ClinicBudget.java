package com.clinic.ruralimmunization.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clinic_budgets")
public class ClinicBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fiscalYear;

    private BigDecimal allocatedGrant;

    private BigDecimal actualProcurementExpense;

    @ManyToOne
    @JoinColumn(name = "clinic_site_id", nullable = false)
    private ClinicSite clinicSite;

    public Long getId() {
        return id;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public BigDecimal getAllocatedGrant() {
        return allocatedGrant;
    }

    public void setAllocatedGrant(BigDecimal allocatedGrant) {
        this.allocatedGrant = allocatedGrant;
    }

    public BigDecimal getActualProcurementExpense() {
        return actualProcurementExpense;
    }

    public void setActualProcurementExpense(BigDecimal actualProcurementExpense) {
        this.actualProcurementExpense = actualProcurementExpense;
    }

    public ClinicSite getClinicSite() {
        return clinicSite;
    }

    public void setClinicSite(ClinicSite clinicSite) {
        this.clinicSite = clinicSite;
    }
}
