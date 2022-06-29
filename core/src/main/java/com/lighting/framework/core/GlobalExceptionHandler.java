package com.lighting.framework.core;

import com.lighting.framework.core.configure.JacksonDateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Controller类全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse toExceptionHandler(Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        BaseResponse baseResponse = new BaseResponse(-1, "未知错误");
        return baseResponse;
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public BaseResponse toSQLExceptionHandler(SQLException e) {
        logger.error(e.getLocalizedMessage(), e);
        BaseResponse baseResponse = new BaseResponse(-1, "数据库操作错误");
        return baseResponse;
    }

    @InitBinder
    protected void initData(WebDataBinder binder) { // 添加创建人信息

        // 普通data格式转换
        binder.registerCustomEditor(Date.class, new PropertiesEditor() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    if ("".equals(text)) {
                        super.setValue(null);
                    } else {
                        super.setValue(new SimpleDateFormat(JacksonDateConfig.dateFormat).parseObject(text));
                    }
                } catch (ParseException e) {
                    super.setAsText(text);
                }
            }
        });

        // LocalDate时间格式转换
        binder.registerCustomEditor(LocalDate.class, new PropertiesEditor() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if ("".equals(text)) {
                    super.setValue(null);
                } else {
                    super.setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(JacksonDateConfig.shortDateFormat)));
                }
            }
        });
        // LocalDateTime时间格式转换
        binder.registerCustomEditor(LocalDateTime.class, new PropertiesEditor() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if ("".equals(text)) {
                    super.setValue(null);
                } else {
                    super.setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(JacksonDateConfig.dateFormat)));
                }

            }
        });

        // LocalDateTime时间格式转换
        binder.registerCustomEditor(LocalTime.class, new PropertiesEditor() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if ("".equals(text)) {
                    super.setValue(null);
                } else {
                    super.setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(JacksonDateConfig.timeFormat)));
                }

            }
        });
    }
}
