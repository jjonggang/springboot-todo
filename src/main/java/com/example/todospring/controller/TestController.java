package com.example.todospring.controller;

import com.example.todospring.dto.ResponseDTO;
import com.example.todospring.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // http와 관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다.
@RequestMapping("/test") // 리소스
public class TestController {
    @GetMapping("/testGetMapping")
    public String testController(){
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required=false) int id){
        return "Hello World" + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required=false)int id){
        return "Hello World! ID: "+id;
    }

    @GetMapping("/testRequestBody")
    public String testRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! ID"+testRequestBodyDTO.getId()+"Message: "+testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        // http status를 400으로 설정
//        return ResponseEntity.badRequest().body(response);
        return ResponseEntity.ok().body(response);
    }
}
