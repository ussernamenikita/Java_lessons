package com.leti.social_net.config;


import com.leti.social_net.commands.NotAuthorized;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect usage
 */
@Aspect
@Component
public class LoggingAspect {
    private static Logger logger = Logger.getLogger(LoggingAspect.class);

    @Around("execution(* com.leti.social_net.commands.impl.*.execute(..))")
    public Object logging(ProceedingJoinPoint point) throws Throwable {
        logger.info("Starting method "
                        +point.getSignature()
                        +" of object "
                        +point.getTarget()
                        +" with arguments "
                        +Arrays.toString(point.getArgs()));
        Object result = null;
        try {
           result = point.proceed();
        }catch (NotAuthorized notAuthorized)
        {
            System.out.println(notAuthorized.getMessage());
        }
        logger.info("The result "+result);
        return result;
    }
}
