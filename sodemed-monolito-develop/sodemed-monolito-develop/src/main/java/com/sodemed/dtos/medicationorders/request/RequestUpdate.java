package com.sodemed.dtos.medicationorders.request;

import com.sodemed.models.medicationorders.enums.StatusOrder;

public class RequestUpdate {
    private long idEmployee;
    private String nameEmployee;
    private StatusOrder statusOrder;
    private String comments;
    private boolean partial;
    private int totalPending;
    private String detailsPending;

    public long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
