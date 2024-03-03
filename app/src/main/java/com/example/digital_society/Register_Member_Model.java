package com.example.digital_society;

public class Register_Member_Model {

    int id;
    String firstname;
    String lastname;
    String flat_no;
    String no_of_members;
    String member_password;

    public Register_Member_Model(int id, String firstname, String lastname, String flat_no, String no_of_members, String member_password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.flat_no = flat_no;
        this.no_of_members = no_of_members;
        this.member_password = member_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFlat_no() {
        return flat_no;
    }

    public void setFlat_no(String flat_no) {
        this.flat_no = flat_no;
    }

    public String getNo_of_members() {
        return no_of_members;
    }

    public void setNo_of_members(String no_of_members) {
        this.no_of_members = no_of_members;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }
}
