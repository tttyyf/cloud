package com.education.bean;

public class FileBean {
    private int f_id;
    private String f_name;
    private long f_size;
    private String f_path;

    public FileBean(int f_id, String f_name, long f_size, String f_path) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_size = f_size;
        this.f_path = f_path;
    }

    public FileBean() {
        super();
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public long getF_size() {
        return f_size;
    }

    public void setF_size(int f_size) {
        this.f_size = f_size;
    }

    public String getF_path() {
        return f_path;
    }

    public void setF_path(String f_path) {
        this.f_path = f_path;
    }

    @Override
    public String toString() {
        return "File [f_id=" + f_id + ", f_name=" + f_name + ", f_size=" + f_size + ", f_path=" + f_path +
                "]";
    }
}