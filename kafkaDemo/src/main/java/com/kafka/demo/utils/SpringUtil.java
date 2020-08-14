package com.kafka.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}

	/**
	 * @Title: getApplicationContext
	 * @Description: 获取applicationContext
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @Title: getBean
	 * @Description: 通过name获取 Bean.
	 * @param name
	 *            Bean名称
	 * @return Object
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * @Title: getBean
	 * @Description: 通过class获取Bean.
	 * @param clazz
	 *            Class
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * @Title: getBean
	 * @Description: 通过name,以及Clazz返回指定的Bean
	 * @param name
	 *            bean名称
	 * @param clazz
	 *            Class
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

}
