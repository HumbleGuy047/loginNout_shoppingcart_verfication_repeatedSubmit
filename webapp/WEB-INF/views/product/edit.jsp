<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>巧克力电子商库 - 编辑</title>
</head>
<body>
	<div align="center">
		<h1>${pro == null? "添加":"更改"}</h1>
	</div>
	<div align="center" style="padding-left: 7%">
		<form action="/product?cmd=save" method="post">
			<input name="id" style="text-align: center; width: 40%; height: 5%" type="text" hidden="true" value="${pro.id}"/><br/>
				<table>
					<tbody>
						<tr>
							<td>商品名：</td><td><input name="productName" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.productName}"/></td>
						</tr>
						<tr>
							<td>商品分类：</td>
							<td>
								<select name="dir_id">
									<c:forEach items="${proDirList}" var="proDir">
										<option value="${proDir.id}" ${pro.dir_id==proDir.id? 'selected':''}>${proDir.dirName}</option>		
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>零售价：</td><td><input name="salePrice" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.salePrice}"/></td>
						</tr>
						<tr>
							<td>供应商：</td><td><input name="supplier" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.supplier}"/></td>
						</tr>
						<tr>
							<td>品牌：</td><td><input name="brand" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.brand}"/></td>
						</tr>
						<tr>
							<td>折扣：</td><td><input name="discount" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.discount}"/></td>
						</tr>
						<tr>
							<td>成本价：</td><td><input name="costPrice" style="text-align: center; width: 40%; height: 5%" type="text" value="${pro.costPrice}"/></td>
						</tr>
					</tbody>
				</table>
				<button style="width: 10%; height: 5%" type="submit" name="submit">${pro == null? "添加":"更改"}</button>
		</form>
	</div>
</body>
</html>