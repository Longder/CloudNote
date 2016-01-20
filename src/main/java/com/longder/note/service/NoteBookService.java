package com.longder.note.service;

import com.longder.note.util.NoteResult;

/**
 * Created by Longder on 2016/1/9.
 */
public interface NoteBookService {
    /**
     * 加载用户的所有笔记本
     * @param userId 用户ID
     * @return
     */
    NoteResult loadUserNoteBooks(String userId);

    /**
     * 添加一个笔记本
     * @param bookName 笔记本名称
     * @param userId 用户ID
     * @return
     */
    NoteResult addNoteBook(String bookName,String userId);
}
