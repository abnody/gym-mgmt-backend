package com.gym.reporting.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect @Component @Slf4j
public class LoggingAspect {
    @Around("execution(* com.gym.reporting..*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        long s = System.currentTimeMillis();
        try { Object r = pjp.proceed(); log.info("[REPORTING] {} ({}ms)", pjp.getSignature().getName(), System.currentTimeMillis()-s); return r; }
        catch (Exception e) { log.error("[REPORTING] {} failed: {}", pjp.getSignature().getName(), e.getMessage()); throw e; }
    }
}
