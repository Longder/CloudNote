package com.longder.note.dao;

import com.longder.note.entity.ShareNote;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Longder on 2016/1/14.
 */
@Repository
public interface ShareNoteDao {
    /**
     * 添加分享笔记
     *
     * @param note
     * @return
     */
    int add(ShareNote note);

    /**
     * 根据笔记ID查询
     *
     * @param noteId
     * @return
     */
    ShareNote findByNoteId(String noteId);

    /**
     * 根据标题模糊查询
     *
     * @param title
     * @return
     */
    List<ShareNote> findByTitle(String title);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ShareNote findById(String id);
}
