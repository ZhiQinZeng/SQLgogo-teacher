<%@ page language="java" import="javax.servlet.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/NewQes.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
     
     <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" >
     <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="css/NewQes.css">
    <title>SQLgogo自动评测</title>
</head>
<body onload="nav(),loadData()">
 <jsp:include page="/teacher/menu.jsp"></jsp:include>
<div class="container-fluid" style="position: absolute;top: 160px;left:350px" >
    <strong style="font-size:30px;">最新问题</strong>
    <br/>
<div id="containers" >
  <div id="qes" class="qes">
   <div class="qes_part" id="qes_part">
    

   </div>
  </div>
        

    </div>
    <div class="bottom_part">
     <div class="bottom_nav">
                <ul class="pager more">
                <li><a href="#" id="pre" style="color: #26c6da ;">上一页</a></li>
                <li style="width:30px;opacity: 0;"><span>中间</span></li>
                <li><a href="#" id="next" style="color: #26c6da ;">下一页</a></li>
               </ul>      
       </div>
       <div class="skip">
       <input class="form-control" aria-describedby="basic-addon1" style="width: 40px;" id="page" type="text" placeholder="">
       <div  class="btn btn-default go_skip" style="background-color: #2b9cff;color: white;" onclick="skip()">Go!</div>
      <div style="position: relative;left: 20px;color: gray;top: 7px;"><span>共</span><span id="page_count"></span><span>页</span></div> 
      </div>
    </div>
    <br/>
    
    <jsp:include page="/teacher/Tip.jsp"></jsp:include>
</div>

</body>
</html>