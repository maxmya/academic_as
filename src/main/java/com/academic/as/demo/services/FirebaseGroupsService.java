package com.academic.as.demo.services;

import org.springframework.stereotype.Service;

@Service
public class FirebaseGroupsService {


    public String doSomething() throws InterruptedException {

        Thread.sleep(1000);

        return "hello";
    }


}
