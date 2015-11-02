package com.zone.cn.sqlite;

import java.util.Collections;
import java.util.List;

import com.zone.cn.entity.User;

import Android.Zone.Sqlite.Sqlite_Utils;

public class DbManager {
	
	public static void setUser(Sqlite_Utils dao,String name,String passWord){
		List<User> list = dao.queryEntitysByCondition(User.class, "where name=?", new String[]{name});
		if(list.size()==0){
			//name没有 则新建
			User user=new User(name, passWord, 0+"", System.currentTimeMillis()+"");
			dao.addEntity(user);
		}else{
			for (User user : list) {
				if((name).equals(user.getName())){
					// name 有 则修改     password  时间 当前mils 次数加1
					user.setLoginCount(Integer.parseInt(user.getLoginCount())+1+"");
					user.setPassWord(passWord);
					user.setLastLoginMi(System.currentTimeMillis()+"");
					dao.addOrUpdateEntity(user);
				}
			}
		}
	}
	public static List<User> getUser(Sqlite_Utils dao,String name){
		List<User> list = dao.queryAllByClass(User.class);
		Collections.sort(list);
		return list;
	}

}
