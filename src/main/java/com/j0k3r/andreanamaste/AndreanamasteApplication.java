package com.j0k3r.andreanamaste;

import com.j0k3r.andreanamaste.models.Product;
import com.j0k3r.andreanamaste.repositories.ProductRepository;
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

import java.util.List;

@SpringBootApplication
public class AndreanamasteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AndreanamasteApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

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

		List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30).forEach(
				i -> productRepository.save(
						Product.builder()
								.name("Producto "+i)
								.description("Descripcion del producto "+i)
								.priceARS(Double.valueOf(i))
								.priceUSD(Double.valueOf(i))
								.build()
		));

	}
}
