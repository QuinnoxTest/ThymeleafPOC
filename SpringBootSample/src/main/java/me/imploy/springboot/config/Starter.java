package me.imploy.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import sample.aspect.LoggingAspect;
import sample.springboot.dao.SampleDAO;
import sample.springboot.service.EmployerEventManager;
import sample.springboot.service.EmployerJobPostManager;
import sample.springboot.service.IEmployerEventManager;
import sample.springboot.service.IEmployerJobPostManager;
import sample.springboot.service.ILoginManagement;
import sample.springboot.service.IUniversityManager;
import sample.springboot.service.LoginManagement;
import sample.springboot.service.SampleManager;
import sample.springboot.service.UniversityManager;

import com.datastax.driver.core.Cluster;


@SpringBootApplication
@ComponentScan(basePackages = {"me.imploy.*","sample.*"})
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
@Configuration
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	
	@Bean
	public LoggingAspect getLoggingAspect(){
		return new LoggingAspect();	
	}
	
	
	@Bean
	public IEmployerEventManager loadIEmployerEventManager(){
		return new EmployerEventManager();
	}
	
	@Bean
	public IUniversityManager loadIUniversityManager(){
		return new UniversityManager();
	}
	
	@Bean
	public IEmployerJobPostManager loadIEmployerJobPostManager(){
		return new EmployerJobPostManager();
	}
	
	@Bean
	public ILoginManagement loadILoginManagement(){
		return new LoginManagement();
	}
	
	
	
	@Bean
	public CassandraOperations getCassandraOperations(){
		return new CassandraTemplate(Cluster.builder().addContactPoint("10.20.4.106").build().connect("caerusdb"));
	}
	
	@Bean(name = "sampleDAO")
	public SampleDAO getSampleDAO(){
		return new SampleDAO();
	}
	
	@Bean(name = "sampleManager")
	public SampleManager getSampleService(){
		return new SampleManager();
	}

	/** Thymeleaf View Resolver */	
	@Bean
    public TemplateResolver templateResolver(){
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);

        return templateResolver;
    }
	
	
	  @Bean
	  public SpringTemplateEngine templateEngine(){
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(templateResolver());
	        return templateEngine;
	  }

	    @Bean
	    public ViewResolver viewResolver(){
	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
	        viewResolver.setTemplateEngine(templateEngine());
	        viewResolver.setOrder(0);

	        return viewResolver;
	     }
	    /** Thymeleaf View Resolver */	
	
}
