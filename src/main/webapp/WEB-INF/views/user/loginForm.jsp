<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	<form action="/blog/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" placeholder="Enter Username" id="username" name="username">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> 
			<input type="password" class="form-control" placeholder="Enter Password" id="password" name="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> 
			<input class="form-check-input" type="checkbox" id="email" name="email"> Remember me
			</label>
		</div>	
		<button id="btn-login" class="btn btn-primary">로그인</button>	
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=9fb8744cbc91ff1c85fd1641698b3d84&redirect_uri=http://localhost:8000/blog/auth/kakao/callback&response_type=code"><img height="38" src="/blog/image/kakao_login_small.png" /></a>	
		</form>


</div>

<script src="/blog/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>