package com.service.domainPersistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableR2dbcRepositories(basePackages = "com.service.orderService.repository")
@EntityScan(basePackages={"com.service.domainPersistence.persistence"})
public class PersistenceConfiguration {
}
