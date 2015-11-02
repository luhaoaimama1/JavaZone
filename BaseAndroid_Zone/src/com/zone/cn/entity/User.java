package com.zone.cn.entity;
/**
 * 可以多账户排序(按时间)   实现了comparable
 * @author Zone
 *
 */
public class User implements Comparable<User>{
	private String id;
	private String name;
	private String passWord;
	private String loginCount;
	/**
	 * 最后的登陆时间  毫秒数
	 *<b>System.currentTimeMillis()<b>
	 */
	private String lastLoginMi;
	public User() {
	}
	public User(String name,String passWord,String loginCount,String lastLoginMi) {
		this.name=name;
		this.passWord=passWord;
		this.loginCount=loginCount;
		this.lastLoginMi=lastLoginMi;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	public String getLastLoginMi() {
		return lastLoginMi;
	}
	public void setLastLoginMi(String lastLoginMi) {
		this.lastLoginMi = lastLoginMi;
	}
	@Override
	public int compareTo(User another) {
		long thisMils=Long.parseLong(this.getLastLoginMi());
		long anotherMils=Long.parseLong(another.getLastLoginMi());
		if(thisMils>anotherMils)
			return 1;	
		else
			return -1;
		
	}
	
}
