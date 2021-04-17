package com.example.demo.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    @Value("${front-uri}")
    private String frontUri;

    @GetMapping("/")
    public String redirect2Front() {
        return "redirect:" + frontUri;
    }
}
