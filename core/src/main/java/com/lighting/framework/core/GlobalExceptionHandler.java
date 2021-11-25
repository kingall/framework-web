package com.lighting.framework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Controller类全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse toExceptionHandler(Exception e){
        logger.error(e.getLocalizedMessage(),e);
        BaseResponse baseResponse = new BaseResponse(-1, "未知错误");
        return baseResponse;
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public BaseResponse toSQLExceptionHandler(SQLException e){
        logger.error(e.getLocalizedMessage(),e);
        BaseResponse baseResponse = new BaseResponse(-1, "数据库操作错误");
        return baseResponse;
    }

}
