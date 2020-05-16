<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%--静态包含头部内容--%>
	<%@include file="/pages/common/head.jsp"%>
<meta charset="UTF-8">
<title>图书管理</title>
<script type="text/javascript">
	$(function () {
		//给删除绑定单机事件
		$("a.deleteClass").click(function () {
			//this 代表点击的当前行的dom对象
			return confirm("你确定删除么"+$(this).parent().parent().find("td:first").text()+"?")
		})
		//给跳转带指定绑定单机事件
		$("#goToPage").click(function () {
			//获取要跳转的页码数
			var pageNo = $("#pn_input").val()
			//使用js提供的location对象的.href属性 传入参数(修改地址信息进行跳转)
			location.href = "${pageContext.getAttribute("basePath")}manager/bookServlet?action=page&pageNo="+pageNo+"&pageSize=4"
		})
	})

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含菜单内容--%>
		<%@include file="/pages/common/manger_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageTotal}&pageSize=4">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageTotal}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}&pageSize=4">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="manager/bookServlet?action=page&pageNo=1&pageSize=4">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}&pageSize=4">上一页</a>
			</c:if>
			<%--显示具体的页码号--%>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal <= 5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<c:if test="${i == requestScope.page.pageNo}">
							[${i}]
						</c:if>
						<c:if test="${i != requestScope.page.pageNo}">
							<a href="manager/bookServlet?action=page&pageNo=${i}&pageSize=4">${i}</a>
						</c:if>

					</c:forEach>
				</c:when>

			</c:choose>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}&pageSize=4">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}&pageSize=4">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input id="goToPage" type="button" value="确定">
		</div>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>