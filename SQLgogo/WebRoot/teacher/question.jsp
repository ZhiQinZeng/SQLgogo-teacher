<%@page import="bean.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <meta charset="UTF-8">
    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/question.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="css/question.css">
       <link rel="stylesheet" type="text/css"
	href="layui-v2.4.3/layui/css/layui.css" media="screen">
      <script type="text/javascript" src="js/wangEditor.min.js"></script>
     
       <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js" ></script>
    <title>SQLgogo自动评测</title>
 
  </head>
  
  <body onload="nav(),loadData();" style=" background-color:#eef5f9;">
<jsp:include page="/teacher/menu.jsp"></jsp:include>
<div class="container-fluid" style="position: absolute;top: 180px;left: 350px" >
  <div id="qes" class="qes">
   <div class="qes_part">
   
      <div class="panel panel-default" style="width:auto;">
		<div class="panel-body" style="box-shadow: 0px 0px 5px #88888861;">
			 <div class="qes_demo">
      <div id="user" class="user">
        <div id="user_pic" class="user_pic">
        <a href="#" >
          <img class="img-circle" id="qes_photo" src="" style="width:80px; height: 80px;">
        </a>
        </div>
        <p class="user_name"></p>
      
      </div>

      <div id="user_qes" class="user_qes">
        <div class="qes_name"></div>
        <div class="qes_time"></div>
        <div class="qes_state">关注量：<span id="mentions"></span>
        </div>
      </div>
      <div id="user_qes_protray" class="user_qes_protray">
       <p style="font-size:25px;weight:bold;margin-left:20px;margin-top:1px;">问题描述</p>
         <p style="width:400px;font-size:15px;word-wrap:break-word;word-break:break-all;
    overflow: hidden;margin-left:20px;" id="qes_protray"></p>  
         </div> 
      </div>

			<div id="eval" class="eval">
			 <div id="pro_comment"style="display:flex;flex-direction:row; display:none;">
					<div class="panel panel-info"
						style="background-color: #bce8f1;width:510px;margin-left:200px;display:flex;background-color: #bce8f1;align-items: center;height: 32px;">
						<p style="margin-bottom: 0px;margin-left: 20px;"></p>
					</div>
					 <button type="button" class="btn btn-danger btn-xs" onclick="proto_deleted()"
						style="height: 28px;margin-top: 2px;margin-left: 15px;">删除</button>
				</div>
			</div>






         <div  id="tea_deleted"  style="display:none;flex-direction: row-reverse;"> <button onclick="tea_deleted()" type="button" class="btn btn-danger btn-sm" style="margin-right: 10px;
    width: 62px;" >删除</button>  </div>
			<div style="display:flex;flex-direction:column-reverse;">
				<div
					style="display:flex; flex-direction: row-reverse;margin-top:10px;">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="collapse" data-target="#demo" id="comment" style="background-color:#539cdf;border-color: white;">回复</button>
					<button type="button" class="btn btn-warning btn-xs" id="submit"
						style="position:relative;margin-right: 120px; display:none; ">提交</button>
				</div>

				<div id="demo" class="collapse">
					<div id="div1" class="toolbar"></div>
					<div id="div2" class="text">
						<!--可使用 min-height 实现编辑区域自动增加高度-->
						<p>请输入内容</p>
					</div>
				</div>
			</div>

		</div>
	</div>
 
      </div>

       </div>
      </div>
   



  </body>
</html>
