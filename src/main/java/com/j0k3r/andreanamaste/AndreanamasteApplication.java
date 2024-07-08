package com.j0k3r.andreanamaste;

import com.j0k3r.andreanamaste.security.enums.Role;
import com.j0k3r.andreanamaste.security.models.User;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AndreanamasteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AndreanamasteApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(
				User.builder()
						.username("admin")
						.password(new BCryptPasswordEncoder().encode("admin"))
						.role(Role.ROLE_ADMIN)
						.email("miemail@Mail.com")
						.enable(true).build()
		);
	}
}
