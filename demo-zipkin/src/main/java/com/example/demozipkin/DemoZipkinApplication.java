package com.example.demozipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class DemoZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoZipkinApplication.class, args);
	}
}
