package com.lighting.framework.core.configure;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * Jackson时间格式转换
 */
@Configuration
public class JacksonDateConfig {
    public static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public static String shortDateFormat = "yyyy-MM-dd";

    public static String timeFormat = "HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){

        return builder ->{
            builder.simpleDateFormat(dateFormat);
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(shortDateFormat)));
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));
        };
    }
}
