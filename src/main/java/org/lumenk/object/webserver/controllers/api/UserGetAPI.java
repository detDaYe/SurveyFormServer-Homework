package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.UserDto;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserGetAPI {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user/get/{id}")
    public ResponseEntity<UserDto> userGet(@PathVariable("id") String id){
        if(null == id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new UserDto(optionalUser.get()), HttpStatus.OK);

    }
}
