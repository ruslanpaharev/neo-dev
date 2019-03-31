package ru.neoflex.dev.spring.env_on_startup;

import java.util.Arrays;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
//import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
//import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
//import org.springframework.core.env.PropertySource;

public class MyListener implements ApplicationListener<ApplicationStartedEvent>{

	/* 
	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		
		Map<String, String> propMap = new HashMap<>();
		MutablePropertySources source = event.getApplicationContext().getEnvironment().getPropertySources();
								
		Iterator<PropertySource<?>> iter = source.iterator();
		
		while(iter.hasNext()) {
			
			PropertySource<?> src = iter.next();
			//propMap.put(src.getName(), (String) src.getProperty(src.getName()));
			propMap.put(src.getName(), src.getSource().toString());
			System.out.println(src.getName() + " = " + src.getSource().toString());
		}
		
		SpringBootEnvOnStartupRunner.FileWriter.writeProps(propMap);
	}
	*/
	
	@Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
		
        Map<String, String> properties = new HashMap<String, String>();
        
        ConfigurableEnvironment environment = applicationStartedEvent.getApplicationContext().getEnvironment();
        
        MutablePropertySources propertiesSources = environment.getPropertySources();

        StreamSupport.stream(propertiesSources.spliterator(), false)
                .filter(item -> item instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource<?>) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .forEach(propertyName -> properties.put(propertyName, environment.getProperty(propertyName)));

        SpringBootEnvOnStartupRunner.FileWriter.writeProps(properties);
    }
	
}
