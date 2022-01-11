<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>商品列表</h1>
	<form action="/shoppingCart?cmd=save" method="post">
		商品：<select name="name">
			<option>电脑</option>
			<option>手机</option>
			<option>平板</option>
		</select><br>
		
		数量：<input type="number" name="num"/>
		<input type="submit" value="任性买"/>
	</form>
</body>
</html>