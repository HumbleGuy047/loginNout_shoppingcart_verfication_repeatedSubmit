<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<a href="javascript:go(1)">首页</a>
<!-- <a href="/product?currentPage=${page.prePage}">上一页</a> -->	
<a href="javascript:go(${page.prePage})">上一页</a>
<c:forEach begin="${page.pageIndex.beginIndex}" end="${page.pageIndex.endIndex}" var="index">
	<c:if test="${index==page.currentPage}">
		<span style="color: red">${index}</span>
	</c:if>
	<c:if test="${index!=page.currentPage}">	
		<a href="javascript:go(${index})">${index}</a>
	</c:if>
</c:forEach>
<!-- <a href="/product?currentPage=${page.nextPage}">下一页</a> -->
<a href="javascript:go(${page.nextPage})">下一页</a> 

<a href="javascript:go(${page.totalPage})">尾页页</a>
总条数：${page.totalCount}
当前页：${page.currentPage }/${page.totalPage}
<input name="currentPage" type="number" min="1" max="${page.totalPage}" value="${page.currentPage }"/>
<input name="currentPageJump" type="submit" value="跳"/>
每页显示 
	<select name="pageSize" onchange="document.getElementById('form2').submit()">
		<option ${page.pageSize==5?'selected':''}>5</option>
		<option ${page.pageSize==10?'selected':''}>10</option>
		<option ${page.pageSize==20?'selected':''}>20</option>
	</select>
条数据