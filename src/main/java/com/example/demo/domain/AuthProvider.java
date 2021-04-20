package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthProvider {
    local, // 자사 사이트 가입자
    google,
    naver,
    facebook,
    kakao
}
