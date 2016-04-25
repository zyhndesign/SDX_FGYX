package com.cidic.sdx.dao;

import java.util.List;

import com.cidic.sdx.model.Style;
import com.cidic.sdx.model.User;

public interface StyleDao {
	public void insertStyle(Style style);

	public Style selectStyle(int id);

	public void updateStyle(Style style);

	public void deleteStyle(Style style);
	
	public List<Style> listAllStyle();
	
	public List<Style> getDataByPage(int limit, int offset, String sEcho);
	
	public int getDataCount();
}
