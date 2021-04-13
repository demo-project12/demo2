package com.example.demo.user.controller;

import com.example.demo.annotation.LoginUser;
import com.example.demo.user.domain.SessionUser;
import com.example.demo.user.domain.User;
import com.example.demo.user.model.CommonUserResult;
import com.example.demo.user.service.ResponseService;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("user")
    public CommonUserResult saveUser(@RequestBody User user) {
        log.info("UserController saveUser() user : '{}'", user.toString());
        userService.saveUser(user);

        return responseService.getSuccessUserResult();
    }

    @GetMapping("/user")
    public ResponseEntity<?> test(@LoginUser SessionUser user) {
        return ResponseEntity.ok(new JSONObject().appendField("result", true));
    }
}
