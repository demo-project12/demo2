package com.example.demo.service;

import com.example.demo.model.CommonUserResult;
import com.example.demo.util.ServerStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResponseService {

    // failResult
    public CommonUserResult getFailUserResult() {
        CommonUserResult commonUserResult = new CommonUserResult();
        commonUserResult.setStatusCode(ServerStatus.ERROR_CODE);
        commonUserResult.setStatusMessage(ServerStatus.ERROR_MESSAGE);

        return commonUserResult;
    }

    // successResult
    public CommonUserResult getSuccessUserResult() {
        CommonUserResult commonUserResult = new CommonUserResult();
        commonUserResult.setStatusCode(ServerStatus.SUCCESS_CODE);
        commonUserResult.setStatusMessage(ServerStatus.SUCCESS_MESSAGE);

        return commonUserResult;
    }
}
