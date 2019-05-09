<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title>SQLgogo自动评测</title>
    <style type="text/css">
        a, a:link, a:hover, a:active, a:visited {
            text-decoration: none;
            color: #666;
        }
        a:hover {
            color: #259;
        }
    .testDIV{
        width: 520px;
        text-overflow:ellipsis;
        white-space:nowrap;
        overflow:hidden;
        }

    </style>
    
    <script>
   		function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
   </script>
   
   
</head>
<body>
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">

            <div class="navbar-header">
                <button class="navbar-toggle" data-toggle="collapse" data-target="#togglemenu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">SQLgogo Judge</a>

            </div>
            <div class="collapse navbar-collapse" id="togglemenu">
                <ul class="nav navbar-nav navbar-right"><!--定义导航中的菜单-->
                    <li class="active"><a href="/SQLgogo/teacher/main.jsp">欢迎您，<%=session.getAttribute("realname")%></a></li>
                    <li><a href="/SQLgogo/teacher/personal.jsp">个人主页</a></li>
                    <li><a href="/SQLgogo/teacher/help.jsp">帮助</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">密码修改</a></li>
                    <li><a href="javascript:void(0):" onclick="exit()">退出</a></li>
                </ul>
            </div>

    </div>
</nav>
<div>
	<div align="center">
	<font size="4">修改密码</font><br>
	</div>
	<hr>
	<br>
	
	<div margin:20dp >
    <form  action ="../PwdChange" method="post">
    	<span>请输入原密码:</span> <br>  
    	<input type = "password"  name ="oldpwd" >
    	<br/><br>
    	<span>请输入新密码:</span> <br>  
    	<input type = "password"  name ="newpwd" >
    	<br/><br>
    	<span>请再次输入新密码:</span><br>
    	<input type = "password"  name ="confirm" ><br><br>
    	<input  type = "submit" value = "确认" >
    	</form>
    </div>
  </div>
  </body>
</html>