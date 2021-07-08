package com.algaworks.algafood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerTeste {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @GetMapping("/world")
    @ResponseBody
    public String world() {
        return "world";
    }

    @GetMapping("/bird")
    @ResponseBody
    public String bird() {
        return "bird";
    }
}
