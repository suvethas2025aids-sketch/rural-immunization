package com.clinic.ruralimmunization.dto;

public class AdministerRequest {

    private Long batchId;
    private Integer doses;

    public AdministerRequest() {
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }
}