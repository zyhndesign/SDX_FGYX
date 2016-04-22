package com.cidic.sdx.controllers;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.cidic.sdx.util.DateUtil;
import com.cidic.sdx.exception.SdxException;
import com.cidic.sdx.model.ListResultModel;
import com.cidic.sdx.model.ResultModel;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;
import com.cidic.sdx.services.StyleService;


@Controller
@RequestMapping("/style")
public class StyleController {

	private static final Logger logger = LoggerFactory.getLogger(StyleController.class);
	
	@Autowired
	@Qualifier(value="styleServiceImpl")
	private StyleService styleServiceImpl;
	
	private ResultModel resultModel = null;
	
	@ExceptionHandler(SdxException.class)
	public @ResponseBody ResultModel handleCustomException(SdxException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	
	@RequestMapping(value = "/clothCOR", method = RequestMethod.GET)
	public String clothCOR(Locale locale, Model model) {
		return "clothCOR";
	}
	
	@RequestMapping(value = "/clothMgr", method = RequestMethod.GET)
	public String clothMgr(Locale locale, Model model) {
		return "clothMgr";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)  
	@ResponseBody
	public ResultModel insert(@RequestParam String image_url,@RequestParam(required=false) String createTime){
		Style style = new Style();
		style.setImageUrl(image_url);
		style.setCreateTime(new Date());
		try{
			styleServiceImpl.insertStyle(style);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new SdxException(500, "写入数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)  
	@ResponseBody
	public ResultModel delete(@PathVariable int id){
		Style style = new Style();
		style.setId(id);
		
		try{
			styleServiceImpl.deleteStyle(style);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new SdxException(500, "删除数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)  
	@ResponseBody
	public ResultModel update(@RequestParam int id,@RequestParam String image_url,@RequestParam(required=false) String createTime){
		Style style = new Style();
		style.setId(id);
		style.setImageUrl(image_url);
		style.setCreateTime(new Date());
		
		try{
			styleServiceImpl.updateStyle(style);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new SdxException(500, "更新数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	@ResponseBody
	public ResultModel listStyleData(@RequestParam int pageNum,@RequestParam String userId){
		try{
			List<Style> listStyle = styleServiceImpl.listStyleByUser(userId, pageNum);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(listStyle);
		}
		catch(Exception e){
			throw new SdxException(500, "获取数据列表出错");
		}
		return resultModel;
	} 
	
	@RequestMapping(value = "/listStyle", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody
	public ListResultModel listUser(@RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		try{
			List<Style> list = styleServiceImpl.getDataByPage(iDisplayLength, iDisplayStart, sEcho);
			listResultModel.setAaData(list);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(list.size());
			listResultModel.setiTotalDisplayRecords(iDisplayStart + list.size());
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
