package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author qin
 * @create 2019-04-15 10:17
 */
@ControllerAdvice
@Slf4j
public class BasicExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        //记录异常信息
        log.error(e.getMessage(),e);
        if(e instanceof LyException){
            LyException e2 = (LyException) e;
            int code = e2.getStatus() == null ? e2.getStatusCode() : e2.getStatus().value();
            return ResponseEntity.status(code).body(e.getMessage());
        }
        // 其它情况，返回500
        return ResponseEntity.status(500).body("未知错误！");


    }
}
