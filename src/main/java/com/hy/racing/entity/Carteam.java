package com.hy.racing.entity;
// default package
// Generated 2017-5-3 17:20:02 by Hibernate Tools 4.3.5.Final

/**
 * Carteam generated by hbm2java
 */
public class Carteam implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer status;

	public Carteam() {
	}

	public Carteam(String name, Integer status) {
		this.name = name;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}