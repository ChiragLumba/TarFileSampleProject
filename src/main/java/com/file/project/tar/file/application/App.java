package com.file.project.tar.file.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.file.project")
public class App {
	public static void main( String[] args )
    {
    	
        SpringApplication.run(App.class, args);
    }
}
