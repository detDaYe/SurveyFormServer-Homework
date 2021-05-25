package org.lumenk.object.webserver.controllers.api.formcurd;

import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.lumenk.object.webserver.util.questions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@RestController
public class FormUpdateJsonAPI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormRepository formRepository;

    @PostMapping("/api/form/updatejson/{id}")
    public ResponseEntity<String> updateFormJson(@RequestBody Question[] questions, @PathVariable Long id){
        if(null == id)
            return new ResponseEntity<>("id cannot be null", HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(id);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("form not found", HttpStatus.BAD_REQUEST);

        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("form" + id.toString());
            fileWriter.write(gson.toJson(questions));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("updated", HttpStatus.NO_CONTENT);
    }
}
