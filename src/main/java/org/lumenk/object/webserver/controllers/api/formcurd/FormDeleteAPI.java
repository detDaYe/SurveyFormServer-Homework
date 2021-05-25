package org.lumenk.object.webserver.controllers.api.formcurd;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FormDeleteAPI {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/form/delete")
    public ResponseEntity<String> formDelete(@RequestParam("id") Long id, @RequestParam("owner") String owner){
        if(null == id)
            return new ResponseEntity<>("id MUST NOT NULL", HttpStatus.BAD_REQUEST);
        if(null == owner)
            return new ResponseEntity<>("owner MUST NOT NULL", HttpStatus.BAD_REQUEST);
        Optional<Form> optionalForm = formRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(owner);

        if(optionalUser.isEmpty())
            return new ResponseEntity<>("Owner NOT FOUND", HttpStatus.BAD_REQUEST);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("Form NOT FOUND", HttpStatus.BAD_REQUEST);

        Form form = optionalForm.get();
        User user = optionalUser.get();

        if(!form.getOwner().getId().equals(user.getId()))
            return new ResponseEntity<>("You are not own this Form", HttpStatus.BAD_REQUEST);

        formRepository.delete(form);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
