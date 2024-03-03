package com.example.digital_society;

public class Notice_Model {

    int notice_id;
    String Notice;

    public Notice_Model(int notice_id, String notice) {
        this.notice_id = notice_id;
        Notice = notice;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
