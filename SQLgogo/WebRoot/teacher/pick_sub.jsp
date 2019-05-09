<%@page import="bean.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html lang="en">
  <head>
    <meta charset="UTF-8">
   
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" rel="stylesheet" >
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    
    
    
    <link rel="stylesheet" type="text/css" href="css/pick_sub.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/pick_sub.js"></script>
    
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
   
    <title>SQLgogo自动评测</title>
    <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              margin-top:2px;
          }
          #left-menu ul li{
              margin-bottom:4px;
          }
    </style>
  </head>
  <body onload="load_choice(),nav(),loadData();">
<jsp:include page="/teacher/menu.jsp"></jsp:include>
<div class="container-fluid" style="position: absolute;top: 160px;left: 330px;width: 55%;" >
       <div id="contain" style="display:flex; flex-direction: column;" >
          <p style="font-size: 30px;font-weight: bold;">题库选择</p>
          	
 		<div class="panel panel-default">
         <!-- Default panel contents -->
       <div class="panel-heading" style="padding-left: 30px;font-size: 20px;">题库List</div>

      <!-- Table -->
       <table class="table" id="table">
       <tbody><tr>
         <td>序号</td>
	         <td>题库名称</td>
	         <td>题库描述</td>
	         <td>发布时间</td>
	         <td>操作</td>
       </tr>
       </tbody>
     </table>
     </div>
       
     
     <div>
 </div>
 <jsp:include page="/teacher/qes_choiced.jsp"></jsp:include>
  </body>
</html>