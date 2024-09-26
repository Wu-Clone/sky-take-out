package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
// 为所有RestController 控制器提供全局的异常处理功能
    // 能够捕获控制器中的异常
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
// http://localhost:8080/doc.html#/home
    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    //java.sql.SQLIntegrityConstraintViolationException:
    // Duplicate entry 'test' for key 'employee.idx_username'
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")){
            String name = message.split(" ")[2];
            String msg = name + " " + MessageConstant.ALREADY_EXISTS;
            log.error(msg);
            return Result.error(msg);
        }
        log.error(ex.getClass() + " " + message);
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

}
