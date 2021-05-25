package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.util.Optional;

@RestController
public class UserDeleteAPI {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        if(null == id)
            return new ResponseEntity<>("id MUST NOT BE NULL", HttpStatus.BAD_REQUEST);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            return new ResponseEntity<>("user not exists", HttpStatus.BAD_REQUEST);
        userRepository.delete(optionalUser.get());
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
