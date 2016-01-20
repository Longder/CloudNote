package com.longder.note.service.impl;

import com.longder.note.dao.NoteDao;
import com.longder.note.dao.ShareNoteDao;
import com.longder.note.entity.Note;
import com.longder.note.entity.ShareNote;
import com.longder.note.service.NoteService;
import com.longder.note.util.NoteResult;
import com.longder.note.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Longder on 2016/1/11.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteDao noteDao;
    @Resource
    private ShareNoteDao shareNoteDao;

    /**
     * @return
     */
    @Transactional(readOnly = true)
    public NoteResult loadNotesByBookId(String bookId) {
        List<Note> list = noteDao.findByBookId(bookId);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setData(list);
        result.setMessage("查询成功");
        return result;
    }

    /**
     * 根据笔记ID加载笔记内容
     *
     * @param noteId
     * @return
     */
    public NoteResult loadNoteByNoteId(String noteId) {
        Note note = noteDao.findById(noteId);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setData(note);
        result.setMessage("查询成功");
        return result;
    }

    /**
     * 添加笔记
     *
     * @param noteTitle 笔记标题
     * @param bookId    笔记本ID
     * @param userId    用户ID
     * @return
     */
    public NoteResult addNote(String noteTitle, String bookId, String userId) {
        Note note = new Note();
        String id = NoteUtil.createId();
        note.setId(id);
        note.setNoteBookId(bookId);
        note.setUserId(userId);
        note.setTypeId("1");
        note.setStatusId("1");
        note.setBody("");
        note.setTitle(noteTitle);
        Long time = System.currentTimeMillis();
        note.setCreateTime(time);
        note.setLastModifyTime(time);
        noteDao.add(note);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMessage("添加笔记成功");
        result.setData(id);
        return result;
    }

    /**
     * 修改笔记
     *
     * @param noteId    笔记ID
     * @param noteTitle 笔记标题
     * @param noteBody  笔记内容
     * @return
     */
    public NoteResult updateNote(String noteId, String noteTitle, String noteBody) {
        Note note = new Note();
        note.setId(noteId);
        note.setTitle(noteTitle);
        note.setBody(noteBody);
        Long time = System.currentTimeMillis();
        note.setLastModifyTime(time);
        noteDao.update(note);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMessage("更新笔记成功");
        return result;
    }

    /**
     * 删除笔记
     * 把笔记本的statusId置为2
     *
     * @param noteId
     * @return
     */
    public NoteResult deleteNote(String noteId) {
        NoteResult result = new NoteResult();
        noteDao.delete(noteId);
        result.setStatus(0);
        result.setMessage("删除成功");
        return result;
    }

    /**
     * 分享笔记
     *
     * @param noteId
     * @return
     */
    public NoteResult shareNote(String noteId) {
        NoteResult result = new NoteResult();
        //检查笔记是否已分享
        ShareNote share = shareNoteDao.findByNoteId(noteId);
        if (share != null) {
            result.setStatus(1);
            result.setMessage("笔记已经分享");
            return result;
        }
        //先查询笔记信息
        Note note = noteDao.findById(noteId);
        ShareNote shareNote = new ShareNote();
        String shareId = NoteUtil.createId();
        shareNote.setId(shareId);
        shareNote.setTitle(note.getTitle());
        shareNote.setBody(note.getBody());
        shareNote.setNoteId(note.getId());
        shareNoteDao.add(shareNote);
        result.setStatus(0);
        result.setMessage("分享成功");
        return result;
    }

    /**
     * 移动笔记
     *
     * @param noteId
     * @param bookId
     * @return
     */
    public NoteResult moveNote(String bookId, String noteId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("noteId", noteId);
        map.put("bookId", bookId);
        noteDao.move(map);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMessage("移动笔记成功");
        return result;
    }

    /**
     * 查询分享笔记
     *
     * @param shareTitle
     * @return
     */
    public NoteResult searchShareNote(String shareTitle) {
        if (shareTitle == null) {
            shareTitle = "";
        }
        NoteResult result = new NoteResult();
        List<ShareNote> notes = shareNoteDao.findByTitle(shareTitle);
        result.setStatus(0);
        result.setMessage("查询成功");
        result.setData(notes);
        return result;
    }

    /**
     * 根据ID查询分享笔记信息
     *
     * @param shareId
     * @return
     */
    public NoteResult loadShare(String shareId) {
        ShareNote shareNote = shareNoteDao.findById(shareId);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMessage("加载分享笔记信息成功");
        result.setData(shareNote);
        return result;
    }
}
