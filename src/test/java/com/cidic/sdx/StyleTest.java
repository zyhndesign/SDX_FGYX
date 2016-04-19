package com.cidic.sdx;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.services.StyleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class StyleTest {

	@Autowired
	@Qualifier(value="styleServiceImpl")
    private StyleService styleServiceImpl; 
	
	@Test
	public void testInsert(){
		Style style1 = new Style();
		style1.setImageUrl("http://www.163.com");
		style1.setCreateTime(new Date());
		styleServiceImpl.insertStyle(style1);
		
		Style style2 = new Style();
		style2.setImageUrl("http://www.sohu.com");
		style2.setCreateTime(new Date());
		styleServiceImpl.insertStyle(style2);
		
		Style style3 = new Style();
		style3.setImageUrl("http://www.sina.com");
		style3.setCreateTime(new Date());
		styleServiceImpl.insertStyle(style3);
	}
}
