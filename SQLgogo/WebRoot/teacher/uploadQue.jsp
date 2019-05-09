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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		a, a:link, a:hover, a:active, a:visited {
            text-decoration: none;
            color: #666;
        }
        a:hover {
            color: #259;
        }
	</style>
	<script type="text/javascript">
        
        function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
        
       
        
   </script>
   
	
  </head>
  
  <body onload="nav()">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
 
<br/>
<div class="container-fluid" style="position: absolute;top: 90px;left: 300px" >
	&lt&lt<a href="/SQLgogo/teacher/main.jsp" style="color:black;text-decoration:underline;">返回 </a>
    <br>
	<div>
	<form action="../UploadQue" method="post" enctype="multipart/form-data">
	
	上传文件 ： <input type="file" name="file" multiple id="fileId" />
	<input type="submit" name="btn" value="提交" id="btnId" />
	</form>
</div>
</div>
 
  </body>
</html>
