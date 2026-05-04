package com.gym.member.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect @Component @Slf4j
public class LoggingAspect {

    @Around("execution(* com.gym.member.service.*.*(..))")
    public Object logExecution(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        String method = pjp.getSignature().getName();
        log.info("[MEMBER] >> {}", method);
        try {
            Object result = pjp.proceed();
            log.info("[MEMBER] << {} ({}ms)", method, System.currentTimeMillis() - start);
            return result;
        } catch (Exception e) {
            log.error("[MEMBER] !! {} failed: {}", method, e.getMessage());
            throw e;
        }
    }
}
