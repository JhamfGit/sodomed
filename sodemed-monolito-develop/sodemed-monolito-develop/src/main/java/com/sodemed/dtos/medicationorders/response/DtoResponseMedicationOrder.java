package com.sodemed.dtos.medicationorders.response;

import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.users.enums.TypeClient;
import java.util.Date;

public class DtoResponseMedicationOrder {

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
    private String userOrderIdentification;
    private String userOrderName;
    private TypeClient typeClient;
    private Date creationDate;
    private String district;
    private String furtherDirectionIndication;
    private StatusOrder statusOrder;
    private Date lastModificationDate;
    private long idEmployee;
    private String nameEmployee;
    private String observations;
    private boolean active;
    private boolean partial;
    private int totalPending;
    private String detailsPending;
    private String userCreatePhone;
    private String userCreateEmail;
    private String userCreateCountryDialCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
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

    public String getUserCreatePhone() {
        return userCreatePhone;
    }

    public void setUserCreatePhone(String userCreatePhone) {
        this.userCreatePhone = userCreatePhone;
    }

    public String getUserCreateEmail() {
        return userCreateEmail;
    }

    public void setUserCreateEmail(String userCreateEmail) {
        this.userCreateEmail = userCreateEmail;
    }

    public String getUserCreateCountryDialCode() {
        return userCreateCountryDialCode;
    }

    public void setUserCreateCountryDialCode(String userCreateCountryDialCode) {
        this.userCreateCountryDialCode = userCreateCountryDialCode;
    }

}
