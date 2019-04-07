package ru.neoflex.dev.spring.pass_filter_params.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @PostMapping("/testcall1")
    public String testCallOne( @RequestBody MyObject request) { // add @RequestBody annotation // 
        return request.getDate() + request.getOtherValue() + request.getVeryOtherValue();
    }

    @GetMapping("/testcall2/{path}")
    public String testCallTwo(@PathVariable int path, FilterParams filter) { // add @PathVariable annotation //
        return path + filter.getAge() + filter.getName();
    }
}
