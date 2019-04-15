package com.leyou.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author qin
 * @create 2019-04-15 10:25
 */
@Data
public class LyException extends RuntimeException {

    /*响应状态对象*/
    private HttpStatus status;

    /* 响应状态码*/
    private int statusCode;

    public LyException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public LyException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}

