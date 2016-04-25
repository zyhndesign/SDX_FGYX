package com.cidic.sdx.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.dao.UserDao;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;
import com.cidic.sdx.util.DateUtil;

@Repository
@Component
@Qualifier(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertUser(User user){
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(user);
	}

	@Override
	public User selectUser(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		return (User)session.get(User.class, id);
	}

	@Override
	public void updateUser(User user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(user);
	}

	@Override
	public void deleteUser(User user) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(user);
	}

	@Override
	public List<User> getDataByPage(int limit, int offset, String sEcho) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from User order by createTime desc"; 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        final List<User> list = query.list(); 
		return list;
	}

	@Override
	public List<Style> getStyleListByUser(String userId) throws ParseException{
		Session session = this.getSessionFactory().getCurrentSession();
		final String sql = "select s.id, s.image_url, s.create_time from user_style u, style s where u.user_id=:user_id and u.style_id = s.id";
		final Query query = session.createSQLQuery(sql);
		query.setParameter("user_id", userId);
		List<Object[]> styles = query.list();
		Style style = null;
		List<Style> resultList = new ArrayList<Style>();
		for(Object[] object : styles){  
			style = new Style();
			style.setId(Integer.parseInt(String.valueOf(object[0])));
			style.setImageUrl(String.valueOf(object[1]));
			style.setCreateTime(DateUtil.parse(String.valueOf(object[2])));
			resultList.add(style);
		}
		return resultList;
	}

	@Override
	public int getDataCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "select count(*) from User";  
		Query query =  session.createQuery(hql);  
		return ((Number)query.uniqueResult()).intValue();
	}

}
