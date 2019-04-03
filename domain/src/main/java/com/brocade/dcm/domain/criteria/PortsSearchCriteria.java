package com.brocade.dcm.domain.criteria;

import java.io.Serializable;

public class PortsSearchCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String searchQuery = "";

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
}
