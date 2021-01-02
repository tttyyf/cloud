package com.education.bean;

import java.util.Date;

public class Student {
	private int s_id;
	private String s_name;
	private String s_sex;
	private String s_bright;
	private String s_email;
	private int s_term;
	private int s_grate;
	private String s_phone;
	private int su_id;
	private String s_remark;

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_sex() {
		return s_sex;
	}

	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}

	public String getS_bright() {
		return s_bright;
	}

	public void setS_bright(String s_bright) {
		this.s_bright = s_bright;
	}

	public String getS_email() {
		return s_email;
	}

	public void setS_email(String s_email) {
		this.s_email = s_email;
	}

	public int getS_term() {
		return s_term;
	}

	public void setS_term(int s_term) {
		this.s_term = s_term;
	}

	public int getS_grate() {
		return s_grate;
	}

	public void setS_grate(int s_grate) {
		this.s_grate = s_grate;
	}

	public String getS_phone() {
		return s_phone;
	}

	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}

	public int getSu_id() {
		return su_id;
	}

	public void setSu_id(int su_id) {
		this.su_id = su_id;
	}

	public String getS_remark() {
		return s_remark;
	}

	public void setS_remark(String s_remark) {
		this.s_remark = s_remark;
	}

	public Student(int s_id, String s_name, String s_sex, String s_bright, String s_email, int s_term, int s_grate,
			String s_phone, int su_id, String s_remark) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_sex = s_sex;
		this.s_bright = s_bright;
		this.s_email = s_email;
		this.s_term = s_term;
		this.s_grate = s_grate;
		this.s_phone = s_phone;
		this.su_id = su_id;
		this.s_remark = s_remark;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [s_id=" + s_id + ", s_name=" + s_name + ", s_sex=" + s_sex + ", s_bright=" + s_bright
				+ ", s_email=" + s_email + ", s_term=" + s_term + ", s_grate=" + s_grate + ", s_phone=" + s_phone
				+ ", su_id=" + su_id + ", s_remark=" + s_remark + "]";
	}

}
