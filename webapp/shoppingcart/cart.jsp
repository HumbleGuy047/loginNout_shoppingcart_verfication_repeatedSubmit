<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>俺的购物车</h1>
	<table cellspacing="0" cellpadding="0" border="1" width="80%">
		<tr>
			<td>名称</td>
			<td>价格</td>
			<td>数量</td>
			<td>操作</td>
		</tr>
		
		<c:forEach items="${sessionScope.SHOPPINGCART_IN_SESSION.items}" var="item" >
			<tr>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td>${item.num}</td>
				<td><a href="/shoppingCart?cmd=delete&id=${item.id}">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<c:if test="${empty SHOPPINGCART_IN_SESSION.items}">
				<td colspan="4" align="center">空空如也</td>
			</c:if>
			<c:if test="${!empty SHOPPINGCART_IN_SESSION.items}">
				<td colspan="4" align="right">商品总价：${sessionScope.SHOPPINGCART_IN_SESSION.totalPrice}</td>
			</c:if>
		</tr>
	</table>
	<a href="/shoppingcart/shopping.jsp">继续购物</a>
</body>
</html>