package ru.neoflex.dev.spring.aop_method.stuff;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public MyService() {};

    @MyAnnotation // add our annotation //
    public void printStuff() {
        System.out.println("Printing...");
    }
}
