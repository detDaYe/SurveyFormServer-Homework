package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserUpdateAPI {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user/update")
    public ResponseEntity<String> userUpdate(@RequestBody User user){

        if(null == user.getId())
            return  new ResponseEntity<>("user id is empty", HttpStatus.BAD_REQUEST);
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        userRepository.save(user);
        return new ResponseEntity<>("updated", HttpStatus.NO_CONTENT);
    }
}
