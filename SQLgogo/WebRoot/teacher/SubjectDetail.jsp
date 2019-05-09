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
<link rel="stylesheet" type="text/css" href="css/AddSubject.css"
	rel="stylesheet">

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/SubjectDetail.js"></script>

<script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
 <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              
          }
          #left-menu ul li{
             
              
          }
    </style>

<title>SQLgogo自动评测</title>

</head>

<body onload="nav(),loadData();">
	<jsp:include page="/teacher/menu.jsp"></jsp:include>

	<div class="container-fluid"style="position: absolute;top: 160px;left: 310px;width:1100px;">
		<div id="contain" style="    display: flex;
   				 background-color: white;
   				 flex-direction: column;
    			 box-shadow: 0px 0px 5px #8888888a;
    			 border-radius: 3px;">
			 <div style="display: flex;width: 100%;justify-content: center;align-items: center;margin-top: 10px;" id="topic"><p style="font-size: 30px;font-weight: bold;"></p></div>
			<div style="position: relative;flex-direction: column;left: 25px;">
			<div ><p style="font-size: 20px;font-weight: bold;">1.试题描述<p>
			<div id="sub_protray" style="margin-top:10px;margin-left:10px;"><p style="font-size: 15px;width:auto;word-wrap:break-word;word-break:break-all;"></p></div>
			</div>
			
			
			<div  style="margin-top:20px;margin-bottom: 20px;"><p style="font-size: 20px;font-weight: bold;">2.试题答案<p>
			<div  id="sub_answer" style="margin-top:10px;margin-left:10px;"><p style="font-size: 15px;width:auto;word-wrap:break-word;word-break:break-all;"></p></div>
			</div>
			</div>
			
		  </div>
	</div>
</body>
</html>