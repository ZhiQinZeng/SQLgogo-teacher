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
    <script type="text/javascript" src="js/resultlook.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.js" ></script>
    <script type="text/javascript" src="jquerypage/js/zxf_page.js"></script>
    
    
    <link rel="stylesheet" type="text/css" href="css/scanQue.css">
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquerypage/css/zxf_page.css"/>
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
  
  <body onload="nav(),loadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  

<br/>
<div class="container-fluid" style="position: absolute;top: 170px;left: 370px;
    width: 70%;" >
    
   <nav class="navbar navbar-default"style="display:  flex;align-items:  center;background-color: #26da66;">
    <div class="container-fluid">
     <div class="navbar-header">
     	
	<input id="praid" style="display:none;" value=<%=request.getParameter("praid") %>>
    <input id="classid" style="display:none;" value=<%=request.getParameter("classid") %>>
	<div id="practice" class="prac_fore" >
		<font size="5" style="color:white">结果详情</font>
		
	</div>
	
	 </div>
    </div>
   </nav>
      
      
    <!-- Table -->
     <div style="height: 376px;">
     <table class="table" id="table">
       <thead>
	      <tr>
	          <td>试题名称</td>
	          <td>过程分</td>
	          <td>结果分</td>
	          <td>运行总分</td>
	          
	          <td style="padding-left: 55px;">操作</td>
	      </tr>
       </thead>
       <tbody id="mytbody">
       </tbody>
     </table>
       </div>
      <!-- 分页 -->
	<div class="zxf_pagediv" ></div>

	<input type="hidden" id="mytotalPage">
	
 
  </body>
</html>
<script>

</script>