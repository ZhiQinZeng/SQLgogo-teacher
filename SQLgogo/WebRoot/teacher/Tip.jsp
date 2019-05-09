
<%@ page language="java" import="javax.servlet.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
  <head>
     <link rel="stylesheet" type="text/css" href="css/Tip.css">
   
  </head>
  <body>
     <div style=" display: inline-block;
    position: fixed;
    top: 26%;
    left: 86%;
    width: 150px;
    height: 600px;">
       <ul class="tip">
           <a href="AddTask.jsp"> <li style="list-style: none;">提问</li></a>
            <a href="main.jsp"><li style="list-style: none; ">热门问题</li></a>
          <a href="NewQes.jsp">  <li style="list-style: none; ">最新问题</li></a>
          <a href="TeaFocus.jsp">  <li style="list-style: none;">我关注的问题</li></a>
          <a href="Answer.jsp">  <li style="list-style: none;">我回答的问题</li></a>
           <a href="TeacherQes.jsp"> <li style="list-style: none;">我的提问</li></a>
        </ul>
    </div>
    
  </body>
</html>