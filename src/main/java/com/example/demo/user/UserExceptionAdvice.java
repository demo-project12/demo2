package com.example.demo.user;

import com.example.demo.user.model.CommonUserResult;
import com.example.demo.user.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class UserExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserException.class)
    public CommonUserResult userException() {
        log.error("UserExceptionAdvice userException()");

        return responseService.getFailUserResult();
    }
}
