package com.test.marcopolo.controller;

import com.test.marcopolo.model.LoginContext;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log
public class LoginController {

    @PostMapping(path = "/login")
    public boolean login(@Valid @RequestBody LoginContext loginContext){
       if(loginContext.getUsername().equalsIgnoreCase("marco")
        && loginContext.getPassword().equalsIgnoreCase("polo")){
           log.info("Authorized User");
            return true;
        } else{
            log.info("UnAuthorized User");
            return false;
        }
    }
}
