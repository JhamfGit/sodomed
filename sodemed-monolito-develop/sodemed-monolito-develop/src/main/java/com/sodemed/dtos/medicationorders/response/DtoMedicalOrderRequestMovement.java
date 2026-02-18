package com.sodemed.dtos.medicationorders.response;

import java.util.Date;

import com.sodemed.models.medicationorders.enums.StatusOrder;

public class DtoMedicalOrderRequestMovement {

    private Long id;
    private Date effDate;
    private Long idEmployee;
    private String nameEmployee;
    private Long medicationOrder;
    private StatusOrder statusOrder;
    private String observations;
    private boolean partial;
    private int totalPending;
    private String detailsPending;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public Long getMedicationOrder() {
        return medicationOrder;
    }

    public void setMedicationOrder(Long medicationOrder) {
        this.medicationOrder = medicationOrder;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public boolean isPartial() {
        return partial;
    }

    public void setPartial(boolean partial) {
        this.partial = partial;
    }

    public int getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(int totalPending) {
        this.totalPending = totalPending;
    }

    public String getDetailsPending() {
        return detailsPending;
    }

    public void setDetailsPending(String detailsPending) {
        this.detailsPending = detailsPending;
    }

}
