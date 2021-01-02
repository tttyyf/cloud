package com.education.bean;
//��ҳ������ģ��
public class PageModel {
	//��ǰҳ��
	int pageIndex;
	//��һҳ
	int prePage;
	//��һҳ
	int nextPage;
	//��ҳ��
	int totalPage;
	//�ܼ�¼����
	int totalRecordNum;
	//��ѯ����ʼλ��
	int startIndex;
	//ÿҳ��ʾ�ļ�¼����
	int pageSize = 10;
	
	//�õ���ǰҳ��
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		//�����ж�
		if(pageIndex <= 0) { //С�ڵ���0
			this.pageIndex = 1;
		}else if(pageIndex > this.getTotalPage()) {//������ҳ��
			this.pageIndex = this.getTotalPage();
		}
		this.pageIndex = pageIndex;
	}
	
	public int getPrePage() {
		//��һҳ = ��ǰҳ��-1
		int item = pageIndex - 1;
		if(item <= 0) {
			return 1;
		}
		return item;
	}
	
	public void setPrePage(int prePage) {
		//�����ж�
		if(prePage <= 0) {//С�ڵ���0
			this.prePage = 1;
		}
		this.prePage = prePage;
	}
	
	public int getNextPage() {
		//��һҳ = ��ǰҳ�� + 1
		int item = pageIndex + 1;
		if(item > this.totalPage) {
			return this.getTotalPage();
		}
		return item;
	}
	
	public void setNextPage(int nextPage) {
		//�����ж�
		if(prePage <= 0) {//С�ڵ���0
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
