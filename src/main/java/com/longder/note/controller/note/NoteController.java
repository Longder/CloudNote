package com.longder.note.controller.note;

import com.longder.note.service.NoteService;
import com.longder.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by Longder on 2016/1/11.
 */
@Controller
@RequestMapping("/note")
public class NoteController{
    @Resource
    NoteService noteService;

    @RequestMapping("/load.do")
    @ResponseBody
    public NoteResult loadNoteBooks(String bookId) {
        NoteResult result = noteService.loadNotesByBookId(bookId);
        return result;
    }

    @ResponseBody
    @RequestMapping("/getNote.do")
    public NoteResult loadNote(String noteId) {
        NoteResult result = noteService.loadNoteByNoteId(noteId);
        return result;
    }

    @ResponseBody
    @RequestMapping("/add.do")
    public NoteResult addNote(String noteTitle, String bookId, String userId) {
        NoteResult result = noteService.addNote(noteTitle, bookId, userId);
        return result;
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public NoteResult updateNote(String noteId, String noteTitle, String noteBody) {
        NoteResult result = noteService.updateNote(noteId, noteTitle, noteBody);
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public NoteResult deleteNote(String noteId) {
        NoteResult result = noteService.deleteNote(noteId);
        return result;
    }

    @RequestMapping("/share.do")
    @ResponseBody
    public NoteResult shareNote(String noteId) {
        NoteResult result = noteService.shareNote(noteId);
        return result;
    }

    @RequestMapping("/move.do")
    @ResponseBody
    public NoteResult moveNote(String noteId, String bookId) {
        NoteResult result = noteService.moveNote(bookId, noteId);
        return result;
    }

    @RequestMapping("/search.do")
    @ResponseBody
    public NoteResult searchShareNote(String keyword) {
        NoteResult result = noteService.searchShareNote(keyword);
        return result;
    }

    @RequestMapping("/loadShare.do")
    @ResponseBody
    public NoteResult loadShareNoteById(String shareId) {
        NoteResult result = noteService.loadShare(shareId);
        return result;
    }
}
