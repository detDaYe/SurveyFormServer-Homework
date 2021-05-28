package org.lumenk.object.webserver.controllers.api.answer;

import org.lumenk.object.webserver.entities.Answer;
import org.lumenk.object.webserver.entities.dto.AnswerDto;
import org.lumenk.object.webserver.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
public class AnswerGetJsonAPI {

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/api/answer/jsonbyid/{id}")
    public ResponseEntity<String> answerGetJson(@PathVariable("id")Long id){
        if(null == id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if(optionalAnswer.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            //BufferedReader reader = new BufferedReader(new FileReader("answer/" + id.toString()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("answer/" + optionalAnswer.get().getId()), StandardCharsets.UTF_8));
            String result = reader.readLine();
            reader.close();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
