package com.skillbox.fibonacci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFibonacciApplication {

	public static void main(String[] args) {
		SpringApplication.from(FibonacciApplication::main).with(TestFibonacciApplication.class).run(args);
	}

}
