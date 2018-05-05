<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="./jquery/jquery-3.3.1.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<button onclick="list()">listPolicy</button>
	<button onclick="listStu()">listStu</button>
	<table id="table1" border="1">
		<tr align="center">
			<th>policyid</th>
			<th>policycode</th>
			<th>policyproductsumamt</th>
			<th>transactiondate</th>
		</tr>

	</table>
</body>
<script type="text/javascript">
	/* 	$(doucument).ready(function(){
	 list();
	 }); */

	function list() {
		debugger;
		$.ajax({
			url : "/TestSpringMvc/listPolicy",
			type : 'get',
			success : function(responseStr) {
				var policys = responseStr.policy;
				debugger
				$.each(policys, function() {
					var tr = $("<tr align='center'> </tr>");
					$("<td/>").html(this.policyid).appendTo(tr)
					$("<td/>").html(this.policycode).appendTo(tr)
					$("<td/>").html(this.policyproductsumamt).appendTo(tr)
					$("<td/>").html(this.transactiondate).appendTo(tr)
					$("#table1").append(tr)
				});

			}

		})
	}

	function listStu() {
		debugger
		var paramMap = {
			"teacherid" : 1,
			"aaa" : 2
		};
		
		var isBatch = false;
		$.ajax({
			url : "listStudnetByCondition",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(paramMap),
			dataType : 'json',
			success : function(responseStr) {
				debugger;

			}

		})
	}
</script>
</html>