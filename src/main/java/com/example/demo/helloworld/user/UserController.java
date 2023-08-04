package com.example.demo.helloworld.user;

import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;  //생성자로 의존성주입
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){ //사용자전원반환
        return service.findAll();
    }

    //GET users/1 ->문자형태로전달
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){  //사용자한명씩반환
        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException(String.format("id{%s} not found", id));
        }

        //HATEOAS
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user){  //사용자추가
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//201 created
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteUserId(id);

        if(user == null){
            throw new UserNotFoundException(String.format("id{%s} not found", id));
        }
    }
}
