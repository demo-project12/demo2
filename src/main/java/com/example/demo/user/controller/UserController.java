package com.example.demo.user.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.model.CommonUserResult;
import com.example.demo.user.service.ResponseService;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
