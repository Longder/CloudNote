package com.longder.note.test;

import com.longder.note.aop.DebugLogerBean;
import com.longder.note.controller.user.UserController;
import com.longder.note.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Longder on 2016/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestAOP {
    @Resource
    private UserController controller;
    @Resource
    private UserService service;
    @Resource
    private DebugLogerBean bean;
    @Test
    public void test1() {
        System.out.println(controller.getClass().getName());
        System.out.println(service.getClass().getName());
        System.out.println(bean.getClass().getName());
    }
}
