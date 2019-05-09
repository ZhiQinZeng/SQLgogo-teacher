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
          #left-menu ul{
              margin-left: 40px;
              
          }
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
		<font size="5" style="margin-bottom: 19px;display: block;color: #535353;">增加老师用户</font>
	
	
		 <div class="layui-form-item">
		    <label class="layui-form-label">编号：</label>
		    <div class="layui-input-block">
		      <input type="text" name="teausername" id="teausername" lay-verify="title" autocomplete="off" placeholder="请输入老师编号（如16111651227）" class="layui-input">
		    </div>
  		</div> 
			
 		
		 <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">密码：</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入默认密码" class="layui-textarea" name="teapassword" id="teapassword"></textarea>
    		</div>
  		</div>
  		
  		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">名称：</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入老师名称" class="layui-textarea" name="teaname" id="teaname"></textarea>
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
	var teausername = $('#teausername').val();
	var teapassword = $('#teapassword').val();
	var teaname = $('#teaname').val();
	if(teausername == "" || teapassword == "" || teaname == ""){
		layer.msg("不能有空！");
		return false;
	}else{
		$.ajax({
		  url:"../StudentsServlet?method=addteacher",
		  type:"post",
		  data:{
		  	"teausername":teausername,
		  	"teapassword":teapassword,
		  	"teaname":teaname
		  },
		  dataType:"json",
		  async:true,
		  success:function(result){
		  console.log(result.res);
		  	if(result.res == '1'){
		  		layer.msg("添加成功！");
		  	}else{
		  		layer.msg("此编号已存在，添加失败！");
		  	}	
		  },
		  error:function(err){
			  alert("系统繁忙，请稍后再试！");
		  }
	  });
	}
}
</script>