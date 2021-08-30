package com.dbs.lmng.sweeps.intraday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SweepsIntraDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweepsIntraDayApplication.class, args);
	}
}
