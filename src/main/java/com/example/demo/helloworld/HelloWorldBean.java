package com.example.demo.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //set,get 메소드 자동으로 설치
@AllArgsConstructor
public class HelloWorldBean {
    private String message;

}
