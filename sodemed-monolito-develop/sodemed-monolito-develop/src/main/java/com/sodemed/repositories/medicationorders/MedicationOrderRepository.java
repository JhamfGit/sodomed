package com.sodemed.repositories.medicationorders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sodemed.models.medicationorders.MedicationOrder;
import com.sodemed.models.medicationorders.enums.StatusOrder;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {

        List<MedicationOrder> findByUserCreate_IdAndActive(long id, boolean active);

        List<MedicationOrder> findByUserCreate_IdAndStatusAndActive(long id, StatusOrder statusUser, boolean active);

        List<MedicationOrder> findByUserCreate_IdAndStatusNot(long id, StatusOrder statusUser);

        Page<MedicationOrder> findByCreationDateBetweenOrStatusOrUserOrderIdentification(Date startDate, Date endDate,
                        StatusOrder statusOrder, String identification, Pageable pageable);

        @Query("SELECT m FROM MedicationOrder m " +
                        "WHERE m.active = true AND (:statusOrder IS NULL OR m.status = :statusOrder) " +
                        "AND (:identification IS NULL OR m.userOrderIdentification = :identification) " +
                        "AND (:startDate IS NULL OR :endDate IS NULL OR m.creationDate BETWEEN :startDate AND :endDate)")
        Page<MedicationOrder> fetchFilters(
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate,
                        @Param("statusOrder") StatusOrder statusOrder,
                        @Param("identification") String identification,
                        Pageable pageable);

        @Query("SELECT m FROM MedicationOrder m " +
                        "WHERE m.active = true AND (:statusOrder IS NULL OR m.status = :statusOrder) " +
                        "AND (:identification IS NULL OR m.userOrderIdentification = :identification) " +
                        "AND (:startDate IS NULL OR :endDate IS NULL OR m.creationDate BETWEEN :startDate AND :endDate)")
        List<MedicationOrder> fetchFiltersAll(
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate,
                        @Param("statusOrder") StatusOrder statusOrder,
                        @Param("identification") String identification);

}
