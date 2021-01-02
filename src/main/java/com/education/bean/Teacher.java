package com.education.bean;

public class Teacher {
    //私有化的属性
    private int t_id;
    private String t_name;
    private String t_password;
    private String t_sex;
    private String t_identity;
    private String t_email;
    private int l_id;
    private String t_phone;

    public Teacher(int t_id, String t_name, String t_password, String t_sex, String t_identity, String t_email, int l_id, String t_phone) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.t_password = t_password;
        this.t_sex = t_sex;
        this.t_identity = t_identity;
        this.t_email = t_email;
        this.l_id = l_id;
        this.t_phone = t_phone;
    }

    public Teacher() {
        super();
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_sex() {
        return t_sex;
    }

    public void setT_sex(String t_sex) {
        this.t_sex = t_sex;
    }

    public String getT_identity() {
        return t_identity;
    }

    public void setT_identity(String t_identity) {
        this.t_identity = t_identity;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    //toString方法：主要是用于观察该实例对象的数据
    @Override
    public String toString() {
        return "Teacher [t_id=" + t_id + ", t_name=" + t_name + ", t_password=" + t_password + ", t_sex=" + t_sex + ", t_identity=" + t_identity + ", t_email=" + t_email + ", l_id=" + l_id + ", t_phone=" + t_phone + "]";
    }
}