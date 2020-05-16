<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<%--静态包含头部内容--%>
	<%@include file="/pages/common/head.jsp"%>


<title>尚硅谷会员注册页面</title>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<script type="text/javascript">
	//页面完整加载后
	$(function(){
		//为提交绑定点击事件
		$("#sub_btn").click(function(){
			//获取用户输入的信息
			var username = $("#username").val();
			//使用正则表达式验证输入
			var usernamePatt = /^\w{5,12}$/;
			//使用test函数
			if(!usernamePatt.test(username)){
				//提示用户不合法
				$("span.errorMsg").text("用户名不合法");
				return false;
			}
			
			//获取用户输入的信息
			var password = $("#password").val();
			//使用正则表达式验证输入
			var passwordPatt = /^\w{5,12}$/;
			//使用test函数
			if(!passwordPatt.test(password)){
				//提示密码不合法
				$("span.errorMsg").text("密码不合法");
				return false;
			}
			
			
			//校验确认密码
			//获取确认密码信息
			var repwd = $("#repwd").val();
			//比较
			if(repwd != password){
				//提示密码不合法
				$("span.errorMsg").text("密码不一致");
				return false;
			}
			
			//校验邮箱
			//获取内容
			var email = $("#email").val();
			//创建正则表达式
			var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			//使用test函数
			if(!emailPatt.test(email)){
				//提示邮箱不合法
				$("span.errorMsg").text("邮箱不合法");
				return false;
			}
			
			//检验验证码
			var code = $("#code").val();
			//去除空格
			console.log(code)
			
			code = $.trim(code);
			if(code == null || code == ""){
				//提示验证码不合法
				$("span.errorMsg").text("验证码不正确");
				return false;
			}
			
			//合法不提示
			$("span.errorMsg").text("");
			
		})
		// 给验证码的图片，绑定单击事件
		$("#code_img").click(function () {
			// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
			// src 属性表示验证码 img 标签的 图片路径。它可读，可写
			// alert(this.src);
			//d=" + new Date();  使得每次点击请求的地址都不容为了解决缓存问题
			this.src = "${basePath}kaptcha.jpg?d=" + new Date();    //每次点击都重新发送请求来更新图片
		});


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
								<h1>注册会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value="${requestScope.username }"
									autocomplete="off" tabindex="1" name="username" id="username" />
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
										   value="${requestScope.email }"

									autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code" value="abcd"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="width: 100px; height: 30px; margin-right: 60px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>