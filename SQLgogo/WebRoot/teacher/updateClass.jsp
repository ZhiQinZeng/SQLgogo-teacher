
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <title>SQLgogo自动评测</title>
	
    <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              
          }
            body{
          background-color:#eef5f9;
          }
    </style>
  </head>
  
  <body onload="nav()">
  
  <jsp:include page="/teacher/menu.jsp"></jsp:include>

<br/>
<div class="container-fluid" style="position: absolute;top: 170px;left: 300px" >
	
	<div align="center" style="position: absolute;left: 160px;width:800px;">
		<font size="5">班级信息更改</font>
		<hr>
	
	
	<form method="post" action="../ClassManager?method=updateClass">
	
		<input style="display:none;" name="upid" value=<%=request.getParameter("upid") %> >
	
		<!-- 班级名称：<input type="text" name="classname" id="classname"><br><br> -->
		 <div class="layui-form-item">
		    <label class="layui-form-label" style="width:102px;">班级名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="classname" id="classname" lay-verify="title" autocomplete="off" value=<%=request.getParameter("className") %> class="layui-input">
		    </div>
  		</div> 
		<!-- 教师：&nbsp&nbsp<input type="text" name="teacher" id="teacher"><br><br> -->
		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label" style="width:102px;">教师</label>
		    <div class="layui-input-block">
		    	<textarea  class="layui-textarea" name="teacher" disabled="disabled" id="teacher"><%=session.getAttribute("realname") %></textarea>
    		</div>
  		</div>
		<!-- 班级人数：<input type="text" name="studentsnum" id="studentsnum"><br><br> -->
		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label" style="width:102px;">班级人数</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入班级人数" class="layui-textarea" name="studentsnum" id="studentsnum"><%=request.getParameter("stuNum") %></textarea>
    		</div>
  		</div>
  		<a href="/SQLgogo/teacher/classAdmin.jsp"><button class="btn btn-info" type="button" >返回</button></a>
		<button id="mybutton" class="btn btn-info" type="submit" onclick="return assure()">确定</button>
	</form>
	</div>
</div>




  </body>
</html>


<script>
	  function assure(){
	  	console.log($('#classname').val());
	  	var classname = $('#classname').val();
	  	var teacher = $('#teacher').val();
	  	var studentsnum = $('#studentsnum').val();
	  	if(classname=="" || teacher=="" || studentsnum==""){
	  		layer.msg("都不能为空，请检查！");
	  		return false;
	  	}else{
	  		return true;
	  	}
	  }       
         
</script>