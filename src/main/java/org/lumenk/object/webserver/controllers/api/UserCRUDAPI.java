package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.UserDto;
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
    public ResponseEntity<UserDto> readUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<UserDto>(new UserDto(optionalUser.get()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/add")
    public ResponseEntity<String> addUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent())
            return new ResponseEntity<String>("user already exists", HttpStatus.BAD_REQUEST);
        userRepository.save(user);
        return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/update")
    public ResponseEntity<String> updateUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity<String>("user not exists", HttpStatus.BAD_REQUEST);
        userRepository.save(user);
        return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/user/delete")
    public ResponseEntity<String> deleteUserRequest(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if(optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        }else return new ResponseEntity<String>("user not found", HttpStatus.NO_CONTENT);
    }
}
