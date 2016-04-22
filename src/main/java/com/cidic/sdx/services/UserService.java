package com.cidic.sdx.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;

public interface UserService {
	
	public void insertUser(User user);
	
	public User selectUser(int id);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> getDataByPage(int limit, int offset, String sEcho);
	
	public List<Style> getStyleListByUser(String userId) throws ParseException;
}
