package com.longder.note.service;

import com.longder.note.util.NoteResult;

import java.io.Serializable;

/**
 * 用户表业务层接口
 * Created by Longder on 2016/1/8.
 */
public interface UserService extends Serializable{
    /**
     * 登录业务
     *
     * @param username
     * @param password
     * @return
     */
    NoteResult checkLogin(String username, String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    NoteResult registUser(String username, String password, String nickname);
}
