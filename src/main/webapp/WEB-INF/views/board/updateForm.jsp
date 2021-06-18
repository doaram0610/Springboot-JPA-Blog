<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	
	<c:if test="${board.user.id == principal.user.id }"> 
		<a href="#" id="btn-update" class="btn btn-warning">저장</a>
	</c:if>		
	<button id="btn-list" class="btn btn-secondary" onclick="history.back()">목록</button>
	<br /> <br />	
	<input type="hidden" id="id" value="${board.id }"/>
	<div class="form-group">
		<input type="text" class="form-control"  id="title" value="${board.title}">
	</div>
	<hr />
	<div class="form-group">
		<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
	</div>
	<hr />

</div>

<script>
	$('.summernote').summernote({
		placeholder : 'Enter Content',
		tabsize : 2,
		height : 300
	});
</script>
<script src="/blog/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>