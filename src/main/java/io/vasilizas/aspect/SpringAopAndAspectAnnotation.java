package io.vasilizas.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Slf4j
@Component
public class SpringAopAndAspectAnnotation {
    @Pointcut("execution(* io.vasilizas.controller.*.*(..))")
    public void controllers() {
        // pointcut
    }

    @Before("controllers()")
    public void before(JoinPoint jp) {
        log.debug("----------------");
        log.debug("Logging before controller's method: {}", jp.getSignature().toShortString());
    }

    @AfterReturning("controllers()")
    public void after(JoinPoint jp) {
        log.debug("----------------");
        log.debug("Logging after controller's method: {}", jp.getSignature().toShortString());
    }

    @AfterThrowing(value = "controllers()", throwing = "exception")
    public void afterThrowingException(JoinPoint jp, Exception exception) {
        log.info("----------------------------");
        log.info("Logging exception in method: {}, {}", jp.getSignature().toShortString(), exception.toString());
    }


    @Around("execution(public String io.vasilizas.controller.*.*(..))")
    public String catchExceptionWhereReturningStringAndRedirectToErrorPage(ProceedingJoinPoint pjp) {
        String result;
        try {
            result = (String) pjp.proceed();
        } catch (Throwable e) {
            log.error(e.toString());
            result = "error";
        }
        return result;
    }

    @Around("@annotation(io.vasilizas.myannotation.MyPrintAnnotation)")
    public void printBeforeAndAfterMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("********************************************************************************");
        pjp.proceed();
        log.info("********************************************************************************");
    }

    @Around("@annotation(io.vasilizas.myannotation.MyAopExceptionAnnotation)")
    public ModelAndView catchExceptionAndRedirectToErrorPage(ProceedingJoinPoint pjp) {
        ModelAndView result = new ModelAndView();
        try {
            result = (ModelAndView) pjp.proceed();
        } catch (Throwable e) {
            log.error(e.toString());
            result.setViewName("error");
        }
        return result;
    }
}
