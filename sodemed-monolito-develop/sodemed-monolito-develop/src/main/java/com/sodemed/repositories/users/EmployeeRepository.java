package com.sodemed.repositories.users;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sodemed.models.users.Employee;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        boolean existsByEmail(String email);

        boolean existsByIdentification(String identification);

        boolean existsByEmailAndUserType(String email, UserType userType);

        boolean existsByIdentificationAndUserType(String identification, UserType userType);

        Optional<Employee> findByEmail(String email);

        Optional<Employee> findByIdentification(String identification);

        Optional<Employee> findByIdentificationAndStatus(String identification, StatusUser status);

        Optional<Employee> findByLogin(String login);

        Optional<Employee> findByLoginAndStatusAndTypeIdentification(String login, StatusUser status,
                        TypeIdentification typeIdentification);;

        @Query("SELECT e FROM Employee e " +
                        "WHERE (:id IS NULL OR e.id = :id) " +
                        "AND (:identification IS NULL OR LOWER(e.identification) LIKE LOWER(CONCAT('%', :identification, '%'))) "
                        +
                        "AND (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
                        "AND (:lastName IS NULL OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
                        "AND (:email IS NULL OR LOWER(e.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
                        "ORDER BY e.id")
        Page<Employee> fetchEmployeesByFilters(
                        @Param("id") Long id,
                        @Param("identification") String identification,
                        @Param("name") String name,
                        @Param("lastName") String lastName,
                        @Param("email") String email,
                        Pageable pageable);

}
