package com.kxj.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData(T data) {
        this.data=data;
        this.timestamp = System.currentTimeMillis();
    }
    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(data).
                setCode(ReturnCodeEnum.RC200.getCode()).
                setMessage(ReturnCodeEnum.RC200.getMessage());
    }

    public static <T> ResultData<T> fail(String code, String message) {
        return new ResultData<T>().
                setCode(code).
                setMessage(message);
    }

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }
}
