package ru.neoflex.dev.spring.rename_bean.stuff;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessor implements BeanPostProcessor {
	
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		if (beanName.equals("shapeShifter")) {
			((ShapeShifter) bean).setMyProp(10);
		}
						
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

}
