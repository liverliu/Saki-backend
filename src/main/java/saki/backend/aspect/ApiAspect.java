package saki.backend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saki.backend.annotation.CheckEmpty;
import saki.backend.annotation.LogInvoke;
import saki.backend.entity.Result;

import java.lang.reflect.Method;

/**
 * Created by deepglint on 16/7/15.
 */
@Component
@Aspect
public class ApiAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAspect.class);

    @Around("execution(* saki.backend.api..*.*(..)) && @annotation(saki.backend.annotation.CheckEmpty))")
    private Object checkParam(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        CheckEmpty ant = signature.getMethod().getAnnotation(CheckEmpty.class);
        String[] params = signature.getParameterNames();
        Object[] values = point.getArgs();
        String lack = "";
        for(String logParam: ant.params()) {
            boolean find = false;
            for (int i=0; i<params.length; i++) {
                if (logParam.equals(params[i])) {
                    find = (values[i] != null);
                    if (values[i] instanceof String && "".equals(values[i])) {
                        find = false;
                    }
                    break;
                }
            }
            if (!find) {
                lack = logParam;
                break;
            }
        }
        if (!"".equals(lack)) {
            LOGGER.error("缺少参数: "+ lack);
            return new Result<>(-1, "缺少参数: "+ lack, "");
        }
        return point.proceed();
    }
}
