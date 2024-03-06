package com.kxj.cloud.exp;

import com.kxj.cloud.resp.ResultData;
import com.kxj.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exceptionHandler(RuntimeException e){
        System.out.println("全局异常处理");
        log.error("全局异常处理:{}",e.getMessage(),e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }

}
