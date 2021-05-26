package org.lumenk.object.webserver;

import com.google.gson.Gson;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.lumenk.object.webserver.util.JsonUtil;
import org.lumenk.object.webserver.util.SurveyFormUtil;
import org.lumenk.object.webserver.util.questions.ChoiceTypeQuestion;
import org.lumenk.object.webserver.util.questions.EssayTypeQuestion;
import org.lumenk.object.webserver.util.questions.Question;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class WebserverApplication implements CommandLineRunner {

	public static String getQuestionPath(long id){
		return "form/" + id;
	}

	public static String getAnswerPath(long id){
		return "answer/" + id;
	}
	public WebserverApplication(FormRepository formRepository, UserRepository userRepository) {
		this.formRepository = formRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		File formFolder = new File("form");
		File answerFolder = new File("answer");
		if(!formFolder.exists()) formFolder.mkdir();
		if(!answerFolder.exists()) answerFolder.mkdir();

	}

	private final FormRepository formRepository;
	private final UserRepository userRepository;
}
