package org.lumenk.object.webserver.controllers.api.formcurd;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FormGetAPI {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/api/form/get/{id}")
    public ResponseEntity<FormDto> getForm(@PathVariable("id") Long id){
        if(null == id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Form> optionalForm = formRepository.findById(id);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new FormDto(optionalForm.get()), HttpStatus.OK);
    }
}
