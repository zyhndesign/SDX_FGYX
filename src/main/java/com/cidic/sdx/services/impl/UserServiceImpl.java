package com.cidic.sdx.services.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dao.StyleDao;
import com.cidic.sdx.dao.UserDao;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;
import com.cidic.sdx.services.UserService;

@Service
@Component
@Qualifier(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public void insertUser(User user){
		userDaoImpl.insertUser(user);
	}

	@Override
	public User selectUser(int id) {
		
		return userDaoImpl.selectUser(id);
	}

	@Override
	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userDaoImpl.deleteUser(user);
	}

	@Override
	public List<User> getDataByPage(int limit, int offset, String sEcho) {
		return userDaoImpl.getDataByPage(limit, offset, sEcho);
	}

	@Override
	public List<Style> getStyleListByUser(String userId) throws ParseException {
		return userDaoImpl.getStyleListByUser(userId);
	}

	
}
