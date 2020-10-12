package com.neo.config;

import com.neo.mapper.CodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public enum testEnum  {
    PARAM_ERROR(1, "参数不正确"),
    ;
    private Integer code;
    private String message;



    testEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }



}
