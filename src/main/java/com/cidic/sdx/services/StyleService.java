package com.cidic.sdx.services;

import java.util.List;

import com.cidic.sdx.model.Style;

public interface StyleService {
	
	public void insertStyle(Style style);

	public Style selectStyle(int id);

	public void updateStyle(Style style);

	public void deleteStyle(Style style);
	
	public List<Style> listStyleByUser(String userId,int pageNum);
}
