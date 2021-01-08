package org.unicome.data.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.unicome.data.msg.ResultResponse;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResultResponse().success(false).msg(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultResponse handleException(HttpServletResponse response, Exception e) {
        response.setStatus(200);
        log.info(e.getMessage(), e);
        return new ResultResponse().success(false).code(500).msg(e.getMessage());
    }
}
