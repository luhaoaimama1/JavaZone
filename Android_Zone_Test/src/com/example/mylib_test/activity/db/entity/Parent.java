package com.example.mylib_test.activity.db.entity;

import java.util.ArrayList;
import java.util.List;

import Android.Zone.Sqlite.Annotation.Table;

@Table(name = "parent")
public class Parent {
	
	private String id;
	private Child child = new Child();
	private List<String> strList = new ArrayList<String>();
	private List<Child> childList = new ArrayList<Child>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}

}
