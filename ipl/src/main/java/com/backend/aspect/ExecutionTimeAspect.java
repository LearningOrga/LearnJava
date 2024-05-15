package com.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class ExecutionTimeAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.backend.model.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long currentTime = System.currentTimeMillis();
        double timeInsec = (double) currentTime / 1000;

        Object proceed = joinPoint.proceed();

        long currentTimeAfter = System.currentTimeMillis();
        double timeInsecAfter = (double) currentTime / 1000;

        double diff = timeInsec - timeInsecAfter;

        logger.debug("Time it took -" + String.valueOf(diff));
        return proceed;
    }


}
