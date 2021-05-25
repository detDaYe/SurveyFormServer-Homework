package org.lumenk.object.webserver.controllers.api.formcurd;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FormCreateAPI {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/form/create")
    public ResponseEntity<String> formCreate(@RequestParam("owner") String owner, @Nullable @RequestParam("title") String title){

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
        return new ResponseEntity<>("created", HttpStatus.CREATED);

    }
}