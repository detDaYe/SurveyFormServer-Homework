package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FormCRUDAPI {

    private final FormRepository formRepository;

    public FormCRUDAPI(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping("/api/form/list")
    public ResponseEntity<FormDto[]> formList(){
        ArrayList<Form> formArrayList = new ArrayList<Form>();
        formRepository.findAll().iterator().forEachRemaining(formArrayList::add);
        final int size = formArrayList.size();
        FormDto[] formDtos = new FormDto[size];
        for(int i = 0; i < size; i++)
            formDtos[i] = new FormDto(formArrayList.get(i));
        return new ResponseEntity<FormDto[]>(formDtos, HttpStatus.ACCEPTED);
    }
}
