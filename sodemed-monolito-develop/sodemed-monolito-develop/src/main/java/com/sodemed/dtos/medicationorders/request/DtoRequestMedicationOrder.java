package com.sodemed.dtos.medicationorders.request;

import com.sodemed.models.users.enums.TypeClient;
import java.util.Date;

public class DtoRequestMedicationOrder {

    private long idMember;
    private String codigo;
    private String urlIdentificationFile;
    private String urlMedicationOrderFile;
    private String urlMedicalHistoryFile;
    private String comments;
    private String homeAddress;
    private String homeCity;
    private String urlFiles;
    private String userCreate;
    private String userOrderIdentification;
    private String userOrderName;
    private TypeClient typeClient;
    private Date creationDate;
    private String district;
    private String furtherDirectionIndication;

    public long getIdMember() {
        return idMember;
    }

    public void setIdMember(long idMember) {
        this.idMember = idMember;
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

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserOrderIdentification() {
        return userOrderIdentification;
    }

    public void setUserOrderIdentification(String userOrderIdentification) {
        this.userOrderIdentification = userOrderIdentification;
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

}
