package saki.backend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saki.backend.annotation.LogInvoke;
import saki.backend.utils.BeanUtil;

import java.lang.reflect.Method;

/**
 * Created by deepglint on 16/7/15.
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* saki.backend..*.*(..)) && @annotation(saki.backend.annotation.LogInvoke))")
    private void logInvoke(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogInvoke logAnt = signature.getMethod().getAnnotation(LogInvoke.class);
        StringBuilder sb = new StringBuilder();
        sb.append("Invoke method "+method.getDeclaringClass().getName()+"."+method.getName()+" with param: ");
        String[] params = signature.getParameterNames();
        Object[] values = point.getArgs();
        for(String logParam: logAnt.params()) {
            for(int i=0; i<params.length; i++) {
                if (logParam.equals(params[i])) {
                    sb.append("{").append(params[i]).append(": ");
                    if (values[i] instanceof Integer || values[i] instanceof Short ||
                            values[i] instanceof String || values[i] instanceof Long ||
                            values[i] instanceof Boolean || values[i] instanceof Byte ||
                            values[i] instanceof Float || values[i] instanceof Double) {
                        sb.append(values[i]);
                    } else {
                        sb.append(BeanUtil.toMap(values[i]).toString());
                    }
                    sb.append("} ");
                    break;
                }
            }
        }
        LOGGER.info(sb.toString());
    }

    @Around("execution(* saki.backend..*.*(..)) && @annotation(saki.backend.annotation.LogExeTime))")
    private Object logExeTime(ProceedingJoinPoint point) throws Throwable {
        long now = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("Method "+method.getDeclaringClass().getName()+"."+method.getName()+" executed time: " + (end-now) + "ms");
        return result;
    }
}
