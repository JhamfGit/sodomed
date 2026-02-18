package com.sodemed.models.notifications;

import java.util.Date;

import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.notifications.enums.TypeNotification;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date creationDate;
    private long idMedicationOrder;
    @Enumerated(EnumType.STRING)
    private TypeNotification type;
    private boolean sent;
    private long idClient;
    @Enumerated(EnumType.STRING)
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
