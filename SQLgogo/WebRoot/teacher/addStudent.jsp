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
    <title>SQLgogo自动评测</title>
    <script src="<%=request.getContextPath() %>/teacher/js/regulate_addStudent.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/teacher/layui-v2.4.3/layui/layui.all.js"></script>
    <script src="<%=request.getContextPath() %>/teacher/layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css"href="<%=request.getContextPath() %>/teacher/layui-v2.4.3/layui/css/layui.css" media="screen">
	  <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              
          }
            body{
          background-color:#eef5f9;
          }
    </style>
	
	
  </head>
  
  <body onload="nav(),LoadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  
  <div class="container-fluid" style="position: absolute;top: 50px;left: 300px" >
	
 	<div align="center" style="position: absolute;top: 113px;left: 160px;width:800px;>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
 			 <legend style="    border-bottom: 0px solid #e5e5e5;">新添学生信息</legend>
		</fieldset>
		<hr>	
	<input id="stuid" style="display:none;" value=<%=request.getParameter("id") %>>
	<input id="realname" style="display:none;" value=<%=request.getParameter("realname") %>>
	 <input id="classname" style="display:none;" value=<%=request.getParameter("classname") %>>
	 <input id="username" style="display:none;" value=<%=request.getParameter("username") %>>
	 
		 <div class="layui-form-item">
		    <label class="layui-form-label" style="width:102px;">学生名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="studentname" id="studentname" lay-verify="title" autocomplete="off"  class="layui-input">
		    </div>
  		</div> 
		
  		
  		<div class="layui-form-item">
		    <label class="layui-form-label" style="width:102px;">学生班级</label>
	  		<div class="layui-input-inline">
			  	<button class="btn btn-default dropdown-toggle" style="    position: relative;right: 31px;" id='class_choice'  type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择班级
            <span class="caret"></span>
           </button>
           <ul class="dropdown-menu" aria-labelledby="dropdownMenu" id="select">
           	 
             </ul>
			</div>
		</div>
		<!-- 班级人数：<input type="text" name="studentsnum" id="studentsnum"><br><br> -->
		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label" style="width:102px;">学号</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入学生学号" class="layui-textarea" name="studentsnum" id="studentsnum" ></textarea>
    		</div>
  		</div>
  		<a id="fanhui" href="#" onclick="javascript :history.back(-1);"><button class="btn btn-info" type="button" >返回</button></a>
		<button id="mybutton" class="btn btn-info" type="submit" onclick="return assure()">确定</button>
	
	
	</div>
</div>

 
  </body>
</html>
