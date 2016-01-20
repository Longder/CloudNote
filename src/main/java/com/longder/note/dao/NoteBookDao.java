package com.longder.note.dao;

import com.longder.note.entity.NoteBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 笔记本表的数据库访问接口
 * Created by Longder on 2016/1/9.
 */
@Repository
public interface NoteBookDao {
    /**
     * 根据用户ID查询该用户的所有笔记本
     *
     * @param userId 用户ID
     * @return
     */
    List<NoteBook> findByUserId(String userId);

    /**
     * 插入一个笔记本
     *
     * @param book
     * @return
     */
    int add(NoteBook book);
}
