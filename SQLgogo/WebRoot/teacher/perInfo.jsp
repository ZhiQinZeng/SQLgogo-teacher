

<%@page import="java.util.List"%>
<%@page import="dao.PersonInfoDao"%>
<%@page import="java.math.BigInteger"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.TeaUser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" import="javax.servlet.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    </style>
    <script type="text/javascript">
        $(function () {
            $('#sel option[]').attr('selected',true)
        })
        
        
 </script>
    
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

<%
	UserDao teaDao=new UserDao();
	TeaUser tea=new TeaUser();
	String username=(String)session.getAttribute("username");
	tea = teaDao.SelectByName(username);
 %>


    
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
                    <li><a href="/SQLgogo/teacher/changepwd.jsp">密码修改</a></li>
                    <li><a href="javascript:void(0):" onclick="exit()">退出</a></li>
                </ul>
            </div>

    </div>
</nav>
<div style="position: absolute;left: 400px;top: 130px">个人主页</div>
<div style="border-top: 1px solid #e5e5e5;position: absolute;display: inline-block;left: 400px;top: 150px;width: 740px">
         
         <div style="display: inline-block;position: absolute;top:50px;left: 20px">
                <!-- <img style="width: 150px;height: 150px;border-radius: 50%;" src="upload/教师端页面.PNG" alt="icon">
                <form action="/UploadImage" method="post" enctype="multipart/form-data">
                	<label for="icons">
                		<img style="width: 150px;height: 150px;border-radius: 50%;" src="upload/教师端页面.PNG" alt="icon">
                	</label>
               		 <input type="file"  name="icon" style="opacity: 0" id="icons"  title="选择图片"> 
            	</form>	 -->
     
 	<form action="../uploadServlet" method="post" enctype="multipart/form-data">
     			请选择上传的图片:
     <input type="file" name="fileName"/><input type="submit" value="上传"/>
    </form> 
    <%
    	String username1=(String) request.getSession().getAttribute("username");
    	BigInteger username2 = new BigInteger(username1);
    	PersonInfoDao per = new PersonInfoDao();
    	List<TeaUser> li = per.findImagePath(username2);
    	String filename = (String)li.get(0).getProfile(); 
     %>
    <div style="width:100px;height:100px">
    <img style="width:100px;height:100px" src="../upload/<%=filename %>">
   </div>
   </div>
        
        <form method="post" action="../PersonInfoServlet" >
            
           
            <div style="display: inline-block;position: absolute;left:270px; ">

                <div style="display: inline-block;width: 470px">
                    <br/>
                    <br/>
                    <label>邮箱</label>
                    <br>
                    <input type="text" name="e_mail" value="<%=tea.getE_mail() %>" style="width: 470px;border-style: none;outline: none;border-bottom: 1px solid #A6C8FF; ">
                </div>
                <br/>
                <div style="display: inline-block;width: 470px">
                    <br/>
                    <br/>
                    <label>电话号码</label>
                    <br>
                    <input type="text" value="<%=tea.getPhone_num() %>" name="phone_number" style="width: 470px;border-style: none;outline: none;border-bottom: 1px solid #A6C8FF; ">
                    
                </div>
                <br/>
                <div style="width: 470px;display: inline-block;border-bottom: 1px solid #A6C8FF;">
                    <br/>
                    <br/>
                    <label>性别</label>
                    <br>
                    <input type="text" value="<%=tea.getSex() %>" name="sex" style="width: 470px;border-style: none;outline: none;border-bottom: 1px solid #A6C8FF; ">
                    
                </div>
                <br/>
               
                <div style="display: inline-block;width: 470px">
                    <br/>
                    <br/>
                    <label>专业</label>
                    <br>
                    <input type="text" value="<%=tea.getMajor() %>" name="major" style="width: 470px;border-style: none;outline: none;border-bottom: 1px solid #A6C8FF; ">
                </div>
                
                <br/>
                <div style="display: inline-block;width: 470px">
                    <br/>
                    <br/>
                    <label>就职企业</label>
                    <br>
                    <input type="text" value="<%=tea.getCompany() %>" name="company" style="width: 470px;border-style: none;outline: none;border-bottom: 1px solid #A6C8FF; ">
                </div>
                
                <br/>
                <br/>
          	
                <input type="submit" class="btn-success" value="保存并修改">
                <br/>
                <br/>
                <br/>
            </div>
            
        </form>
    </div>



</body>
</html>