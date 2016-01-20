package com.longder.note.test;

import com.longder.note.dao.NoteBookDao;
import com.longder.note.dao.NoteDao;
import com.longder.note.dao.UserDao;
import com.longder.note.entity.Note;
import com.longder.note.entity.NoteBook;
import com.longder.note.entity.User;
import com.longder.note.util.NoteUtil;
import com.longder.note.vo.SearchBean;
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
public class DaoTest {
    @Resource
    private UserDao dao;
    @Resource
    private NoteBookDao noteBookDao;
    @Resource
    private NoteDao noteDao;

    @Test
    public void testFindByName() {
        User user = dao.findByName("demo");
        Assert.assertNotNull(user);
        user = dao.findByName("longder");
        Assert.assertNull(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        String id = NoteUtil.createId();
        user.setId(id);
        user.setUsername("longder");
        user.setPassword(NoteUtil.md5("123456"));
        user.setDescription("大表哥");
        dao.save(user);
        User dbUser = dao.findByName("longder");
        Assert.assertEquals(id, dbUser.getId());
        Assert.assertEquals(NoteUtil.md5("123456"), dbUser.getPassword());
        Assert.assertEquals("大表哥", dbUser.getDescription());
        Assert.assertNull(dbUser.getToken());
    }

    @Test
    public void testFindByUserId() {
        User user = dao.findByName("demo");
        List<NoteBook> list = noteBookDao.findByUserId(user.getId());
        for (NoteBook nb : list) {
            System.out.println(nb);
        }
    }

    @Test
    public void testFindByBookId() {
        List<Note> list = noteDao.findByBookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
        System.out.println(list.size());
        for (Note n : list) {
            System.out.println(n);
        }
    }

    @Test
    public void testFindByCondition() {
        SearchBean sb = new SearchBean();
        sb.setTitle("测试");
        List<Note> list = noteDao.findByCondition(sb);
        for (Note note : list) {
            System.out.println(note);
        }
        System.out.println("总数：" + list.size());
    }
}
