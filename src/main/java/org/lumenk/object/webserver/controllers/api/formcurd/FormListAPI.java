package org.lumenk.object.webserver.controllers.api.formcurd;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FormListAPI {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/api/form/list")
    public ResponseEntity<FormDto[]> formList(){
        ArrayList<Form> formArrayList = new ArrayList<>();
        formRepository.findAll().forEach(formArrayList::add);

        final int size = formArrayList.size();
        FormDto[] formDtos = new FormDto[size];

        for(int i = 0; i < size; i++)
            formDtos[i] = new FormDto(formArrayList.get(i));

        return new ResponseEntity<>(formDtos, HttpStatus.OK);
    }
}
