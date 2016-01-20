package com.longder.note.service.impl;

import com.longder.note.dao.UserDao;
import com.longder.note.entity.User;
import com.longder.note.service.UserService;
import com.longder.note.util.NoteResult;
import com.longder.note.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Longder on 2016/1/8.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao dao;

    public NoteResult checkLogin(String username, String password) {
        User user = dao.findByName(username);
        NoteResult rs = new NoteResult();
        if (user == null) {
            rs.setStatus(1);
            rs.setMessage("用户不存在");
            return rs;
        }
        //加密之后再比对
        password = NoteUtil.md5(password);
        if (!user.getPassword().equals(password)) {
            rs.setStatus(2);
            rs.setMessage("密码错误");
            return rs;
        }
        rs.setStatus(0);
        rs.setMessage("用户名密码正确");
        rs.setData(user.getId());
        return rs;
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    public NoteResult registUser(String username, String password, String nickname) {
        NoteResult result = new NoteResult();
        //检测用户名是否注册
        User user = dao.findByName(username);
        if (user != null) {
            result.setStatus(1);
            result.setMessage("用户名已存在");
            return result;
        }
        //注册
        user = new User();
        user.setId(NoteUtil.createId());
        user.setUsername(username);
        user.setPassword(NoteUtil.md5(password));
        user.setDescription(nickname);
        dao.save(user);
        result.setStatus(0);
        result.setMessage("注册成功");
        //模拟空指针异常
        String s = null;
        s.length();
        return result;
    }
}
