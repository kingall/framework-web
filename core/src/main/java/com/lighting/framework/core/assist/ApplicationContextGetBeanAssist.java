package com.lighting.framework.core.assist;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器中的bean对象
 * @author c.wei
 *
 */
@Component
public class ApplicationContextGetBeanAssist implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextGetBeanAssist.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
     * 根据默认bean名称获取bean对象
     * @param className
     * @return
     * @throws BeansException
     * @throws IllegalArgumentException
     */
    public static Object getBeanByDefaultBeanName(String className) throws BeansException,IllegalArgumentException {
        if(className==null || className.length()<=0) {
            //throw new IllegalArgumentException("className为空");
            return null;
        }

        String beanName = null;
        if(className.length() > 1) {
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        } else {
            beanName = className.toLowerCase();
        }
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }

    /**
     * 根据自定义bean名称获取对象
     * @param customName
     * @return
     * @throws BeansException
     * @throws IllegalArgumentException
     */
    public static Object getBeanBycCustomName(String customName) throws BeansException,IllegalArgumentException {
        if(customName==null || customName.length()<=0) {
            throw new IllegalArgumentException("className为空");
        }
        return applicationContext != null ? applicationContext.getBean(customName) : null;
    }

}
