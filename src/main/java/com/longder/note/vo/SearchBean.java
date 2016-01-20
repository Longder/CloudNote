package com.longder.note.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Longder on 2016/1/20.
 */
public class SearchBean implements Serializable {
    private String title;
    private String status;
    private String beginTime;
    private String endTime;

    /**
     * 对日期进行类型装换，在Mybatis中可以使用#{longBeginTime}来获取
     *
     * @return
     */
    public Long getLongBeginTime() {
        if (beginTime == null || "".equals(beginTime)) {
            return null;
        } else {
            Date time = Date.valueOf(beginTime);
            return time.getTime();
        }
    }

    /**
     * #{longEndTime}
     *
     * @return
     */
    public Long getLongEndTime() {
        if (endTime == null || "".equals(endTime)) {
            return null;
        } else {
            Date time = Date.valueOf(endTime);
            return time.getTime();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
