package com.sodemed.dtos.notifications.response;

import java.util.Date;

import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.notifications.enums.TypeNotification;

public class DtoResponseNotificationTask {

    private long id;
    private Date creationDate;
    private long idMedicationOrder;
    private TypeNotification type;
    private boolean sent;
    private long idCLient;
    private StatusOrder statusOrder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getIdCLient() {
        return idCLient;
    }

    public void setIdCLient(long idCLient) {
        this.idCLient = idCLient;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

}
