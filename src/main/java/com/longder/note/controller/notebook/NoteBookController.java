package com.longder.note.controller.notebook;

import com.longder.note.service.NoteBookService;
import com.longder.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 笔记本相关控制器
 * Created by Longder on 2016/1/9.
 */
@Controller
@RequestMapping("/notebook")
public class NoteBookController{
    @Resource
    private NoteBookService service;

    @RequestMapping("/load.do")
    @ResponseBody
    public NoteResult loadNoteBooks(String userId) {
        NoteResult result = service.loadUserNoteBooks(userId);
        return result;
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult addNoteBook(String bookName, String userId) {
        System.out.println("进入控制器！！！！" + bookName + "  " + userId);
        NoteResult result = service.addNoteBook(bookName, userId);
        return result;
    }


}
