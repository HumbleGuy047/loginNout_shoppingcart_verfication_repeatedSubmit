package com.redKango.pss.page;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

// 分页的结果对象
@Data
@NoArgsConstructor
public class PageResult {
	// 结果及数据
	private List listData;
	// 总条数
	private Integer totalCount;
	// 当前页
	private Integer currentPage;
	// 总页数 
	private Integer totalPage;
	// 上一页
	private Integer prePage;
	// 下一页
	private Integer nextPage;
	// 页面大小 （本页有多少数据）
	private Integer pageSize;
	// 首页
	private Integer beginPage = 1;
	// 跳转页面按钮算法
	PageIndex pageIndex;

	public PageResult(List listData, Integer totalCount, Integer currentPage, Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		pageIndex = PageIndex.getPageIndex(3, currentPage, pageSize);

		// 计算数据
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize
				: this.totalCount / this.pageSize + 1;
		this.prePage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
	}
}
