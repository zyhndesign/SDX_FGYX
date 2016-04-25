package com.cidic.sdx.dao;

import java.text.ParseException;
import java.util.List;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;


public interface UserDao {
	public void insertUser(User user);
	
	public User selectUser(int id);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> getDataByPage(int limit, int offset, String sEcho);
	
	public List<Style> getStyleListByUser(String userId) throws ParseException;
	
	public int getDataCount();
}
