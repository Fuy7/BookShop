<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/9
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>书城首页</title>
  <%--静态包含头部内容--%>
  <%@include file="/pages/common/head.jsp"%>
</head>
<script type="text/javascript">
  $(function () {
    //给跳转带指定绑定单机事件
    $("#goToPage").click(function () {
      //获取要跳转的页码数
      var pageNo = $("#pn_input").val()
      //使用js提供的location对象的.href属性 传入参数(修改地址信息进行跳转)
      location.href = "${pageContext.getAttribute("basePath")}manager/bookServlet?action=page&pageNo="+pageNo+"&pageSize=4"
    })
    //添加到购物车按钮
    $("button.addToCar").click(function () {
      //获取商品图书编号
      var bookID = $(this).attr("bookId");
      //使用js提供的location对象的.href属性 传入参数(修改地址信息进行跳转)
      location.href = "${pageContext.getAttribute("basePath")}carServlet?action=addToCar&id="+bookID
    })
  })

</script>
<body>
<div id="header">
  <img class="logo_img" alt="" src="static/img/logo.gif" >
  <span class="wel_word">网上书城</span>
  <div>
      <c:if test="${empty sessionScope.user}">
          <a href="pages/user/login.jsp">登录</a> |
          <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
      </c:if>
      <c:if test="${not empty sessionScope.user}">
          <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
          <a href="pages/order/order.jsp">我的订单</a>
          <a href="userServlet?action=logout">注销</a>&nbsp;
      </c:if>
    <a href="pages/cart/cart.jsp">购物车</a>
    <a href="pages/manager/manager.jsp">后台管理</a>
  </div>
</div>
<div id="main">
  <div id="book">

    <div class="book_cond">
      <form action="client/clientBookServlet" method="get">
        <input type="hidden" name="action" value="pageByPrice">
        价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
        <input id="max" type="text" name="max" value="${param.max}"> 元
        <input type="submit" value="查询" />
      </form>
    </div>

    <c:if test="${empty sessionScope.car}">
      <div style="text-align: center">
        <span> </span>
        <div>
          <span style="color: red">您的购物车为空</span>
        </div>
      </div>
    </c:if>

    <c:if test="${not empty sessionScope.car}">
      <div style="text-align: center">
        <span>您的购物车中有${sessionScope.car.totalCount}件商品</span>
        <div>
          您刚刚将<span style="color: red">${sessionScope.carItem.name}</span>加入到了购物车中
        </div>
      </div>
    </c:if>

      <%--items代表遍历的对象--%>
    <c:forEach items="${requestScope.page.items}" var="book">
      <div class="b_list">
        <div class="img_div">
          <img class="book_img" alt="" src="${book.imgPath}" />
        </div>
        <div class="book_info">
          <div class="book_name">
            <span class="sp1">书名:</span>
            <span class="sp2">${book.name}</span>
          </div>
          <div class="book_author">
            <span class="sp1">作者:</span>
            <span class="sp2">${book.author}</span>
          </div>
          <div class="book_price">
            <span class="sp1">价格:</span>
            <span class="sp2">￥${book.price}</span>
          </div>
          <div class="book_sales">
            <span class="sp1">销量:</span>
            <span class="sp2">${book.sales}</span>
          </div>
          <div class="book_amount">
            <span class="sp1">库存:</span>
            <span class="sp2">${book.stock}</span>
          </div>
          <div class="book_add" >
            <button bookId="${book.id}" class="addToCar">加入购物车</button>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>

  <div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
      <a href="client/clientBookServlet?action=page&pageNo=1&pageSize=4">首页</a>
      <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageNo-1}&pageSize=4">上一页</a>
    </c:if>
    <%--显示具体的页码号--%>
    <c:choose>
      <c:when test="${requestScope.page.pageTotal <= 5}">
        <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
          <c:if test="${i == requestScope.page.pageNo}">
            [${i}]
          </c:if>
          <c:if test="${i != requestScope.page.pageNo}">
            <a href="client/clientBookServlet?action=page&pageNo=${i}&pageSize=4">${i}</a>
          </c:if>

        </c:forEach>
      </c:when>

    </c:choose>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
      <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageNo+1}&pageSize=4">下一页</a>
      <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageTotal}&pageSize=4">末页</a>
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