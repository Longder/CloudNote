package com.longder.note.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 将异常记录文件，当切面使用
 * Created by Longder on 2016/1/19.
 */
@Component
@Aspect
public class ExceptionHandler {
    /**
     * @param ex 目标组件抛出的异常对象
     */
    @AfterThrowing(throwing = "ex", pointcut = "within(com.longder.note.controller..*)")
    public void handler(Exception ex) {
        System.out.println("哼哼哈嘿横");
        System.out.println("发生异常了！" + ex);
    }
}
