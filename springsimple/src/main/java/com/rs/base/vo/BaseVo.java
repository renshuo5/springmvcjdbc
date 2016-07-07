package com.rs.base.vo;

import java.sql.Timestamp;

public class BaseVo {

	private Integer id;
	
//	protected Integer version;
	
//	protected Timestamp creation;

	protected Timestamp lastModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	
	
	
}
