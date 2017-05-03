package com.hy.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 页面对象，主要用于JQuery DataTable分页查询时
 * 
 * @版本 1.0
 * @作者 <a href="mailto:54fbj@163.com"/>樊炳江</a>
 * @创建时间 2013-3-10 上午2:37:33
 * @param <E>
 */
public class PageObj<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<E> data = new ArrayList<E>(0);// 显示的数据集合
	private int recordsTotal;// 总行,即没有过滤的记录数（数据库里总共记录数）
	private int recordsFiltered;// 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
	private int start = 0;// 第一条数据的起始位置，比如0代表第一条数据
	private int length = 10;// 每页显示的条数,这个也可能是-1，代表需要返回全部数据
	private int draw;// 请求次数计数器，每次发送给服务器后又原封返回

	private String searchKey;// 过滤框的值

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void setCount(int count){
		this.recordsFiltered = count;
		this.recordsTotal = count;
	}
}
