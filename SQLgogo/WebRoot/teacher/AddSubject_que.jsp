
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
<script type="text/javascript" src="js/AddSubject_que.js"></script>

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

<body onload="nav()">
	<jsp:include page="/teacher/menu.jsp"></jsp:include>
	<input id="subid" style="display:none" value=<%=request.getParameter("addid") %>>
	<div class="container-fluid"
		style="position: absolute;top: 160px;left: 350px">
		<div id="contain" style="display:flex; flex-direction: column;">
			<p style="font-size: 30px;font-weight: bold;">问题添加</p>

			<div>
				<p>1.问题名称：</p>
				<input class="form-control" aria-describedby="basic-addon1"
					style="width: 300px;" id="sub_name" type="text" placeholder="试题名称">
			</div>

			<div id="prac_protray"
				style="display:flex;flex-dirction:row; margin-top:20px;">
				<div>
					<p>2.问题描述</p>
					<textarea id="textarea" class="form-control" rows="3" cols=""
						onfocus="textFocus()"
						style="color: #808080e3;width: 500px;height: 164px;" placeholder="描述一下你要发布的试题"></textarea>
				</div>
			</div>


			<div id="prac_answer" style="margin-top:20px;display:flex;">
			<div>
				<p>3.问题的答案：</p>
				<textarea id="textarea2" class="form-control" rows="3" cols=""
					onfocus="textFocus2()"
					style="color: #808080e3;height: 164px;width:500px;"  placeholder="请输入试题答案"></textarea>
			</div>		
			   <div style="margin-left:15px;margin-top:5px;" class="score_key">
			   <div>
			     <p>请选择得分关键字</p>
			    <div class="keys">
			    <input type="checkbox" name="like" value="select"  checked/>select
				<input type="checkbox" name="like" value="from"/>from
				<input type="checkbox" name="like" value="where"/>where
				<input type="checkbox" name="like" value="and"/>and
				<input type="checkbox" name="like" value="join"/>join
				</div>
			   </div>
			 <div style="margin-top: 78px;">
			   <p style="margin-bottom:4px;">请输入自定义问题分数</p>
			       <input class="form-control" aria-describedby="basic-addon1" style="width: 60px;" id="self_score" type="text" placeholder="">
			   </div>
			</div>
			   </div>
			   
			 

			<div id="prac_class" style="margin-top:20px;">
				<a id="fanhui" href="#" onclick="javascript :history.back(-1);"><button type="button" id="submit" class="btn btn-default"
					style="margin-left: 450px;background-color: #1e88e5;color: white;"onclick="submit()">返回</button></a>
				<button type="button" id="submit" class="btn btn-default"
					style="margin-left: 20px;background-color: #1e88e5;color: white;"onclick="submit()">提交</button>
				
					
			</div>
		  </div>
		</div>
</body>
</html>