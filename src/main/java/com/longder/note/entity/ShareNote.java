package com.longder.note.entity;

/**
 * Created by Longder on 2016/1/14.
 */
public class ShareNote {
    private String id;
    private String title;
    private String body;
    private String noteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShareNote{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", noteId='").append(noteId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
