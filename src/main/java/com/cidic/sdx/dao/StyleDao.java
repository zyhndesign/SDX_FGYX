package com.cidic.sdx.dao;

import java.util.List;

import com.cidic.sdx.model.Style;

public interface StyleDao {
	public void insertStyle(Style style);

	public Style selectStyle(int id);

	public void updateStyle(Style style);

	public void deleteStyle(Style style);
	
	public List<Style> listAllStyle();
}
