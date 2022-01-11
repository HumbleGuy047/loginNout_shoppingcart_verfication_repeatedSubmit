<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function change(){
		// 浏览器会保存get方式的请求，再次请求如果为同一个资源就直接将缓存的数据返回
		// 对策：在资源后加时间这样就永远不会相同
		document.getElementById("randomCode").src="/randomCode?" + new Date().getTime();
	}
</script>
</head>
<body>
	<h1>登陆</h1>
	<span style="color: red">${sessionScope.errorMsg}</span>
	<form action="/login" method="post">
		用户名：<input type="text" name="username"/><br/>
		密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"/><br/>
		验证码：<input name="checkCode" maxlength="5" style="width: 60px;"/>
		<img id="randomCode" alt="验证码" src="/randomCode" title="看不清？点击切换" onclick="change()">
		<input type="submit" value="登陆"/>
	</form>
</body>
</html>