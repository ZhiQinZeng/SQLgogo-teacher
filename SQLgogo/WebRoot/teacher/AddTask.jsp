<%@page import="bean.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="layui-v2.4.3/layui/css/layui.css" media="screen">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"
	rel="stylesheet" media="all">
<link rel="stylesheet" type="text/css" href="css/AddTask.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/AddTask.js"></script>


<script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>


<title>SQLgogo自动评测</title>


</head>

<body onload="nav()">
	<jsp:include page="/teacher/menu.jsp"></jsp:include>
	<div class="container-fluid"
		style="position: absolute;top: 160px;left: 350px">
		<div id="contain" style="display:flex; flex-direction: column;">
			<p style="font-size: 30px;font-weight: bold;">提问</p>

			<div>
				<p>1.问题名称：</p>
				<input class="form-control qes_name" aria-describedby="basic-addon1"
					style="width: 300px;" id="qes_name" type="text" placeholder="问题名字">
			</div>

			<div id="prac_protray"
				style="display:flex;flex-dirction:row; margin-top:20px;">
				<div>
					<p>2.问题描述</p>
					<div id="editor">
						<p>给学生的问题内容编辑</p>
					</div>
					<script type="text/javascript" src="js/wangEditor.min.js"></script>
					<script type="text/javascript">
						var E = window.wangEditor
						var editor = new E('#editor')
						editor.create()
					</script>
				</div>
			</div>

			<div id="prac_class" style="margin-top:20px;">
				<button type="button" id="submit" class="btn btn-default"
					style="margin-left: 640px;background-color: #26c6da;color: white;"onclick="submit()">提交</button>
			</div>
		</div>
	</div>
</body>
</html>