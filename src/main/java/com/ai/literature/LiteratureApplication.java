package com.ai.literature;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteratureApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(LiteratureApplication.class, args);
	}

}
