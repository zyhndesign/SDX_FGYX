package com.cidic.sdx.dao.impl;

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

import com.cidic.sdx.dao.StyleDao;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;

@Repository
@Component
@Qualifier(value = "styleDaoImpl")
public class StyleDaoImpl implements StyleDao {

	private static final Logger logger = LoggerFactory.getLogger(StyleDaoImpl.class);
	
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
	public void insertStyle(Style style) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(style);
	}

	@Override
	public Style selectStyle(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		return (Style)session.get(Style.class, id);
	}

	@Override
	public void updateStyle(Style style) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(style);
	}

	@Override
	public void deleteStyle(Style style) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(style);
	}

	@Override
	public List<Style> listAllStyle() {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Style");
		List<Style> list = query.list();
		return list;
	}

	@Override
	public List<Style> getDataByPage(int limit, int offset, String sEcho) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from Style order by createTime desc"; 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        final List<Style> list = query.list();  
		return list;
	}

}
