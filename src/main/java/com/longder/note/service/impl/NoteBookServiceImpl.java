package com.longder.note.service.impl;

import com.longder.note.dao.NoteBookDao;
import com.longder.note.entity.NoteBook;
import com.longder.note.service.NoteBookService;
import com.longder.note.util.NoteResult;
import com.longder.note.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Longder on 2016/1/9.
 */
@Service
@Transactional
public class NoteBookServiceImpl implements NoteBookService {
    @Resource
    private NoteBookDao dao;

    /**
     * 加载用户的所有笔记本
     *
     * @param userId 用户ID
     * @return
     */
    public NoteResult loadUserNoteBooks(String userId) {
        List<NoteBook> list = dao.findByUserId(userId);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * 添加一个笔记本
     *
     * @param bookName 笔记本名称
     * @param userId   用户ID
     * @return
     */
    public NoteResult addNoteBook(String bookName, String userId) {
        NoteResult result = new NoteResult();
        NoteBook book = new NoteBook();
        String id = NoteUtil.createId();
        book.setId(id);
        book.setName(bookName);
        book.setDescription("");
        book.setTypeId("5");
        book.setUserId(userId);
        book.setCreateTime(new Timestamp(System.currentTimeMillis()));
        dao.add(book);
        result.setStatus(0);
        result.setMessage("添加成功");
        result.setData(id);
        return result;
    }
}
