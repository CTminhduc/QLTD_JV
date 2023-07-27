package com.java08.quanlituyendung;
import com.java08.quanlituyendung.service.IPositionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QuanlituyendungApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanlituyendungApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(QuanlituyendungApplication.class);
		builder.headless(false).run(args);
	}


	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}	

}
