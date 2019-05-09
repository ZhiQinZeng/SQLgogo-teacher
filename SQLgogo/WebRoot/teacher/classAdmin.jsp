<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/teacher/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/classAdmin.js"></script>
	<script type="text/javascript" src="jquerypage/js/zxf_page.js"></script>
    
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <link rel="stylesheet" type="text/css" href="css/classAdmin.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquerypage/css/zxf_page.css"/>
    <title>SQLgogo自动评测</title>
	
	<style type="text/css">
          table a{
	  color:gray;
	}
	  table a:hover {
	 		color:#009688 !important;
            text-decoration: none;
           
        }
          #left-menu ul{
              margin-left: 40px;
              
          }
            body{
          background-color:#eef5f9;
          color:#545454;
          }
    </style>
    
	
  </head>
  
  <body onload="nav(),loadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
 
<br/>
<!-- <div class="container-fluid" style="position: absolute;top: 90px;left: 300px" > -->
<div class="container-fluid" style="position: absolute;    top: 170px;left: 321px;width: 79%;" >
    
   <nav class="navbar navbar-default"style="display:  flex;align-items:  center;">
    <div class="container-fluid">
     <div class="navbar-header">	
	
    
	<div id="practice" class="prac_fore" >
		<font size="5">班级管理</font>
		<div  id="prac_names">
			<a href="./addClass.jsp"><button type="button" style="position: relative;left: 350px;" class="btn btn-default" id="addPractice"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">创建班级</font></font></button></a>
		</div>
	</div>
	
	 </div>
    </div>
   </nav>
      <div style="height:350px">
	 <table class="layui-table" id="table">
       <thead>
	      <tr>
	         <td >班级名称</td>
	         <td>教师</td>
	         <td>班级人数</td>
	         <td>添加日期</td>
	         
	         <td style="padding-left: 55px;">班级操作</td>
	      </tr>
       </thead>
       <tbody id="mytbody">
       </tbody>
     </table>
 </div>  
	<div class="zxf_pagediv"  style="position: relative;
    top: 27px;"   ></div>

	<input type="hidden" id="mytotalPage">
 </div>
 
  </body>
</html>
