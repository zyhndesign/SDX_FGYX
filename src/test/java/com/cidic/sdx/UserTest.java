package com.cidic.sdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jgroups.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;
import com.cidic.sdx.model.UserStyle;
import com.cidic.sdx.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier(value="userServiceImpl")
    private UserService userServiceImpl; 
	
	@Test
	public void insertTest() throws SQLException{
		
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setCreateTime(new Date());
		
		Style style1 = new Style();
		style1.setId(1);
		UserStyle userStyle1 = new UserStyle();
		userStyle1.setUser(user);
		userStyle1.setStyle(style1);
		userStyle1.setCreateTime(new Date());
		
		Style style2 = new Style();
		style2.setId(2);
		UserStyle userStyle2 = new UserStyle();
		userStyle2.setUser(user);
		userStyle2.setStyle(style2);
		userStyle2.setCreateTime(new Date());
		
		Style style3 = new Style();
		style3.setId(3);
		UserStyle userStyle3 = new UserStyle();
		userStyle3.setUser(user);
		userStyle3.setStyle(style3);
		userStyle3.setCreateTime(new Date());
		
		List<UserStyle> userStyleList = new ArrayList<UserStyle>();
		userStyleList.add(userStyle1);
		userStyleList.add(userStyle2);
		userStyleList.add(userStyle3);
		user.setUserStyleList(userStyleList);
		
		userServiceImpl.insertUser(user);
	}
}
