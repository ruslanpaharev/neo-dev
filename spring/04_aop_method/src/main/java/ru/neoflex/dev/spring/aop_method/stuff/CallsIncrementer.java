package ru.neoflex.dev.spring.aop_method.stuff;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Aspect // mark as Aspect //
public class CallsIncrementer {

    private AtomicInteger callsBeforeCount;
    private AtomicInteger callsAfterCount;

    public CallsIncrementer() {
        this.callsAfterCount = new AtomicInteger(0);
        this.callsBeforeCount = new AtomicInteger(0);
    }
        
    @Pointcut("@annotation(ru.neoflex.dev.spring.aop_method.stuff.MyAnnotation)") // add Pointcut annotation //
    public void annotated() {}

    @Before("annotated()")  // add Before annotation //
    public void incrementCallsBefore() {
        this.callsBeforeCount.incrementAndGet();
    }

    @After("annotated()")  // add After annotation //
    public void incrementCallsAfter() {
        this.callsAfterCount.incrementAndGet();
    }

    public int getCallsBeforeCount() {
        return callsBeforeCount.get();
    }

    public int getCallsAfterCount() {
        return callsAfterCount.get();
    }
}
