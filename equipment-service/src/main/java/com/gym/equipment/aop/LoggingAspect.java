package com.gym.equipment.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect @Component @Slf4j
public class LoggingAspect {
    @Around("execution(* com.gym.equipment.service.*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        long s = System.currentTimeMillis();
        try {
            Object r = pjp.proceed();
            log.info("[EQUIPMENT] {} ({}ms)", pjp.getSignature().getName(), System.currentTimeMillis()-s);
            return r;
        } catch (Exception e) {
            log.error("[EQUIPMENT] {} failed: {}", pjp.getSignature().getName(), e.getMessage()); throw e;
        }
    }
}
