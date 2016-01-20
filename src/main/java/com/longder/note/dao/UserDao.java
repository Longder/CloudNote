package com.longder.note.dao;

import com.longder.note.entity.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 用户表的数据库访问接口
 * Created by Longder on 2016/1/8.
 */
@Repository
public interface UserDao extends Serializable{
    /**
     * 根据用户名查询
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 插入
     *
     * @param user
     * @return
     */
    int save(User user);
}
