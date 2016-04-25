package com.cidic.sdx.services;

import java.util.List;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.StylePageModel;

public interface StyleService {
	
	public void insertStyle(Style style);

	public Style selectStyle(int id);

	public void updateStyle(Style style);

	public void deleteStyle(Style style);
	
	public List<Style> listStyleByUser(String userId,int pageNum);
	
	public StylePageModel getDataByPage(int limit, int offset, String sEcho);
}
