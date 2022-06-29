package com.lighting.framework.core;

import com.lighting.framework.core.configure.JacksonDateConfig;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 基础的Controller类
 */
public class BaseController {

	public <T> StandardResponse<T> buildStandardResponse(T t) {
		StandardResponse<T> response = new StandardResponse<T>(0, "success");
		response.setData(t);
		return response;
	}

	public <T>RowsResponse<T> buildRowsResponse(Iterable<T> list) {
		RowsResponse<T> response = new RowsResponse<T>(0, "success");
		response.setRows(list);
		return response;
	}

	public <T> StandardResponse<T> buildRowsResponse(T page) {
		StandardResponse<T> response = new StandardResponse<T>(0, "success");
		response.setData(page);
		return response;
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
