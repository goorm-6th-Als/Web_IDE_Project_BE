package com.als.webIde.config;

import com.als.webIde.DTO.etc.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Integer exception = (Integer)request.getAttribute("exception");

        if(exception == null) {
            setResponse(response, ErrorCode.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception == 1004) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception == 1005) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception == 1006) {
            setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        }
        else {
            setResponse(response, ErrorCode.ACCESS_DENIED);
        }
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorCode exceptionCode) throws IOException, JSONException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(responseJson);
    }
}