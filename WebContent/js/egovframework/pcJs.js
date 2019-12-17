function start() {
		testInterval = setInterval(function() {
			location.reload()
		}, 1000);
	}

	/** 새로고침 및 폰트 크기 조절 */
	$(document).ready(
			function setinterval() {
						start();
						$("#tbs").css("height", $(window).height() + "px");
						$('span.1')
								.contents()
								.unwrap()
								.wrap(
										"<p style='margin-bottom:50px' class='heartBeat'><span style='font-size:150px;'></span></p>");

						/** [O],[X] 채점하기 */
						$(document)
								.on(
										'click',
										'button[name=OX]',
										function(mcAnswer) {
											mcAnswer = $(this).val();
											console.log("button >>> "
													+ mcAnswer);

											clearInterval(testInterval);

											var mark = confirm("정답을 "
													+ mcAnswer + "로 채점하시겠습니까?");
											
											var oCount = $(".oCountH").val();
											if(typeof(oCount) === "undefined"){
												oCount = 0;
											}
											
											var xCount = $(".xCountH").val();
											if(typeof(xCount) === "undefined"){
												xCount = 0;
											}

											if (mcAnswer == 'O') {
												var oMessage = "정답자는 "+ oCount + " 명";
												var xMessage = "오답자는 "+ xCount + " 명";

												$("#imgO").attr("src","../img/O.png");
												$("#imgX").attr("src","../img/X.png");

												$("#oSpan").html(oMessage);
												$("#xSpan").html(xMessage);

											} else if (mcAnswer == 'X') {
												var oMessage = "오답자는 "+ oCount + " 명";
												var xMessage = "정답자는 "+ xCount + " 명";

												$("#imgO").attr("src","../img/X.png");
												$("#imgX").attr("src","../img/O.png");
												
												$("#oSpan").html(oMessage);
												$("#xSpan").html(xMessage);
											}

											if (mark == true) {
												$.ajax({
															type : 'POST',
															url : '../controller/updateMcAnswerAndEmpPass.do',
															data : {
																"mcAnswer" : mcAnswer
															},
															dataType : 'text',
															cache : false,
															success : function(
																	data) {
																//alert(data);
																if (data == "success") {
																} else if (data = "fail") {
																	alert("채점이 완료되지 못했습니다.");
																}
															},
															error : function(
																	request,
																	status,
																	error) {
																alert("code:"
																		+ request.status
																		+ "\n"
																		+ "message:"
																		+ request.responseText
																		+ "\n"
																		+ "error:"
																		+ error);
															}
														}) // $.ajax 끝
											}
										});

					});

	/** [추가]하기 */
	function addEmp() {
		window.name = "parentForm";
		var openWin = window.open('childForm', "_blank",
				"width=400,height=200, resizable=no, scrollbars=no, status=no");
		openWin.location.href = "../controller/goAddEmp.do";
	}

	/** [마감]하기 */
	function finish(YN) {
		if (YN == 'Y') {
			start();
		} else if (YN == 'N') {
			clearInterval(testInterval);
		}

		$.ajax({
			type : 'POST',
			url : '../controller/updateQuestionStart.do',
			data : {
				"mcQStart" : YN
			},
			dataType : 'text', // 컨트롤러에서 받을 데이터 타입
			cache : false,
			success : function(data) { // data는 서버로부터 전송받은 결과(JSON)
				if (data == 'N') {
					alert("문제가 마감되었습니다.");
				} else if (data == 'Y') {
					alert("문제가 시작되었습니다.");
				} else if (data == 'fail') {
					alert("버튼이 눌리지 않았습니다.");
				}

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		}) //$.ajax 끝
	} // function finish() 끝

	/** [초기화]하기 */
	function reset() {
		alert("문제를 리셋하시겠습니까?");

		$.ajax({
			type : 'POST',
			url : '../controller/updateReset.do',
			dataType : 'text',
			cache : false,
			success : function(data) {
				console.log(data);
				if (data == 'success') {
					alert("문제가 리셋되었습니다.");
				} else if (data == 'fail') {
					alert("리셋을 실패하였습니다.");
				}

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		}) // $.ajax 끝
	} // function reset() 끝