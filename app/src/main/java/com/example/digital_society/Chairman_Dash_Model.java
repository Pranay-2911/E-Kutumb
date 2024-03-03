package com.example.digital_society;

public class Chairman_Dash_Model {

    int chairman_img;
    String chairman_title;

    public Chairman_Dash_Model(int chairman_img, String chairman_title) {
        this.chairman_img = chairman_img;
        this.chairman_title = chairman_title;
    }

    public int getChairman_img() {
        return chairman_img;
    }

    public void setChairman_img(int chairman_img) {
        this.chairman_img = chairman_img;
    }

    public String getChairman_title() {
        return chairman_title;
    }

    public void setChairman_title(String chairman_title) {
        this.chairman_title = chairman_title;
    }
}
