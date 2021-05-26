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

import java.util.Optional;

@RestController
public class AnswerGetInfoAPI {

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/api/answer/info/{id}")
    public ResponseEntity<AnswerDto> answerGetInfo(@PathVariable("id") Long id){
        if(null == id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if(optionalAnswer.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new AnswerDto(optionalAnswer.get()), HttpStatus.OK);
    }
}
