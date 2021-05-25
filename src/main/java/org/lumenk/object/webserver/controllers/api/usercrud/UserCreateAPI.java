package org.lumenk.object.webserver.controllers.api.usercrud;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.UserDto;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserCreateAPI {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user/create")
    public ResponseEntity<String> userCreate(@RequestBody User user){

        if(user.getId() == null)  return new ResponseEntity<>("ID must not Empty", HttpStatus.BAD_REQUEST);
        if(user.getId().equals("")) return new ResponseEntity<>("ID must not Empty", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) return new ResponseEntity<>("same id already exists", HttpStatus.BAD_REQUEST);

        if(user.getName() == null) user.setName("");

        userRepository.save(user);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
