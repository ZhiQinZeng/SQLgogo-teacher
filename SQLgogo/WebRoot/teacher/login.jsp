<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
   
    <title>SQLgogo自动评测系统</title>
     <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
     <link rel="stylesheet" type="text/css" href="css/menu.css">
     <link rel="stylesheet" type="text/css" href="css/login.css">
      <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
       <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

  </head>
  
  <body>
   <div class="header"> 
       <div class="logo">
         <p class="logoer">SQLGOGO</p>
       
       </div>
   </div>    
	
<div class="container-fluid login_main">



 <div id="login_part" class="login_part">
    	<div style="font-size: 20px;margin-top: 10px;">Login</div>
    	<div style="border:0.5px solid #d9d7d74f;width: 415px;height: 0px;margin-top: 10px;"></div>
    	
    	<div id="login_user_name" class="login_user">
  			<input type="text" id="username" name="username" class="form-control" style="width: 300px;"  placeholder="请输入用户名" aria-describedby="basic-addon1">
    	</div>
		  <div id="login_user_pws" class="login_user">
  			<input type="password" id="pwd" name="password" class="form-control" style="width: 300px;" placeholder="请输入密码" aria-describedby="basic-addon1">
    	</div>
     	<button type="submit" class="btn btn-default" style="width: 300px;
         	margin-top: 43px;background-color: #5bc0de;color: white;margin-bottom: 30px" onclick="confirm()">Login Now</button>
    	
    </div>
</div>

  </body>
</html>
