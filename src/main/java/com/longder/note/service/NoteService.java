package com.longder.note.service;

import com.longder.note.util.NoteResult;

/**
 * 笔记本操作业务层
 * Created by Longder on 2016/1/11.
 */
public interface NoteService {
    /**
     * 根据笔记本ID加载该笔记本下的所有笔记
     *
     * @return
     */
    NoteResult loadNotesByBookId(String bookId);

    /**
     * 根据笔记ID加载笔记内容
     *
     * @param noteId
     * @return
     */
    NoteResult loadNoteByNoteId(String noteId);

    /**
     * 添加笔记
     *
     * @param noteTitle 笔记标题
     * @param bookId    笔记本ID
     * @param userId    用户ID
     * @return
     */
    NoteResult addNote(String noteTitle, String bookId, String userId);

    /**
     * 修改笔记
     *
     * @param noteId    笔记ID
     * @param noteTitle 笔记标题
     * @param noteBody  笔记内容
     * @return
     */
    NoteResult updateNote(String noteId, String noteTitle, String noteBody);

    /**
     * 删除笔记
     * 把笔记本的statusId置为2
     *
     * @param noteId
     * @return
     */
    NoteResult deleteNote(String noteId);

    /**
     * 分享笔记
     *
     * @param noteId
     * @return
     */
    NoteResult shareNote(String noteId);

    /**
     * 移动笔记
     *
     * @param noteId
     * @param bookId
     * @return
     */
    NoteResult moveNote(String noteId, String bookId);

    /**
     * 查询分享笔记
     *
     * @param shareTitle
     * @return
     */
    NoteResult searchShareNote(String shareTitle);

    /**
     * 根据ID查询分享笔记信息
     *
     * @param shareId
     * @return
     */
    NoteResult loadShare(String shareId);
}
