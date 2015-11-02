package com.example.mylib_test.activity.db.entity;

import Android.Zone.Sqlite.Annotation.Column;
import Android.Zone.Sqlite.Annotation.Table;

@Table(name="TwoThree")
public class DbEntity {
	private String id;
	@Column(column="nameAn" )
	private String name;
	@Column(column="ageAn")
	private String age;
	@Column(column="sjAn")
	private String sj;
	@Column(column="heAn" ,length="666")
	private String he;
	@Column(column="danteng" ,length="100")
	private String danteng;
	private String wwc;
	public String getWwc() {
		return wwc;
	}

	public void setWwc(String wwc) {
		this.wwc = wwc;
	}

	
	public String getHe() {
		return he;
	}

	public void setHe(String he) {
		this.he = he;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		System.out.println("id:"+this.getId() +"\t name"+this.getName()+"\t age"+this.getAge()+"\t sj"+this.getSj());
		return super.toString();
	}

	public String getDanteng() {
		return danteng;
	}

	public void setDanteng(String danteng) {
		this.danteng = danteng;
	}

	
}
