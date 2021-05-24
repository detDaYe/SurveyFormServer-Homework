package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.Form;
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
    public ResponseEntity<Form[]> formList(){
        ArrayList<Form> formArrayList = new ArrayList<Form>();
        formRepository.findAll().iterator().forEachRemaining(formArrayList::add);
        Form[] forms = (Form[]) formArrayList.toArray();
        return new ResponseEntity<Form[]>(forms, HttpStatus.ACCEPTED);
    }
}
