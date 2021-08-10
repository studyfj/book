<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<!--永远固定相对路劲的结果，写到工程路径下-->
	<%--静态包含--%>
	<%@include file="/pages/common/head.jsp"%>>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	<script type="text/javascript">
		$(function () {

			// ajax请求
			$("#username").blur(function () {
				// 1.获取用户名
				let value = this.value;
				$.getJSON("http://localhost:8080/book/userServlet", "action=ajaxExistsUsername&username="+value,function (data) {
					if (data.existsUsername){
						$("span.errorMsg").text("用户名已存在！")
					}else {
						$("span.errorMsg").text("用户名可用！")
					}
				})
			})

			// 验证码图片绑定单机事件
			$("#code_img").click(function () {
				this.src = "${basePath}Kaptcha.jpg?date="+ new Date();
			})


			// 注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				// 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				// 获取用户框中的内容
				var usernameText = $("#username").val();
				// 获取正则表达式对象
				var usernamePatt = /^\w{5,12}$/;
				// test验证
				if (!usernamePatt.test(usernameText)) {
					// 提示用户结果
					$("span.errorMsg").text("用户名不合法！")
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				var passwordText = $("#password").val();
				var passwordPatt = /^\w{5,12}$/;
				if (!passwordPatt.test(passwordText)) {
					$("span.errorMsg").text("密码不合法！");
					return false;
				}
				// 验证确认密码：和密码相同
				var repText = $("#repwd").val();
				if (repText != passwordText) {
					$("span.errorMsg").text("密码不一致！");
					return false;

				}
				// 邮箱验证：xxxxx@xxx.com
				let emailText = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailPatt.test(emailText)) {
					$("span.errorMsg").text("邮箱格式不合法！");
					return false;
				}
				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。(目前固定123456)
				let codeText = $("#code").val();
				codeText = $.trim(codeText);
				if (codeText == null || codeText == "") {
					$("span.errorMsg").text("验证码不能为空！");
					return false;
				}
				$("span.errorMsg").text("")

			})


		})
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register">

									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
<%--											value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>--%>
											value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
<%--											value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"/>--%>
											value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img id="code_img" src="Kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>