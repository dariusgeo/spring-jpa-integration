package edu.link.jpa.service;

import edu.link.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContext {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryService countryService = ctx.getBean(CountryService.class);

	}

}
