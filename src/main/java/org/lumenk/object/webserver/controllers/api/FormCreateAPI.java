package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FormCreateAPI {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/form/create/{owner}")
    public ResponseEntity<String> formCreate(@RequestBody FormDto formDto,
                                             @PathVariable("owner") String ownerID){
        if(null == ownerID)
            return new ResponseEntity<String>("owner ID cannot be NULL", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(ownerID);

        if(optionalUser.isEmpty())
            return new ResponseEntity<String>("owner does not exists", HttpStatus.BAD_REQUEST);

        if(null != formDto.getId())
            return new ResponseEntity<>("when create new form, form ID MUST be null", HttpStatus.BAD_REQUEST);

        Form form = Form.builder()
                .owner(optionalUser.get())
                .title(formDto.getTitle())
                .build();

        formRepository.save(form);

        return new ResponseEntity<String>("form created", HttpStatus.NO_CONTENT);

    }
}
