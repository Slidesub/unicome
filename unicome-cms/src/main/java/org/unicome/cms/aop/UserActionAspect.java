package org.unicome.cms.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserActionAspect {
//    第一个*: 返回类型，表示所有的返回类型
//    第二个*: 类名，表示当前包和当前包下所有子包的所有类
//    第三个*: 方法名，表示所有方法
//    (..): 参数，表示任何参数
    @Pointcut("execution(* org.unicome.cms.service.*.*(..))")
    public void userActionAspect() {}

    @After("userActionAspect()")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("代理的对象：" + joinPoint.getTarget());
        log.info("调用的方法：" + joinPoint.getSignature().getName());
        log.info("参数：" + args.toString());
    }
}
