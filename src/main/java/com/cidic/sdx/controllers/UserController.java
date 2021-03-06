package com.cidic.sdx.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jgroups.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.model.ListResultModel;
import com.cidic.sdx.model.ResultModel;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;
import com.cidic.sdx.model.UserPageModel;
import com.cidic.sdx.model.UserStyle;
import com.cidic.sdx.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(StyleController.class);
	
	@Autowired
	@Qualifier(value="userServiceImpl")
	private UserService userServiceImpl;
	
	private ResultModel resultModel = null;
	
	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	
	@RequestMapping(value = "/userMgr", method = RequestMethod.GET)
	public String userMgr(Locale locale, Model model) {
		return "userMgr";
	}
	
	@RequestMapping(value = "/showUserChoose/{userId}", method = RequestMethod.GET)
	public String showUserChoose(Locale locale, Model model,@PathVariable String userId) {
		List<Style> list;
		try {
			list = userServiceImpl.getStyleListByUser(userId);
			model.addAttribute("list", list );
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return "showUserChoose";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)  
	@ResponseBody
	public ResultModel insert(HttpServletRequest request,HttpServletResponse response,@RequestParam String userId,@RequestParam String styleIds){
		
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin","*");
	    if("IE".equals(request.getParameter("type"))){
	    	response.addHeader("XDomainRequestAllowed","1");
	    }
	    
		try{
			User user = new User();
			user.setUserId(userId);
			user.setCreateTime(new Date());
			
			String[] styles = styleIds.split(",");
			
			List<UserStyle> userStyleList = new ArrayList<UserStyle>();
			for (String s : styles){
				Style style1 = new Style();
				style1.setId(Integer.parseInt(s));
				UserStyle userStyle1 = new UserStyle();
				userStyle1.setUser(user);
				userStyle1.setStyle(style1);
				userStyle1.setCreateTime(new Date());
				userStyleList.add(userStyle1);
			}
			
			user.setUserStyleList(userStyleList);
			userServiceImpl.insertUser(user);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		
		catch(Exception e){
			if (e.getMessage().equals("could not execute statement")){
				throw new SdxException(500, "该用户已经调研");
			}
			else{
				throw new SdxException(500, "写入数据出错");
			}
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody
	public ListResultModel listUser(@RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		try{
			UserPageModel userPageModel = userServiceImpl.getDataByPage(iDisplayLength, iDisplayStart, sEcho);
			List<User> list = userPageModel.getList();
			listResultModel.setAaData(list);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(userPageModel.getCount());
			listResultModel.setiTotalDisplayRecords(userPageModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/detailResult/{userId}", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody
	public ResultModel getUserResultDetailById(@PathVariable String userId){
		
		try{
			List<Style> list = userServiceImpl.getStyleListByUser(userId);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
		}
		catch(Exception e){
			throw new SdxException(500, "写入详情数据出错");
		}
		return resultModel;
	}
}
