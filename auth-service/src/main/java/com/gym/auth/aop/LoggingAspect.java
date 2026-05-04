package com.gym.auth.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP Aspect for logging and performance monitoring across all service methods.
 * Applied to all classes in the service layer automatically.
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.gym.auth.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        log.info("[AUTH] >> Executing: {}", methodName);
        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            log.info("[AUTH] << Completed: {} in {}ms", methodName, elapsed);
            return result;
        } catch (Exception ex) {
            log.error("[AUTH] !! Exception in {}: {}", methodName, ex.getMessage());
            throw ex;
        }
    }
}
