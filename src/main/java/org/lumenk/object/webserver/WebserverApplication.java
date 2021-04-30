package org.lumenk.object.webserver;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserverApplication implements CommandLineRunner {

	public WebserverApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.id("yl_rkpk")
				.name("roemo")
				.build();

		userRepository.save(user);

	}

	private final UserRepository userRepository;
}
