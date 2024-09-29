package com.ai.literature;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteratureApplication implements AppShellConfigurator {
 // this just loads the spring boot app  - nothing fancy here
	public static void main(String[] args) {
		SpringApplication.run(LiteratureApplication.class, args);
	}

}
