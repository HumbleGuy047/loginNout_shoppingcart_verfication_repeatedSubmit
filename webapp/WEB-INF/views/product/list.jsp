<%@page import="java.util.UUID"%>
<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>巧克力电子商库 - 列表</title>
		<script type="text/javascript">
			function go(currentPage){
				//将当前页的数据设置给currentPage隐藏域
				document.getElementById("currentPage").value=currentPage;
				// 提交表单
				document.forms[1].submit();
			}
		</script>
	</head>
	<body>
		欢迎：[${sessionScope.USER_IN_SESSION.username}] 登陆！&nbsp;
		<a href="/logout">注销</a>
		<hr/>
		<div align="center">
			<h1>电子商品列表</h1>
		</div>
		<div align="center" style="margin: 2%">
			<form action="/product?cmd=edit" method="post">
				<button type="submit" style="height: 6%; width: 6%">添加</button>
			</form>
		</div>
		<!-- Filtered Query Input -->
		<form action="/product" method="post" id="form2">
			<input type="hidden" name="token" value="${sessionScope.TOKEN_IN_SESSION}"/>
			<input type="hidden" name="currentPage" id="currentPage"/>
			<div align="center">
					商品名称：<input name="productName" type="text" value="${qo.productName}"> 
					商品零售价：<input name="minSalePrice" type="number" value="${qo.minSalePrice}">-<input name="maxSalePrice" type="number"  value="${qo.maxSalePrice}">
					商品种类：<select name="dir_id">
							<option value="">所有</option>
						<c:forEach items="${proDirList}" var="proDir">
							<option value="${proDir.id}" ${proDir.id==qo.dir_id?'selected':''}>${proDir.dirName}</option>
						</c:forEach>
					</select>
					关键字：<input name="keyword" placeholder="商品名、供应商、品牌" value="${qo.keyword}"/>
					<input type="submit" value="查询">
			</div>
		
			<div align="center">
					<table border="1" width="70%" height="50px" cellspacing="0" cellpadding="10px">
						<thead>
							<tr style="background-color: #fbff46">
								<!-- productName, dir_id, salePrice, supplier, brand, discount, costPrice -->
								<th>ID</th><th>商品名称</th><th>商品分类</th>
								<th>零售价</th><th>供应商</th><th>品牌</th>
								<th>折扣</th><th>成本价</th><th>操作</th>
							</tr>
						</thead>	
						<!-- Example: 1	罗技M90	3	90.0	罗技	罗技	0.5	35.0 -->
						<%--
							<tr>
								<td>罗技M90</td><td>3</td><td>90.0</td>
								<td>罗技</td><td>罗技</td><td>0.5</td>
								<td>35.0</td><td><a href="#">编辑</a>|<a href="#">删除</a></td>
							</tr>
						--%>
						<tbody>
							<c:if test="${empty page.listData}">
								<tr>
									<td colspan=9 style="text-align: center; color: red">无数据</td>
								</tr>
							</c:if>
							<c:forEach items="${page.listData}" var="pro" varStatus="vs">
								<tr align="center" style="background-color: ${vs.count%2==0?'#cbb1c7':'white'}">
									<td>${pageScope.pro.id}</td>
									<td>${pageScope.pro.productName}</td>
									<td>
										<!-- <c:choose>
											<c:when test="${item.dir_id==1}">鼠标</c:when>
											<c:when test="${item.dir_id==2}">无线鼠标</c:when>
											<c:when test="${item.dir_id==3}">有线鼠标</c:when>
											<c:when test="${item.dir_id==4}">游戏鼠标</c:when>
										</c:choose> -->
										<c:forEach items="${proDirList}" var="proDir">
											${pro.dir_id==proDir.id?proDir.dirName:''}
										</c:forEach>
									</td>
									<td>${pageScope.pro.salePrice}</td>
									<td>${pageScope.pro.supplier}</td>
									<td>${pageScope.pro.brand}</td>
									<td>${pageScope.pro.discount}</td>
									<td>${pageScope.pro.costPrice}</td>
									<td><a href="/product?cmd=edit&id=${pageScope.pro.id}">编辑</a>|<a href="/product?cmd=delete&id=${pageScope.pro.id}">删除</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="9" align="center">
									<!-- 分页链接 -->
								<jsp:include page="../commons/page.jsp"></jsp:include>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
	</body>
</html>