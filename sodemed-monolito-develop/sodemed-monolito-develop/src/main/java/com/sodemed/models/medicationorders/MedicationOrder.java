package com.sodemed.models.medicationorders;

import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.enums.TypeClient;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table
public class MedicationOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idMember;
    private String codigo;
    private String urlIdentificationFile;
    private String urlMedicationOrderFile;
    private String urlMedicalHistoryFile;
    private String comments;
    private String homeAddress;
    private String homeCity;
    private String urlFiles;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client userCreate;
    private String userOrderIdentification;
    private String userOrderName;
    private TypeClient typeClient;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    private Date creationDate;
    private String district;
    private String furtherDirectionIndication;
    private Date lastModificationDate;
    private long idEmployee;
    private String nameEmployee;
    private String observations;
    private boolean active;
    private boolean partial;
    private int totalPending;
    private String detailsPending;

    public MedicationOrder() {
        super();
    }

    public MedicationOrder(long id, long idMember, String urlIdentificationFile, String urlMedicationOrderFile,
            String urlMedicalHistoryFile, String comments, String homeAddress, String homeCity) {
        this.id = id;
        this.idMember = idMember;
        this.urlIdentificationFile = urlIdentificationFile;
        this.urlMedicationOrderFile = urlMedicationOrderFile;
        this.urlMedicalHistoryFile = urlMedicalHistoryFile;
        this.comments = comments;
        this.homeAddress = homeAddress;
        this.homeCity = homeCity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlIdentificationFile() {
        return urlIdentificationFile;
    }

    public void setUrlIdentificationFile(String urlIdentificationFile) {
        this.urlIdentificationFile = urlIdentificationFile;
    }

    public String getUrlMedicationOrderFile() {
        return urlMedicationOrderFile;
    }

    public void setUrlMedicationOrderFile(String urlMedicationOrderFile) {
        this.urlMedicationOrderFile = urlMedicationOrderFile;
    }

    public String getUrlMedicalHistoryFile() {
        return urlMedicalHistoryFile;
    }

    public void setUrlMedicalHistoryFile(String urlMedicalHistoryFile) {
        this.urlMedicalHistoryFile = urlMedicalHistoryFile;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public long getIdMember() {
        return idMember;
    }

    public void setIdMember(long idMember) {
        this.idMember = idMember;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUrlFiles() {
        return urlFiles;
    }

    public void setUrlFiles(String urlFiles) {
        this.urlFiles = urlFiles;
    }

    public Client getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Client userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserOrderIdentification() {
        return userOrderIdentification;
    }

    public void setUserOrderIdentification(String userOrderIdentification) {
        this.userOrderIdentification = userOrderIdentification;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public String getUserOrderName() {
        return userOrderName;
    }

    public void setUserOrderName(String userOrderName) {
        this.userOrderName = userOrderName;
    }

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFurtherDirectionIndication() {
        return furtherDirectionIndication;
    }

    public void setFurtherDirectionIndication(String furtherDirectionIndication) {
        this.furtherDirectionIndication = furtherDirectionIndication;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
