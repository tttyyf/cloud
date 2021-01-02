package com.education.bean;
//分页的数据模型
public class PageModel {
	//当前页码
	int pageIndex;
	//上一页
	int prePage;
	//下一页
	int nextPage;
	//总页数
	int totalPage;
	//总记录条数
	int totalRecordNum;
	//查询的起始位置
	int startIndex;
	//每页显示的记录条数
	int pageSize = 10;
	
	//得到当前页码
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		//合理化判断
		if(pageIndex <= 0) { //小于等于0
			this.pageIndex = 1;
		}else if(pageIndex > this.getTotalPage()) {//大于总页数
			this.pageIndex = this.getTotalPage();
		}
		this.pageIndex = pageIndex;
	}
	
	public int getPrePage() {
		//上一页 = 当前页码-1
		int item = pageIndex - 1;
		if(item <= 0) {
			return 1;
		}
		return item;
	}
	
	public void setPrePage(int prePage) {
		//合理化判断
		if(prePage <= 0) {//小于等于0
			this.prePage = 1;
		}
		this.prePage = prePage;
	}
	
	public int getNextPage() {
		//下一页 = 当前页码 + 1
		int item = pageIndex + 1;
		if(item > this.totalPage) {
			return this.getTotalPage();
		}
		return item;
	}
	
	public void setNextPage(int nextPage) {
		//合理化判断
		if(prePage <= 0) {//小于等于0
			this.prePage = 1;
		}else if(nextPage > this.getTotalPage()) {
			this.nextPage = this.getTotalPage();
		}
		this.nextPage = nextPage;
	}
	
	public int getTotalPage() {   
		int item = (this.getTotalRecordNum() + this.pageSize-1)/this.getPageSize();
		return item;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalRecordNum() {
		return totalRecordNum;
	}
	
	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}
	
	public int getStartIndex() {
		int item = (this.getPageIndex()-1)* this.getPageSize();
		return item;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public PageModel(int pageIndex, int totalRecordNum) {
		this.setPageIndex(pageIndex);
		this.setTotalRecordNum(totalRecordNum);
	}
	
	public PageModel() {
		super();
	}
	
	@Override
	public String toString() {
		return "PageModel [pageIndex=" + pageIndex + ", prePage=" + prePage + ", nextPage=" + nextPage + ", totalPage="
				+ totalPage + ", totalRecordNum=" + totalRecordNum + ", startIndex=" + startIndex + ", pageSize="
				+ pageSize + "]";
	}
}
