package com.example.demo.helloworld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //GET
    // endpoint 이름 / hello-world
    @GetMapping(path = "/hello-world") // = @RequestMapping()
    public String helloWorld() {
        return "Hello World";
    }

    //alt + enter
    @GetMapping(path = "/hello-world-bean") // = @RequestMapping()
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean ("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}") // = @RequestMapping()
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean (String.format("Hello World, %s ", name));
    }

    @GetMapping(path = "/hello-world-internationalized") //다국어지원
    public String helloWorldInternationalized(
            @RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("greeting.messages", null,locale);
    }
}
