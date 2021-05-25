package org.lumenk.object.webserver;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.lumenk.object.webserver.util.SurveyFormUtil;
import org.lumenk.object.webserver.util.questions.ChoiceTypeQuestion;
import org.lumenk.object.webserver.util.questions.EssayTypeQuestion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class WebserverApplication implements CommandLineRunner {

	public WebserverApplication(FormRepository formRepository, UserRepository userRepository) {
		this.formRepository = formRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.id("rkpk")
				.name("romeo")
				.build();
		userRepository.save(user);

		File file = new File("test.txt");

		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.append("hello");
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}



	}

	private final FormRepository formRepository;
	private final UserRepository userRepository;
}
