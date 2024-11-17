package com.codingshuttle.alice.bakery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBakeryAppApplication implements CommandLineRunner {

	final private CakeBaker cakeBaker;

    public CakeBakeryAppApplication(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {

		SpringApplication.run(CakeBakeryAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cakeBaker.bakeCake();
	}
}
