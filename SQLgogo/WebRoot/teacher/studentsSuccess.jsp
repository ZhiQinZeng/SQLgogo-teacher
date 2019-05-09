<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'studentsSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">	
		var time = 4; 
		function returnUrlByTime() { 
				window.setTimeout('returnUrlByTime()', 1000); 	
				time = time - 1; 	
				document.getElementById("layer").innerHTML = time;
			}
    </script>



  </head>
  
  <body onload="returnUrlByTime()">
   
   <% if((Boolean)session.getAttribute("errorvalue")) %>
    	<h3>上传成功！学号相同的记录会自动去除。</h3>
	<b><span id="layer">3</span>秒后，回到提交页面。</b>
	
	<%
		//转向语句
		response.setHeader("Refresh", "3;URL=studentsAdmin.jsp");
	%>
  </body>
</html>
