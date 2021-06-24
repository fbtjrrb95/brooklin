package me.screw.brooklin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        String responseString = "hello";
        return ResponseEntity.ok(responseString);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        String responseString = "test_completed";

        return ResponseEntity.ok(responseString);
    }

}
