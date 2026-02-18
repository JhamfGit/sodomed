package com.sodemed.models.medicationorders;

import java.util.Date;

import com.sodemed.models.medicationorders.enums.StatusOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class MedicalOrderRequestMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date effDate;
    private Long idEmployee;
    private String nameEmployee;
    private Long medicationOrder;
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;
    private String observations;
    private boolean partial;
    private int totalPending;
    private String detailsPending;

    public MedicalOrderRequestMovement() {
        super();
    }

    public MedicalOrderRequestMovement(Date effDate, Long idEmployee, String nameEmployee,
            Long medicationOrder, StatusOrder statusOrder, String observatioons, boolean partial, int totalPending,
            String detailsPending) {
        this.effDate = effDate;
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.medicationOrder = medicationOrder;
        this.statusOrder = statusOrder;
        this.observations = observatioons;
        this.partial = partial;
        this.totalPending = totalPending;
        this.detailsPending = detailsPending;
    }

    public MedicalOrderRequestMovement(Date effDate, Long idEmployee, String nameEmployee,
            Long medicationOrder, StatusOrder statusOrder) {
        this.effDate = effDate;
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.medicationOrder = medicationOrder;
        this.statusOrder = statusOrder;
    }

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
