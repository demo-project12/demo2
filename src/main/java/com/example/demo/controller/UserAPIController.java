package com.example.demo.controller;

import com.example.demo.annotation.LoginUser;
import com.example.demo.domain.SessionUser;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserAPIController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@LoginUser SessionUser user) {

        log.info("user : `{}`", user.toString());

        return ResponseEntity.ok(new JSONObject().appendField("result", true));
    }
}
