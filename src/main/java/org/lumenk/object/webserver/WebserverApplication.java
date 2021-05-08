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
				.id("yl_rkpk")
				.name("roemo")
				.build();

		userRepository.save(user);


		Form from = Form.builder()
				.owner(user)
				.build();

		formRepository.save(from);

		SurveyFormUtil survey = new SurveyFormUtil();
		survey.addQuestions(new ChoiceTypeQuestion("do you like math?", true, 2, false, new String[]{"yes", "no"}));
		survey.addQuestions(new EssayTypeQuestion("how much?", false, 50));

		String temp = survey.makeJson();
		SurveyFormUtil.fromJson(temp);

	}

	private final FormRepository formRepository;
	private final UserRepository userRepository;
}
