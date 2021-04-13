package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author swjeon
 * @desc 로그인 유저 정보를 받아오기 위한 어노테이션 생성
 * @since (2021-04-13 13:18:59)
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
