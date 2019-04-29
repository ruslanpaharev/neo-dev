package ru.neoflex.dev.spring.paging_sorting_query;

import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.springframework.data.domain.PageRequest;  
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.stereotype.Component;

import java.util.Arrays;  
import java.util.List;  
import java.util.stream.Collectors;  
import java.util.stream.StreamSupport;


public class SortManipulating {

    public static List<Sort.Order> toOrderStream(Sort sort, boolean ignorecase, NullHandling nulhandling) {
    	
        	
    	if (ignorecase){
    		return StreamSupport.stream(sort.spliterator(), false)
				 	.map(Sort.Order::ignoreCase).map(a -> a.with(nulhandling))
	                .collect(Collectors.toList());
    	}
    	
    	return StreamSupport.stream(sort.spliterator(), false)
  				 .map(a -> a.with(nulhandling))
  	                .collect(Collectors.toList());
    	
    	
    }

}