package com.example.springhibernate;

import com.example.springhibernate.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EntityScan(basePackages = {"com.example.springhibernate.models"})
public class SpringHibernateApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {


		applicationContext = SpringApplication.run(SpringHibernateApplication.class, args);
	}

	@Bean
	@Autowired
	@Transactional
	CommandLineRunner commandLineRunner(ApplicationContext applicationContext){
		return (args)->{
			SessionFactory sessionFactory = applicationContext.getBean("sessionFactory", SessionFactory.class);
			Session session = sessionFactory.openSession();
			session.save(new Employee("shilendra"));
			session.save(new Employee("rohit"));
			session.close();
		};
	}

}
