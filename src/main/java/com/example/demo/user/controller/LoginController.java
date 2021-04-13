package com.example.demo.user.controller;

import com.example.demo.annotation.LoginUser;
import com.example.demo.user.domain.SessionUser;
import com.example.demo.user.model.CommonUserResult;
import com.example.demo.user.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final ResponseService responseService;

    @GetMapping("/")
//    public ResponseEntity<?> index(@LoginUser SessionUser user) {
    public CommonUserResult index() {
        return responseService.getSuccessUserResult();
    }
}
