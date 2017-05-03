package com.hy.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 终端使用分页对象
 * 
 * @author FBJ
 * 
 */
public class MobilePageObj<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<E> data = new ArrayList<E>();// 数据
	private int currPage = 1;// 当前页码
	private int rows = 10;// 当前页面数据行数
	private int allRows;

	public MobilePageObj() {

	}

	public MobilePageObj(Integer currPage) {
		if (null != currPage && currPage > 0)
			this.currPage = currPage;
	}

	public MobilePageObj(int currPage, int rows) {
		this.currPage = currPage;
		this.rows = rows;
	}

	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		if (currPage > 0)
			this.currPage = currPage;
	}

	public int getAllPage() {
		return (allRows - 1) / rows + 1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public boolean hasNext() {
		return getAllPage() >= currPage;
	}

	public boolean isHasData(int count) {
		return (currPage - 1) * rows < count;
	}

	@Override
	public String toString() {
		return "MobilePageObj{" +
				"data=" + data +
				", currPage=" + currPage +
				", rows=" + rows +
				", allRows=" + allRows +
				'}';
	}
}
