package com.als.webIde.DTO.etc;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR(1001, "로그인 후 이용할실 수 있습니다."),
    WRONG_TYPE_TOKEN(1002, "변조된 토큰입니다."),
    EXPIRED_TOKEN(1003, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(1004, "변조된 토큰입니다."),
    ACCESS_DENIED(1005, "권한이 없습니다.");


    ErrorCode (int status, String message) {
        this.code = status;
        this.message = message;
    }

    private int code;
    private String message;

}
