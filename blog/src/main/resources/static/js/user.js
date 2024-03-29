let index = {
	init: function() {
		$('#btn-save').on('click', function() { // function(){} , () => {} this를 바인딩하기 위해서!
			index.save();
		})
		
	},
	save: function() {
		let data = {
			username: $('#username').val(),
			email: $('#email').val(),
			password: $('#password').val()
		};
		console.log(data);

		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // HTTP body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json", // 요청을 서버로해서 응답이 왔을 때 json이라면 javascript 타입으로 변경시켜줍니다.
			success: function(response) {
				alert("회원가입이 완료되었습니다.");
				console.log(response);
				location.href = "/";
			},
			error: function(xhr, status, error) {
				alert("오류가 발생하였습니다. 상태 코드: " + xhr.status);
				console.log("에러: "+error);
			}
		});
	}
}
console.log("user.js");

index.init();