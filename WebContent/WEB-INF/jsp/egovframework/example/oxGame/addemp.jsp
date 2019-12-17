<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OX Game 탈락자 추가 하기</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">
	// [취소] 클릭시 
	$(document).on('click', '#cancle', function() {
		self.opener = self;
		window.close();
	});

	// [확인] 클릭시 
	$(document).on('click','#submit',
			function() {
				var user = $("#userName").val();
				var checked_radio = $('input:radio[name=btn]:checked').val();
				alert("user >>> " + user);

				$.ajax({
					type : 'GET',
					url : '../controller/updateAddEmpPass.do',
					data : {
						"empName" : user,
						"empAnswer" : checked_radio
					}, // 컨트롤러로 보낼 데이터 타입
					dataType : 'text', // 컨트롤러에서 받을 데이터 타입
					cache : false,
					success : function(data) { // data는 서버로부터 전송받은 결과(JSON)
						//alert(data);
						if (data == 'success') {
							window.close();
						} else if (data == 'fail') {
						}
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				})
			});
</script>
<%
	request.setCharacterEncoding("UTF-8");

	System.out.println(" >>> addemp popup.jsp");
%>
</head>
<body>
	<div style="width: 100%; height: 100%; text-align: center;">
		<div>
			<br>
			<h3>정답자를 추가하시겠어요?</h3>
			<br>
			<form name="nameForm" method="get">
				<input type="text" id="userName" name="userName"
					placeholder="이름을 입력해주세요." />
				<input type="radio" name="btn" value="O">O
				<input type="radio" name="btn" value="X">X
			
				<br> <br>

				<button type="button" id="cancle" name="cancle" value="">취소</button>
				<button type="button" id="submit" name="submit" value="submit">확인</button>
			</form>
		</div>
	</div>

</body>
</html>