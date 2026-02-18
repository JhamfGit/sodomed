package com.sodemed.dtos.notifications.request;

import java.util.Date;

import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.notifications.enums.TypeNotification;

public class DtoRequestNotificationTask {

    private Date creationDate;
    private long idMedicationOrder;
    private TypeNotification type;
    private boolean sent;
    private long idClient;
    private StatusOrder statusOrder;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getIdMedicationOrder() {
        return idMedicationOrder;
    }

    public void setIdMedicationOrder(long idMedicationOrder) {
        this.idMedicationOrder = idMedicationOrder;
    }

    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

}
