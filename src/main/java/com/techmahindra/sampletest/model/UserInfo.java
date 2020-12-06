package com.techmahindra.sampletest.model;

public class UserInfo {

    int id;             // user id
    String name;        // user name
    String email;       // user email address
    String phone;       // user phone number

    public UserInfo(int id, String n, String e, String p)
    {
        this.id=id;
        this.name=n;
        this.email=e;
        this.phone=p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
