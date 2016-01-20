package com.longder.note.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试Spring容器
 * Created by Longder on 2016/1/8.
 */
public class SpringTest {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    /**
     * 测试连接池
     *
     * @throws SQLException
     */
    @Test
    public void testDataSrouce() throws SQLException {
        BasicDataSource ds = ac.getBean(BasicDataSource.class);
        Connection con = ds.getConnection();
        Assert.assertNotNull(con);
    }

    /**
     * 测试Spring和Mybatis整合
     */
    @Test
    public void testMyBatisSpring() {
        SqlSessionFactoryBean bean = ac.getBean(SqlSessionFactoryBean.class);
        Assert.assertNotNull(bean);
    }
}
