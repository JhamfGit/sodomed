package com.sodemed.repositories.email;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.email.EmailConfiguration;

public interface  EmailConfigurationRepository extends JpaRepository<EmailConfiguration, Long> {

}
