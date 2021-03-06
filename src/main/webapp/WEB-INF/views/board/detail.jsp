<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	
	<c:if test="${board.user.id == principal.user.id }"> 
		<a href="/blog/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
		
	<button id="btn-list" class="btn btn-secondary" onclick="history.back()">목록</button>
	<br /> <br />
	<div>
		글번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span id="username"><i>${board.user.username} </i></span>
	</div>
	<br />
	<div class="form-group">
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div class="form-group">
		<div>${board.content}</div>
	</div>
	<hr />

</div>

<script src="/blog/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>