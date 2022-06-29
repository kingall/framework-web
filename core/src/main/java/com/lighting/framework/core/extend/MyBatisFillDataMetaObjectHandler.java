package com.lighting.framework.core.extend;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 数据库实体自动填充
 */
public class MyBatisFillDataMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取数据原始对象
        Class<?> clazz = metaObject.getOriginalObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TableField tableField = field.getAnnotation(TableField.class);
            switch (tableField.fill()) {
                case INSERT:
                    setFieldVal(field, metaObject);
                    break;
                case INSERT_UPDATE:
                    setFieldVal(field, metaObject);
                    break;
            }
        }
    }

    private void setFieldVal(Field field, MetaObject metaObject) {
        if (field.getType().equals(LocalDateTime.class)) {
            this.setFieldValByName(field.getName(), LocalDateTime.now(), metaObject);
        }

        if (field.getType().equals(LocalDate.class)) {
            this.setFieldValByName(field.getName(), LocalDate.now(), metaObject);
        }

        if (field.getType().equals(Date.class)) {
            this.setFieldValByName(field.getName(), new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取数据原始对象
        Class<?> clazz = metaObject.getOriginalObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TableField tableField = field.getAnnotation(TableField.class);
            switch (tableField.fill()) {
                case UPDATE:
                    setFieldVal(field, metaObject);
                    break;
                case INSERT_UPDATE:
                    setFieldVal(field, metaObject);
                    break;
            }
        }
    }
}
