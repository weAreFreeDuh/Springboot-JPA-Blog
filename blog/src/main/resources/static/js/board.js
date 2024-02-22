let index = {
	init: function() {
		$('#btn-board-write').on('click', function() { // function(){} , () => {} this를 바인딩하기 위해서!
			index.save();
		})
		$('#boardModify').on('click', function() { // function(){} , () => {} this를 바인딩하기 위해서!
			index.boardModify();
		})
		$('#boardList').on('click', function() { // function(){} , () => {} this를 바인딩하기 위해서!
			index.boardList();
		})
		$('#boardDelete').on('click', function() { // function(){} , () => {} this를 바인딩하기 위해서!
			index.boardDelete();
		})
		
	},
	save: function() {
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		};
		console.log(data);

		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",
			url: "/api/board/write",
			data: JSON.stringify(data), // HTTP body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json", // 요청을 서버로해서 응답이 왔을 때 json이라면 javascript 타입으로 변경시켜줍니다.
			success: function(response) {
				alert("글쓰기가 완료되었습니다.");
				console.log(response);
				location.href = "/";
			},
			error: function(xhr, status, error) {
				alert("오류가 발생하였습니다. 상태 코드: " + xhr.status);
				console.log("에러: "+error);
			}
		});
	},
	
	boardList : function(){
		location.href="/";
	},
	
	boardDelete: function() {
		let boardId = $('#boardId').text();
		console.log(boardId);

		$.ajax({
			type: "DELETE",
			url: "/api/board/delete",
			data: JSON.stringify(boardId), 
			contentType: "application/json; charset=utf-8", 
			dataType: "json", 
			success: function(response) {
				alert("삭제가 완료되었습니다.");
				console.log(response);
				location.href = "/";
			},
			error: function(xhr, status, error) {
				alert("오류가 발생하였습니다. 상태 코드: " + xhr.status);
				console.log("에러: "+error);
			}
		});
	},
	
	boardModify: function() {
		location.href="/board/"+$('#boardId').text();
	},
	
}
console.log("user.js");

index.init();