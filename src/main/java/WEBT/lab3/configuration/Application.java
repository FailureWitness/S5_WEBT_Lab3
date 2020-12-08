package WEBT.lab3.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"WEBT.lab3.controllers", "WEBT.lab3.configuration"})
@SpringBootApplication
public class Application implements WebMvcConfigurer{
	public static void main(String[] args) {		
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/resources/**")) {
			registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/");
		}
	}
}
// Made by EugeneVV