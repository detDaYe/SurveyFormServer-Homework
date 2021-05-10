package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserCRUDAPI {

    private final UserRepository userRepository;

    public UserCRUDAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/api/user/get")
    public ResponseEntity<User> readUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<User>(optionalUser.get(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/add")
    public ResponseEntity addUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent())
            return new ResponseEntity("user already exists", HttpStatus.BAD_REQUEST);
        userRepository.save(user);
        return new ResponseEntity("sucess", HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/update")
    public ResponseEntity updateUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity("user not exists", HttpStatus.BAD_REQUEST);
        userRepository.save(user);
        return new ResponseEntity("sucess", HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/delete")
    public ResponseEntity deleteUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if(optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return new ResponseEntity("success", HttpStatus.ACCEPTED);
        }else return new ResponseEntity("user not found", HttpStatus.NO_CONTENT);
    }
}
