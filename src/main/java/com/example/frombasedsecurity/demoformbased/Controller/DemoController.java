package com.example.frombasedsecurity.demoformbased.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/apiuser")
    public String helloUser()
    {
        return "hello user!!! welcome";
    }
    @GetMapping("/apiadmin")
    public String helloAdmin()
    {
        return "hello admin!!! welcome";
    }
}
