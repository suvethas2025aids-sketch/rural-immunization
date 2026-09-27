package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.dto.BudgetVarianceResponse;
import com.clinic.ruralimmunization.entity.ClinicBudget;
import com.clinic.ruralimmunization.service.ClinicBudgetService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/clinic-budgets")
public class ClinicBudgetController {

    private final ClinicBudgetService service;

    public ClinicBudgetController(ClinicBudgetService service) {
        this.service = service;
    }

    // Create a clinic budget
    @PostMapping
    public ClinicBudget createBudget(
            @RequestBody ClinicBudget budget) {
        return service.createBudget(budget);
    }

    // Get all clinic budgets
    @GetMapping
    public List<ClinicBudget> getAllBudgets() {
        return service.getAllBudgets();
    }

    // Record procurement expense
    @PostMapping("/{budgetId}/expenses")
    public ClinicBudget recordProcurementExpense(
            @PathVariable Long budgetId,
            @RequestParam BigDecimal amount) {
        return service.recordProcurementExpense(budgetId, amount);
    }

    // Get remaining grant
    @GetMapping("/{budgetId}/remaining")
    public BigDecimal getRemainingGrant(
            @PathVariable Long budgetId) {
        return service.getRemainingGrant(budgetId);
    }

    // Get budgets by district
    @GetMapping("/district/{district}")
    public List<ClinicBudget> getBudgetsByDistrict(
            @PathVariable String district) {
        return service.getBudgetsByDistrict(district);
    }

    // Get budget variance report
    @GetMapping("/variance")
    public List<BudgetVarianceResponse> getBudgetVarianceReport(
            @RequestParam(required = false) String district) {
        return service.getBudgetVarianceReport(district);
    }
}