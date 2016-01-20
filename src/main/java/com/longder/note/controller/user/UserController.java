package com.longder.note.controller.user;

import com.longder.note.service.UserService;
import com.longder.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 用户操作相关控制器
 * Created by Longder on 2016/1/8.
 */
@Controller
@RequestMapping("/user")
public class UserController{
    @Resource
    private UserService service;

    @RequestMapping("/login.do")
    @ResponseBody
    public NoteResult login(String username, String password) {
        NoteResult result = service.checkLogin(username, password);
        return result;
    }

    @RequestMapping("/regist.do")
    @ResponseBody
    public NoteResult regist(String username, String password, String nickname) {
        NoteResult result = service.registUser(username, password, nickname);
        return result;
    }
}
