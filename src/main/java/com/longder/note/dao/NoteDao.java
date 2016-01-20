package com.longder.note.dao;

import com.longder.note.entity.Note;
import com.longder.note.vo.SearchBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 笔记表的数据库访问接口
 * Created by Longder on 2016/1/11.
 */
@Repository
public interface NoteDao {
    /**
     * 根据笔记本ID查询
     *
     * @param bookId
     * @return
     */
    List<Note> findByBookId(String bookId);

    /**
     * 根据笔记id查询笔记
     *
     * @param noteId
     * @return
     */
    Note findById(String noteId);

    /**
     * 添加笔记
     *
     * @param note
     * @return
     */
    int add(Note note);

    /**
     * 更新笔记
     *
     * @param note
     * @return
     */
    int update(Note note);

    /**
     * 删除笔记
     *
     * @param noteId
     * @return
     */
    int delete(String noteId);

    /**
     * 移动笔记
     *
     * @param map
     * @return
     */
    int move(Map<String, String> map);

    /**
     * 组合查询
     * @param sb
     * @return
     */
    List<Note> findByCondition(SearchBean sb);
}
