package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.InsuranceClaim;
import com.clinic.ruralimmunization.service.InsuranceClaimService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-claims")
public class InsuranceClaimController {

    private final InsuranceClaimService claimService;

    public InsuranceClaimController(InsuranceClaimService claimService) {
        this.claimService = claimService;
    }

    // Create a new insurance claim
    @PostMapping
    public InsuranceClaim createClaim(
            @Valid @RequestBody InsuranceClaim claim) {
        return claimService.createClaim(claim);
    }

    // Get all insurance claims
    @GetMapping
    public List<InsuranceClaim> getAllClaims() {
        return claimService.getAllClaims();
    }

    // Get one claim by ID
    @GetMapping("/{id}")
    public InsuranceClaim getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    // Update claim status
    @PutMapping("/{id}/status")
    public InsuranceClaim updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return claimService.updateClaimStatus(id, status);
    }
}
