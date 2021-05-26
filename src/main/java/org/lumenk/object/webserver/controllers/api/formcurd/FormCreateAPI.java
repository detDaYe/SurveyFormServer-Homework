package org.lumenk.object.webserver.controllers.api.formcurd;

import com.google.gson.Gson;
import org.apache.catalina.connector.OutputBuffer;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.FormDto;
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
public class FormCreateAPI {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/form/create")
    public ResponseEntity<String> formCreate(HttpEntity<String> httpEntity, @RequestParam("owner") String owner,
                                             @Nullable @RequestParam("title") String title){

        String[] strings = JsonUtil.splitToArray(Objects.requireNonNull(httpEntity.getBody()));
        Question[] questions = new Question[strings.length];
        for(int i = 0; i < strings.length; i++)
            questions[i] = Question.fromJson(strings[i]);

        if(null == owner)
            return new ResponseEntity<>("user CANNOT be NULL", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(owner);
        if(optionalUser.isEmpty())
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        Form form = Form.builder()
                .owner(optionalUser.get())
                .title(title == null ? "NO TITLE" : title)
                .build();

        formRepository.save(form);

        form = formRepository.findById(form.getId()).get();
        try {

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("form/" + form.getId()), StandardCharsets.UTF_8));



            Gson gson = new Gson();
            writer.write(gson.toJson(questions));
            writer.close();
        } catch (IOException e) {
            formRepository.delete(form);
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("created", HttpStatus.CREATED);

    }
}
