package io.pivotal.cf.cassandra.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TouchController {

    @RequestMapping(value = "/touch", method = RequestMethod.GET)
    public String touch() {
        return "Goodbye World!";
    }

}
