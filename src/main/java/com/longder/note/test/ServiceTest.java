package com.longder.note.test;

import com.longder.note.entity.ShareNote;
import com.longder.note.service.NoteBookService;
import com.longder.note.service.NoteService;
import com.longder.note.service.UserService;
import com.longder.note.util.NoteResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Longder on 2016/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {
    @Resource
    private UserService service;
    @Resource
    private NoteService noteService;
    @Resource
    private NoteBookService noteBookService;

    @Test
    public void testCheckLogin() {
        NoteResult rs = service.checkLogin("zhouj", "123");
        //断言
        Assert.assertEquals(1, rs.getStatus());
        Assert.assertEquals("密码错误", rs.getMessage());
        rs = service.checkLogin("demo", "c8837b23ff8aaa8a2dde915473ce0991");
        Assert.assertEquals(0, rs.getStatus());
        Assert.assertEquals("用户名密码正确", rs.getMessage());
    }

    @Test
    public void testLoadNoteById() {
        NoteResult result = noteService.loadNoteByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
        System.out.println(result.getData());
    }

    @Test
    public void testAddNotebook() {
        NoteResult result = noteBookService.addNoteBook("德玛西亚", "48595f52-b22c-4485-9244-f4004255b972");
        System.out.println(result);
    }

    @Test
    public void testAddNote() {
        NoteResult result = noteService.addNote("我勒个去", "389a78d0-4b85-4b10-af53-70acc1ad4c80", "48595f52-b22c-4485-9244-f4004255b972");
        System.out.println(result);
    }

    @Test
    public void testUpdateNote() {
        NoteResult result = noteService.updateNote("e0abeeb6-1290-4204-b453-c18c1210a921", "东方飞飞飞", "修改成功！");
        System.out.println(result);
    }

    @Test
    public void testDeleteNote() {
        NoteResult result = noteService.deleteNote("01da5d69-89d5-4140-9585-b559a97f9cb0");
        System.out.println(result);
    }

    @Test
    public void testShareNote() {
        NoteResult result = noteService.shareNote("ff4bb4d0-4192-4a32-80e7-527349ca8c51");
        System.out.println(result);
    }

    @Test
    public void testMoveNote() {
        NoteResult result = noteService.moveNote("0528555f-f931-4671-906c-c587d3559244", "20b4cbec-bd55-4c21-9c41-3a11ada2b803");
        System.out.println(result);
    }

    @Test
    public void testSearchShareNote() {
        NoteResult result = noteService.searchShareNote(null);
        System.out.println(result.getStatus());
        List<ShareNote> list = (List<ShareNote>) result.getData();
        System.out.println(list.size());
        for (ShareNote s : list) {
            System.out.println(s);
        }
    }
}
