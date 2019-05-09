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
    
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <title>SQLgogo自动评测</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
          
          body{
          background-color:#eef5f9;
          }
          
    </style>
	
  </head>
  
  <body onload="nav()">
  
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  
  
<br/>
<div class="container-fluid" style="position: absolute;top: 90px;left: 300px" >
	
	<div align="center" style="position: absolute;top: 90px;left: 160px;width:800px;">
		<font size="5">新增题库</font>
		<hr>
	
	
		 <div class="layui-form-item">
		    <label class="layui-form-label">名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="quename" id="quename" lay-verify="title" autocomplete="off" placeholder="请输入题目标题" class="layui-input">
		    </div>
  		</div> 
			
 		
		 <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">内容</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入题目内容" class="layui-textarea" name="quecontext" id="quecontext"></textarea>
    		</div>
  		</div>
  		<a id="fanhui" href="#" onclick="javascript :history.back(-1);"><button class="layui-btn" type="button" >返回</button></a>
  		<button class="layui-btn"  onclick="return confirm()">确定</button>
		<!-- <input type="submit" value="确定"> -->
	
	</div>
</div>

  </body>
</html>
<script>
function confirm(){
	var quename = $('#quename').val();
	var quecontext = $('#quecontext').val();
	if(quename == "" || quecontext == ""){
		layer.msg("不能有空！");
		return false;
	}else{
		$.ajax({
		  url:"../QueManager?method=addQue",
		  type:"post",
		  data:{
		  	"quename":quename,
		  	"quecontext":quecontext
		  },
		  dataType:"json",
		  async:true,
		  success:function(result){
		  	layer.msg("添加成功！");
		  },
		  error:function(err){
			  alert("系统繁忙，请稍后再试！");
		  }
	  });
	}
}
</script>