package com.ftn.SBZ_2018;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableJpaRepositories(entityManagerFactoryRef="emf")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public KieContainer kieContainer() {
		 KieServices ks = KieServices.Factory.get();
	     KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
	     KieScanner kScanner = ks.newKieScanner(kContainer);
	     kScanner.start(1000);
	     
	     return kContainer;
	}
}
