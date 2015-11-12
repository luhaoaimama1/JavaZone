package com.example.mylib_test.activity.db.entity;

import Android.Zone.Sqlite.Annotation.Table;

@Table(name = "child")
public class Child {
	private String id;
	private String gan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGan() {
		return gan;
	}

	public void setGan(String gan) {
		this.gan = gan;
	}
}
