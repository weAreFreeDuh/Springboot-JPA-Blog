<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
		
		<div class="form-group">
			<label for="title">board number</label> 
			<p id="boardId">${board.id}</p>
		</div>
		<hr>
		<div class="form-group">
			<label for="title">writer</label> 
			<p id="boardUsername">${board.user.username}</p>
		</div>
		<hr>
		<div class="form-group">
			<label for="title">Title</label> 
			<p>${board.title }</p>
		</div>
		
		<hr>
		<div class="form-group">
			<label for="content">Content</label>
			<div>${board.content}</div>
		</div>
		<hr>
	
	<button id="boardModify" class="btn btn-primary">Modify</button>
	<button id="boardList" class="btn btn-primary">List</button>
	<button id="boardDelete" class="btn btn-primary">Delete</button>
	
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


