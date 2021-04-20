package com.example.demo.controller;

import com.example.demo.annotation.LoginUser;
import com.example.demo.domain.SessionUser;
import com.example.demo.domain.User;
import com.example.demo.model.CommonUserResult;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.ResponseService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/user")
    public CommonUserResult saveUser(@RequestBody User user) {
        log.info("UserController saveUser() user : '{}'", user.toString());
        userService.saveUser(user);

        return responseService.getSuccessUserResult();
    }

    @GetMapping("/user")
    public User test(@LoginUser SessionUser user) {
        return userService.getLoginUser(user);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@LoginUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal);
    }
}
