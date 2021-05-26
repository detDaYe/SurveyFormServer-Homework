package org.lumenk.object.webserver.controllers.api.formcurd;

import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.lumenk.object.webserver.util.JsonUtil;
import org.lumenk.object.webserver.util.questions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@RestController
public class FormUpdateJsonAPI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormRepository formRepository;

    @PostMapping("/api/form/updatejson/{id}")
    public ResponseEntity<String> updateFormJson(HttpEntity<String> httpEntity, @PathVariable Long id){

        String[] strings = JsonUtil.splitToArray(Objects.requireNonNull(httpEntity.getBody()));
        Question[] questions = new Question[strings.length];
        for(int i = 0; i < strings.length; i++)
            questions[i] = Question.fromJson(strings[i]);

        if(null == id)
            return new ResponseEntity<>("id cannot be null", HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(id);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("form not found", HttpStatus.BAD_REQUEST);

        Gson gson = new Gson();
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("form/" + id), StandardCharsets.UTF_8));


            fileWriter.write(gson.toJson(questions));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("updated", HttpStatus.NO_CONTENT);
    }
}
