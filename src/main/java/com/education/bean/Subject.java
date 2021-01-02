package com.education.bean;

//×¨ÒµÀà
public class Subject {
	private int su_id;
	private String su_name;
	private String su_describe;
	private int c_id;

	public int getSu_id() {
		return su_id;
	}

	public void setSu_id(int su_id) {
		this.su_id = su_id;
	}

	public String getSu_name() {
		return su_name;
	}

	public void setSu_name(String su_name) {
		this.su_name = su_name;
	}

	public String getSu_describe() {
		return su_describe;
	}

	public void setSu_describe(String su_describe) {
		this.su_describe = su_describe;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Subject(int su_id, String su_name, String su_describe, int c_id) {
		super();
		this.su_id = su_id;
		this.su_name = su_name;
		this.su_describe = su_describe;
		this.c_id = c_id;
	}

	public Subject(String su_name, String su_describe, int c_id) {
		super();
		this.su_name = su_name;
		this.su_describe = su_describe;
		this.c_id = c_id;
	}

	public Subject() {
		super();
	}

	@Override
	public String toString() {
		return "Subject [su_id=" + su_id + ", su_name=" + su_name + ", su_describe=" + su_describe + ", c_id=" + c_id
				+ "]";
	}

}
