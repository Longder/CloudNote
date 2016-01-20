package com.longder.note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 封装打桩信息，将来当切面组件使用
 * Created by Longder on 2016/1/19.
 */
@Component
@Aspect//声明为切面组件
public class DebugLogerBean {
    @Before("within(com.longder.note.controller..*)")
    public void debugController() {
        System.out.println("进入controller");
    }

    @Before("within(com.longder.note.service..*)")
    public void debugService() {
        System.out.println("进入service");
    }
}
