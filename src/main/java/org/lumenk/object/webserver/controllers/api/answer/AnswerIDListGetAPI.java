package org.lumenk.object.webserver.controllers.api.answer;

import org.lumenk.object.webserver.entities.Answer;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.repositories.AnswerRepository;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AnswerIDListGetAPI {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/api/answer/idlist/{id}")
    public ResponseEntity<Long[]> answerListGet(@PathVariable("id") Long id){
        if(null == id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(id);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Answer> answerList = answerRepository.findByQuestion(optionalForm.get());
        final int size = answerList.size();
        Long[] temp = new Long[size];
        for(int i = 0; i < size; i++)
            temp[i] = answerList.get(i).getId();

        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
}
