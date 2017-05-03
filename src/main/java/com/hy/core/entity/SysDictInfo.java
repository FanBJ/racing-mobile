package com.hy.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * SysDictInfo entity. @author MyEclipse Persistence Tools
 */

public class SysDictInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Integer id;
	@JsonIgnore
	private String tableName;
	private String fieldName;
	private String fieldVal;
	private String parentFieldVal;
	private String fieldDesc;
	private Integer selected;
	@JsonIgnore
	private Integer sort;
	@JsonIgnore
	private Integer state;

	// Constructors

	/** default constructor */
	public SysDictInfo() {
	}

	/** full constructor */
	public SysDictInfo(String tableName, String fieldName, String fieldVal, String parentFieldVal, String fieldDesc, Integer selected, Integer sort, Integer state) {
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.fieldVal = fieldVal;
		this.parentFieldVal = parentFieldVal;
		this.fieldDesc = fieldDesc;
		this.selected = selected;
		this.sort = sort;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldVal() {
		return this.fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	public String getParentFieldVal() {
		return this.parentFieldVal;
	}

	public void setParentFieldVal(String parentFieldVal) {
		this.parentFieldVal = parentFieldVal;
	}

	public String getFieldDesc() {
		return this.fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public Integer getSelected() {
		return this.selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}