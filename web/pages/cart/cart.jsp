<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含--%>
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function(){
			$("a.deleteItem").click(function () {
				return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗?");
			})

			// 清空购物车绑定单机事件
			$("#clearItem").click(function () {
				return confirm("确定要清空购物车吗?");

			})
			// 输出框绑定失去焦点事件===onchange事件(免去判断)
			$("input.updateCount").change(function () {
				// 商品名称
				let name = $(this).parent().parent().find("td:first").text();
				let id = $(this).attr("bookId");
				// 商品数量
				let count = this.value;
				if (confirm("你确定将【"+name+"】商品数量修改为:"+[count]+"吗?")) {
					// 发起请求，给服务器保存修改
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;
				}else {
					// defaultValue表单项默认value属性值
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
				<a href="pages/order/order.jsp">我的订单</a>
				<a href="index.jsp">注销</a>&nbsp;&nbsp;
				<a href="index.jsp">返回</a>
			</div>
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
			<c:if test="${empty sessionScope.cart.items}">
					<tr>
						<td colspan="5"><a href="index.jsp">亲，当前购物车为空!快去浏览商品吧!</a></td>
					</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--如果购物车非空的情况--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input type="text" class="updateCount"
								   bookId = "${entry.value.id}"
								   value="${entry.value.count}" style="width: 50px">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a  class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			
		</table>

		<%--如果购物车非空才输出--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=clear" id="clearItem">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>