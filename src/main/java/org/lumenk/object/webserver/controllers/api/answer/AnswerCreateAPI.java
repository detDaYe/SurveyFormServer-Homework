package org.lumenk.object.webserver.controllers.api.answer;

import com.google.gson.Gson;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.repositories.AnswerRepository;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.util.answers.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@RestController
public class AnswerCreateAPI {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private FormRepository formRepository;

    @PostMapping("/api/answer/create/{id}")
    public ResponseEntity<String> answerCreate(@RequestBody Answer[] answers, @PathVariable("id") Long formID){

        if(null == formID)
            return new ResponseEntity<>("form ID cannot be null", HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(formID);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("form not found", HttpStatus.BAD_REQUEST);

        org.lumenk.object.webserver.entities.Answer answer =
                org.lumenk.object.webserver.entities.Answer.builder()
                .question(optionalForm.get())
                .build();

        try {
            FileWriter writer = new FileWriter("answer/" + answer.getId().toString());
            Gson gson = new Gson();
            writer.write(gson.toJson(answers));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        answerRepository.save(answer);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
