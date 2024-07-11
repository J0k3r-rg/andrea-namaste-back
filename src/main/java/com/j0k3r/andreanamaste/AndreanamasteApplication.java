package com.j0k3r.andreanamaste;

import com.j0k3r.andreanamaste.models.Product;
import com.j0k3r.andreanamaste.models.ProfileItem;
import com.j0k3r.andreanamaste.repositories.ProductRepository;
import com.j0k3r.andreanamaste.repositories.ProfileItemRepository;
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

	@Autowired
	private ProfileItemRepository profileItemRepository;

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

		userRepository.save(
				User.builder()
						.username("user")
						.password(new BCryptPasswordEncoder().encode("user"))
						.role(Role.ROLE_USER)
						.email("user@mail.com")
						.enable(true).build()
		);

		productRepository.save(
			Product.builder()
				.name("Decodificacion Arquetipal")
				.description("La decodificación arquetípica es un método de análisis personal que se basa en la psicología junguiana para identificar los arquetipos que influyen en nuestro comportamiento, pensamientos y emociones.")
				.priceARS(15000.0)
				.priceUSD(10.0)
				.imageUrl("https://i.imgur.com/mtlae37.jpg")
				.build()
		);

		productRepository.save(
				Product.builder()
						.name("Feng Shui")
						.description("Armoniza tu entorno y potencia tu bienestar con Feng Shui. Experto te guía para optimizar la energía de tu hogar o negocio.")
						.priceARS(15000.0)
						.priceUSD(10.0)
						.imageUrl("https://i.imgur.com/0rhoE4E.jpg")
						.build()
		);

		productRepository.save(
				Product.builder()
						.name("Pendulo Egreo de Orgon")
						.description("Equilibra tu energía y bienestar. Armoniza tu aura y elimina bloqueos energéticos con la sabiduría ancestral del Pendulo Egreo de Orgon.")
						.priceARS(15000.0)
						.priceUSD(10.0)
						.imageUrl("https://i.imgur.com/sfcP1Ex.jpg")
						.build()
		);

		productRepository.save(
				Product.builder()
						.name("Sanacion pranica")
						.description("Sana cuerpo, mente y espíritu. Activa tu energía vital y recupera tu equilibrio natural con la milenaria técnica de Sanación Pránica.")
						.priceARS(15000.0)
						.priceUSD(10.0)
						.imageUrl("https://i.imgur.com/XMr6H5R.jpg")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("about_me")
						.body("Soy Andrea Namaste, terapeuta holística y profesora de yoga. Mi misión es acompañarte en tu proceso de sanación y transformación personal, para que puedas conectar con tu esencia y vivir en armonía con tu cuerpo, mente y espíritu.")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("image_about_me")
						.name("image_about_me")
						.body("https://i.imgur.com/j1OA1US.jpeg")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("text_motivation")
						.body("El camino no esta en el cielo, el camino esta en el corazon")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("author_text_motivation")
						.body("Buda")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("whatsapp")
						.body("link")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("facebook")
						.body("link")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("instagram")
						.body("link")
						.build()
		);

		profileItemRepository.save(
				ProfileItem.builder()
						.name("twitter")
						.body("link")
						.build()
		);

	}
}
