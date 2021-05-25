package org.lumenk.object.webserver.controllers.api;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;
import org.lumenk.object.webserver.entities.dto.FormDto;
import org.lumenk.object.webserver.repositories.FormRepository;
import org.lumenk.object.webserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class FormCRUDAPI {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/api/form/list")
    public ResponseEntity<FormDto[]> formList(){
        ArrayList<Form> formArrayList = new ArrayList<Form>();
        formRepository.findAll().iterator().forEachRemaining(formArrayList::add);
        final int size = formArrayList.size();
        FormDto[] formDtos = new FormDto[size];
        for(int i = 0; i < size; i++)
            formDtos[i] = new FormDto(formArrayList.get(i));
        return new ResponseEntity<FormDto[]>(formDtos, HttpStatus.OK);
    }

    @GetMapping("/api/form/get")
    public ResponseEntity<FormDto> getForm(@RequestBody Form form){
        Optional<Form> optionalForm = formRepository.findById(form.getId());

        if(optionalForm.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return  new ResponseEntity<>(new FormDto(optionalForm.get()), HttpStatus.OK);
    }

    /*@PostMapping("/api/form/add")
    public ResponseEntity<String> addForm(@RequestBody FormDto formDto){

        if(null != formDto.getId())
            return new ResponseEntity<>("신규 설문조사 id는 지정할 수 없습니다.", HttpStatus.BAD_REQUEST);
        if(null == formDto.getOwner())
            return new ResponseEntity<>("소유자 없는 설문조사는 존재할 수 없습니다", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(formDto.getOwner());

        if(optionalUser.isEmpty())
            return new ResponseEntity<>("존재하지 않는 것을 소유자로 지정할 수 없습니다", HttpStatus.BAD_REQUEST);

        Form form = Form.builder().owner(optionalUser.get()).title(formDto.getTitle()).build();

        formRepository.save(form);

        return new ResponseEntity<>("성공적으로 등록되었습니다", HttpStatus.CREATED);
    }
     */

    @PostMapping("/api/form/delete")
    public ResponseEntity<String> deleteForm(@RequestBody FormDto formDto){
        if(null == formDto.getId())
            return new ResponseEntity<>("뭘 삭제할건지 말을 해야지 지워줌", HttpStatus.BAD_REQUEST);
        if(null == formDto.getOwner())
            return new ResponseEntity<>("지울거면 일단 본인 ID를 말하셈", HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findById(formDto.getOwner());
        Optional<Form> optionalForm = formRepository.findById(formDto.getId());

        if(optionalUser.isEmpty())
            return new ResponseEntity<>("당신은 없는 사람임", HttpStatus.BAD_REQUEST);
        if(optionalForm.isEmpty())
            return new ResponseEntity<>("존재하지 않는 설문조사를 어케지워", HttpStatus.BAD_REQUEST);

        if(!optionalForm.get().getOwner().getId().equals(optionalUser.get().getId()))
            return new ResponseEntity<>("당신이 만든 설문조사가 아님", HttpStatus.BAD_REQUEST);

        formRepository.delete(optionalForm.get());
        return new ResponseEntity<>("성공적으로 삭제되었습니다", HttpStatus.OK);
    }
}
