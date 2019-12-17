<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("userName");
%>
<%!HttpSession hs = null;%>
<%!String session = "";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OX Game Mobile</title>
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">
*{font-family: 'S-CoreDream-8Heavy';}

.layer {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	text-align: center;
}

.layer .content {
	display: inline-block;
	vertical-align: middle;
	width: 210px;
}

.layer .blank {
	display: inline-block;
	width: 0;
	height: 100%;
	vertical-align: middle;
}

@font-face {
	font-family: 'S-CoreDream-8Heavy';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// 넘겨야 할 값 : 이름, 세션값, 제출한 답
		var user = $("#name").val();
		// console.log(user);
		var session = $("#session").val();
		// console.log(session);

		var button_text;

		document.getElementById("userName").innerHTML = user;
		$("button").click(function() {

			button_text = $(this).attr('value');
			console.log(button_text);

			var ajson = {
				"empName" : user,
				"empSession" : session,
				"empAnswer" : button_text
			}
			
	 		$.ajax({
				type : 'POST',
				url : '../controller/updateEmpAnswer.do',
				data : ajson,
				dataType : 'text',
				cache : false,
				success : function(data) { // data는 서버로부터 전송받은 결과(JSON)
					if (data == 'success'){
						alert("답을 전송하였습니다.");
					} else if (data == 'fail') {
						alert("답이 전송에 실패하였습니다.");
					} else if (data == 'deny') {
						alert("답을 제출하실 수 없습니다.");
					}
					
				}, // success 끝
				
	 			error : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				} // error 끝
				
			}); // $.ajax 끝

		});

	})
</script>
</head>
<body>
	<%
		hs = request.getSession(true);
		name = (String) hs.getAttribute("name");
		if (name != null) {
	%>
	<div class="layer">
		<span class="content"><h1 id="userName"></h1> </br>
			<button type="button" class="btn btn-primary btn-lg"
				style="margin: 5%" id="1" name="1" value="O">O</button>
			<button type="button" class="btn btn-success btn-lg"
				style="margin: 5%" id="2" name="2" value="X">X</button></span> <span
			class="blank"></span>
	</div>

	<input type="hidden" id="name" name="name" value=<%=name%> />
	<input type="hidden" id="session" name="session" value=<%=session%> />


	<%
		} else {
	%>

	<p>
	<h3>처음부터 이용해주세요</h3>
	</p>
	<br>
	<a href="controller/getName.do">시작페이지로</a>
	<%
		}
	%>
</body>
</html>