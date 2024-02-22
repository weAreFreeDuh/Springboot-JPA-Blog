<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="#" method="post">
		<div class="form-group">
			<label for="title">Title</label> 
			<input type="text" class="form-control"  id="title" name="title">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content"></textarea>
		</div>
	</form>
	<button id="btn-board-write" class="btn btn-primary">write</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


