package com.hy.core.entity;

/**
 * CommonSysParam entity. @author MyEclipse Persistence Tools
 */

public class CommonSysParam implements java.io.Serializable {

	// Fields

	private Integer paramid;
	private String paramname;
	private String paramval;
	private String paramdesc;
	private Integer status;
	private Integer catalogid;

	// Constructors

	/** default constructor */
	public CommonSysParam() {
	}

	/** minimal constructor */
	public CommonSysParam(String paramname, String paramval) {
		this.paramname = paramname;
		this.paramval = paramval;
	}

	/** full constructor */
	public CommonSysParam(String paramname, String paramval, String paramdesc, Integer status, Integer catalogid) {
		this.paramname = paramname;
		this.paramval = paramval;
		this.paramdesc = paramdesc;
		this.status = status;
		this.catalogid = catalogid;
	}

	// Property accessors

	public Integer getParamid() {
		return this.paramid;
	}

	public void setParamid(Integer paramid) {
		this.paramid = paramid;
	}

	public String getParamname() {
		return this.paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamval() {
		return this.paramval;
	}

	public void setParamval(String paramval) {
		this.paramval = paramval;
	}

	public String getParamdesc() {
		return this.paramdesc;
	}

	public void setParamdesc(String paramdesc) {
		this.paramdesc = paramdesc;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCatalogid() {
		return this.catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

}