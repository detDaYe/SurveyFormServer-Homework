package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserCreateAPI {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user/create")
    public ResponseEntity<String> userCreate(@RequestParam("id")String id, @RequestParam("name") String name){
        if(null == id)
            return new ResponseEntity<>("id cannot be NULL", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent())
            return new ResponseEntity<>("id already exists", HttpStatus.BAD_REQUEST);

        User user = User.builder()
                .id(id)
                .name(name)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>("created", HttpStatus.CREATED);

    }
}
