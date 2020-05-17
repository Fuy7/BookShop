<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含头部内容--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		//为删除绑定单机事件
		$("a.deleteCarItem").click(function () {
			return confirm("确认删除["+$(this).parent().parent().find("td:first").text()+"]此项商品吗?")
		})
		//为清空购物车绑定单机事件
		$("a.cleanCarItem").click(function () {
			return confirm("确认清空所有商品吗?")
		})
		//给输入框绑定change事件
		$("input.updateCount").change(function () {

			var bookID = $(this).attr('bookID');
			if( confirm("确认修改 ["+$(this).parent().parent().find("td:first").text()+"] 此商品数量为"+this.value)) {
				//发送请求给servlet
				location.href = "${pageContext.getAttribute("basePath")}carServlet?action=updateCount&id="+bookID+"&count="+this.value
			}else {
				//defaultValue 属性是表单项Dom对象的属性。它表示默认的value属性值。
				this.value = this.defaultValue
			}
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含导航内容--%>
		<%@include file="/pages/common/nav.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${ empty sessionScope.car.carItems}">
				<tr>
					<td colspan="5"><a href="index.jsp">购物车中暂无商品,快去添加吧！</a></td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.car.carItems}" var="carItem">
				<tr>
					<td>${carItem.value.name}</td>
					<td>
						<input bookID="${carItem.value.id}" class="updateCount" type="text" value="${carItem.value.count}" style="width: 50px">
					</td>
					<td>${carItem.value.price}</td>
					<td>${carItem.value.totalPrice}</td>
					<td><a class="deleteCarItem" href="carServlet?action=deleteCarItem&id=${carItem.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
			<c:if test="${not empty sessionScope.car.carItems}">
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.car.totalCount}</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${sessionScope.car.totalPrice}</span>元</span>
					<span class="cart_span"><a class="cleanCarItem" href="carServlet?action=cleanCarItem">清空购物车</a></span>
					<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
				</div>
			</c:if>


	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>