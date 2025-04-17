package com.mysite.backend.controller;//현재 클래스의 위치(패키지 경로)를 지정함
import org.springframework.web.bind.annotation.GetMapping;//GetMapping 어노테이션을 사용하기 위해 필요한 import문
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class HelloController {
    @GetMapping("/")
    public String hello() {//실제 /hello경로로 요청이 왔을 때 실행될 메서드
        return "Hello World";
    }
}
