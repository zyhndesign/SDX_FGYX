package com.cidic.sdx.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.sdx.dao.StyleDao;
import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.StylePageModel;
import com.cidic.sdx.model.User;
import com.cidic.sdx.model.UserPageModel;
import com.cidic.sdx.services.StyleService;
import com.cidic.sdx.util.EHCacheService;
import com.cidic.sdx.util.RandomUtil;

@Service
@Component
@Qualifier(value = "styleServiceImpl")
@Transactional
public class StyleServiceImpl implements StyleService {

	private static final Logger logger = LoggerFactory.getLogger(StyleServiceImpl.class);

	@Autowired
	@Qualifier(value = "styleDaoImpl")
	private StyleDao styleDaoImpl;
	
	@Override
	public void insertStyle(Style style) {
		styleDaoImpl.insertStyle(style);
	}

	@Override
	public Style selectStyle(int id) {
		
		return styleDaoImpl.selectStyle(id);
	}

	@Override
	public void updateStyle(Style style) {
		styleDaoImpl.updateStyle(style);
	}

	@Override
	public void deleteStyle(Style style) {
		styleDaoImpl.deleteStyle(style);
	}

	@Override
	public List<Style> listStyleByUser(String userId,int pageNum) {
		EHCacheService service = EHCacheService.getInstance();
		try {
			Object object = service.getCacheElement(userId);
			List<Style> tempList = null;
			if (object == null){
				tempList = styleDaoImpl.listAllStyle();
				
			}
			else{
				tempList = (List<Style>)object;
			}
			
			if (tempList.size() <= pageNum){
				return tempList;
			}
			else{
				List<Integer> indexData = RandomUtil.getIntegerRangeByRandom(tempList.size(), pageNum);
				List<Style> list = new ArrayList<Style>();
				for (Integer i : indexData){
					Style style = tempList.get(i);
					list.add(style);
				}
				
				for (Style style : list){
					tempList.remove(style);
				}
				
				service.addToCache(userId,tempList);
				return list;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public StylePageModel getDataByPage(int limit, int offset, String sEcho) {
		StylePageModel stylePageModel = new StylePageModel();
		List<Style> list = styleDaoImpl.getDataByPage(limit, offset, sEcho);
		int count = styleDaoImpl.getDataCount();
		stylePageModel.setList(list);
		stylePageModel.setCount(count);
		return stylePageModel;
	}

}
