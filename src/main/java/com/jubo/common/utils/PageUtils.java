package com.jubo.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalRecordNum;
	//每页记录数
	private int pageSize;
	//总页数
	private int pages;
	//当前页数
	private int pageNo;
	//列表数据
	private List<?> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalRecordNum  总记录数
	 * @param pageSize    每页记录数
	 * @param pageNo    当前页数
	 */
	public PageUtils(List<?> list, int totalRecordNum, int pageSize, int pageNo) {
		this.list = list;
		this.totalRecordNum = totalRecordNum;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.pages = (int)Math.ceil((double)totalRecordNum/pageSize);
	}

	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
}
