package com.education.bean;

public class Lesson {
	private int l_id;
	private String l_name;
	private int l_score;
	private int l_time;
	private String l_describe;
	private boolean l_require;
	private int c_id;

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public int getL_score() {
		return l_score;
	}

	public void setL_score(int l_score) {
		this.l_score = l_score;
	}

	public int getL_time() {
		return l_time;
	}

	public void setL_time(int l_time) {
		this.l_time = l_time;
	}

	public String getL_describe() {
		return l_describe;
	}

	public void setL_describe(String l_describe) {
		this.l_describe = l_describe;
	}

	public boolean getL_require() {
		return l_require;
	}

	public void setL_require(boolean l_require) {
		this.l_require = l_require;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Lesson(int l_id, String l_name, int l_score, int l_time, String l_describe, boolean l_require, int c_id) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
		this.l_score = l_score;
		this.l_time = l_time;
		this.l_describe = l_describe;
		this.l_require = l_require;
		this.c_id = c_id;
	}

	public Lesson(String l_name, int l_score, int l_time, String l_describe, boolean l_require, int c_id) {
		super();
		this.l_name = l_name;
		this.l_score = l_score;
		this.l_time = l_time;
		this.l_describe = l_describe;
		this.l_require = l_require;
		this.c_id = c_id;
	}

	public Lesson() {
		super();
	}

	@Override
	public String toString() {
		return "Lesson [l_id=" + l_id + ", l_name=" + l_name + ", l_score=" + l_score + ", l_time=" + l_time
				+ ", l_describe=" + l_describe + ", l_require=" + l_require + ", c_id=" + c_id + "]";
	}

}
