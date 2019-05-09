
<%@ page language="java" import="javax.servlet.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
  <head>
     <link rel="stylesheet" type="text/css" href="css/qes_choiced.css">
      <script type="text/javascript" src="js/qes_choiced.js"></script>
  </head>
  <body onload="load_choice()">
     <div class="qes_cho">  
        <div class="qes_banner">
          <p>已选题目<p>
        </div>   
        <div class="choiced" id="choiced">
            <div id="jade" style="height: 29px;"></div>
        </div>
        <div class="qes_submit" >
           <button type="button" id="submit" class="btn btn-warning btn-xs" onclick="submit()" style="background-color:#ffb22b;border-color:white;width: 70%;font-size: 14px;display:none;">提交</button>
        </div>
    </div>
    
  </body>
</html>