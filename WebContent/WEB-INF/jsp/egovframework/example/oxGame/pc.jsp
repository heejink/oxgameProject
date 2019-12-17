<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OX Game PC</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="../js/egovframework/pcJs.js"></script>

<link rel="stylesheet" href="../css/egovframework/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<%
	request.setCharacterEncoding("UTF-8");

	String result = null;
	result = (String) request.getAttribute("result");
%>
</head>
<body>
	<div class=buttons style="text-align: right; margin-bottom: 5px;">
		<div class=reset style="float: left; width: 5%">
			<input type="button" class="btn btn-outline-danger" value="재시작"
				onclick="reset()">
		</div>
		<div class=next style="float: left; width: 83%">
			<input type="button" class="btn btn-outline-secondary" value="다음"
				onclick="finish('Y')">
		</div>
		<div class=add style="float: left; width: 5%">
			<input type="button" class="btn btn-outline-warning" value="추가"
				onclick="addEmp()"> <input type="hidden" id="addEmp"
				name="addEmp" value="">
		</div>
		<div class=finish style="float: left; width: 5%">
			<input type="button" class="btn btn-outline-danger" value="마감"
				onclick="finish('N')"> <input type="hidden" id="mcQStart"
				name="mcQStart" value=<%=result%> />
		</div>
	</div>
	<div>
		<h1>
			<br>
		</h1>
	</div>


	<div id="empListdiv">
		<table border="1">
			<tr>
				<th width="50%"><button type="button"
						class="btn btn-primary btn-lg btn-block" id="1" name="OX"
						value="O">
						<h1>O</h1>
					</button></th>
				<th width="50%"><button type="button"
						class="btn btn-danger btn-lg btn-block" id="2" name="OX" value="X">
						<h1>X</h1>
					</button></th>
			</tr>
			<tr style="text-align: center;" id="tbs">
				<td>
					<div id="oTd">
						<p id="oSpan" style="font-size: 7em;"></p> <img id="imgO">
					</div> 
					
					<c:forEach items="${oList}" var="row" varStatus="o">
						<input type="hidden" class="oCountH" value="${fn:length(oList)}">
						<span class="${o.count}" name="O" style="font-size: 50px;margin:5px">${row.empname}</span>
					</c:forEach>
				</td>

				<td>
				<div id="xTd">
						<p id="xSpan" style="font-size: 7em;"></p> <img id="imgX">
					</div>
				<c:forEach items="${xList}" var="row" varStatus="x">
					<input type="hidden" class="xCountH" value="${fn:length(xList)}">
						<span class="${x.count}" name="X" style="font-size: 50px; margin:5px">${row.empname}</span>
					</c:forEach></td>
			</tr>
		</table>
	</div>
</html>