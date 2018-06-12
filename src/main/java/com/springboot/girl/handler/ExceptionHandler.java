package com.springboot.girl.handler;

import com.springboot.girl.domain.Result;
import com.springboot.girl.enums.ResultEnum;
import com.springboot.girl.exception.GirlException;
import com.springboot.girl.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handler(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getMessage());
        }
        return ResultUtil.error(ResultEnum.UNKONW_ERROR);
    }
}
