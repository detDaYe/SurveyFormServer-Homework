package org.lumenk.object.webserver.controllers.api.answer;

import com.google.gson.Gson;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.repositories.AnswerRepository;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.util.JsonUtil;
import org.lumenk.object.webserver.util.answers.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AnswerCreateAPI {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private FormRepository formRepository;

    @PostMapping("/api/answer/create/{id}")
    public ResponseEntity<String> answerCreate(HttpEntity<String> httpEntity, @PathVariable("id") Long formID){

        String[] strings = JsonUtil.splitToArray(Objects.requireNonNull(httpEntity.getBody()));
        Answer[] answers = new Answer[strings.length];

        for(int i = 0; i < strings.length; i++)
            answers[i] = Answer.fromJson(strings[i]);

        if(null == formID)
            return new ResponseEntity<>("form ID cannot be null", HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(formID);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("form not found", HttpStatus.BAD_REQUEST);

        org.lumenk.object.webserver.entities.Answer answer =
                org.lumenk.object.webserver.entities.Answer.builder()
                .question(optionalForm.get())
                .build();
        answerRepository.save(answer);
        System.out.println(answer.getId());

        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("answer/" + answer.getId()), StandardCharsets.UTF_8));

            Gson gson = new Gson();
            writer.write(gson.toJson(answers));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
