package org.lumenk.object.webserver.controllers.api.formcurd;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@RestController
public class FormJsonGetAPI {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/api/form/getjson/{id}")
    public ResponseEntity<String> formJson(@PathVariable("id") Long id){
        if(null == id)
            return new ResponseEntity<>("id must not be null", HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(id);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("id not found", HttpStatus.BAD_REQUEST);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("form/" + optionalForm.get().getId().toString()));
            String result = reader.readLine();
            reader.close();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
