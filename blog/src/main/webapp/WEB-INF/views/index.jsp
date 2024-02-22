<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-3" style="">
			<!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width: 100%"> -->
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">See Profile</a>
			</div>
		</div>
	</c:forEach>

	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${boards.pageable.pageNumber  == (pageInfo.startPage-1)}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.pageable.pageNumber-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.pageable.pageNumber-1}">Previous</a></li>	
			</c:otherwise>
		</c:choose>

		<c:forEach var="pageNumber" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
			<c:choose>
				<c:when test="${pageNumber == pageInfo.currPage}">
					<li class="page-item disabled"><a class="page-link" href="?page=${pageNumber-1}">${pageNumber}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${pageNumber-1}">${pageNumber}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${boards.pageable.pageNumber  == (pageInfo.endPage-1)}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.pageable.pageNumber+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.pageable.pageNumber+1}">Next</a></li>	
			</c:otherwise>
		</c:choose>

	</ul>

</div>

<%@ include file="layout/footer.jsp"%>


