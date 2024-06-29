package com.j0k3r.andreanamaste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class AndreanamasteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AndreanamasteApplication.class, args);
	}

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void run(String... args) throws Exception {

	}
}
