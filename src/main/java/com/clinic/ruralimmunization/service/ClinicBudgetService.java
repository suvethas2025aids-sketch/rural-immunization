package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.dto.BudgetVarianceResponse;
import com.clinic.ruralimmunization.entity.ClinicBudget;
import com.clinic.ruralimmunization.entity.ClinicSite;
import com.clinic.ruralimmunization.repository.ClinicBudgetRepository;
import com.clinic.ruralimmunization.repository.ClinicSiteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClinicBudgetService {

    private final ClinicBudgetRepository budgetRepository;
    private final ClinicSiteRepository siteRepository;

    public ClinicBudgetService(
            ClinicBudgetRepository budgetRepository,
            ClinicSiteRepository siteRepository) {
        this.budgetRepository = budgetRepository;
        this.siteRepository = siteRepository;
    }

    // Create a clinic budget
    public ClinicBudget createBudget(ClinicBudget budget) {

        if (budget.getClinicSite() == null
                || budget.getClinicSite().getId() == null) {
            throw new IllegalArgumentException(
                    "Clinic site ID is required");
        }

        if (budget.getAllocatedGrant() == null
                || budget.getAllocatedGrant()
                .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Allocated grant must be zero or more");
        }

        if (budget.getActualProcurementExpense() == null
                || budget.getActualProcurementExpense()
                .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Procurement expense must be zero or more");
        }

        ClinicSite site = siteRepository.findById(
                budget.getClinicSite().getId()
        ).orElseThrow(() ->
                new IllegalArgumentException("Clinic site not found"));

        budget.setClinicSite(site);
        return budgetRepository.save(budget);
    }

    // Get all budgets
    public List<ClinicBudget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // Record procurement expense
    public ClinicBudget recordProcurementExpense(
            Long budgetId, BigDecimal expense) {

        if (expense == null
                || expense.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Expense must be greater than zero");
        }

        ClinicBudget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Budget not found"));

        BigDecimal currentExpense =
                budget.getActualProcurementExpense();

        if (currentExpense == null) {
            currentExpense = BigDecimal.ZERO;
        }

        budget.setActualProcurementExpense(
                currentExpense.add(expense));

        return budgetRepository.save(budget);
    }

    // Calculate remaining grant
    public BigDecimal getRemainingGrant(Long budgetId) {

        ClinicBudget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Budget not found"));

        BigDecimal allocated = budget.getAllocatedGrant();
        BigDecimal spent = budget.getActualProcurementExpense();

        if (allocated == null) {
            allocated = BigDecimal.ZERO;
        }

        if (spent == null) {
            spent = BigDecimal.ZERO;
        }

        return allocated.subtract(spent);
    }

    // Get budgets by district
    public List<ClinicBudget> getBudgetsByDistrict(String district) {

        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException(
                    "District name is required");
        }

        return budgetRepository.findAll().stream()
                .filter(budget ->
                        budget.getClinicSite() != null
                                && budget.getClinicSite().getDistrict() != null
                                && budget.getClinicSite().getDistrict()
                                .equalsIgnoreCase(district))
                .toList();
    }

    // Create budget variance report
    public List<BudgetVarianceResponse> getBudgetVarianceReport(
            String district) {

        List<ClinicBudget> budgets;

        if (district == null || district.isBlank()) {
            budgets = budgetRepository.findAll();
        } else {
            budgets = getBudgetsByDistrict(district);
        }

        return budgets.stream()
                .map(budget -> {
                    BigDecimal allocated = budget.getAllocatedGrant() == null
                            ? BigDecimal.ZERO
                            : budget.getAllocatedGrant();

                    BigDecimal expense =
                            budget.getActualProcurementExpense() == null
                                    ? BigDecimal.ZERO
                                    : budget.getActualProcurementExpense();

                    BigDecimal remaining = allocated.subtract(expense);

                    return new BudgetVarianceResponse(
                            budget.getId(),
                            budget.getClinicSite().getSiteName(),
                            budget.getClinicSite().getDistrict(),
                            budget.getFiscalYear(),
                            allocated,
                            expense,
                            remaining
                    );
                })
                .toList();
    }
}