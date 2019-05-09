
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/teacher/css/bootstrap.min.css">
    <title>SQLgogo自动评测</title>

	<script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">

	

         <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              
          }
            body{
          background-color:#eef5f9;
          color:#545454;
          }
    </style>

  </head>
 <body onload="nav()">

  <jsp:include page="/teacher/menu.jsp"></jsp:include>

<br/>
 <div class="container-fluid" style="position: absolute;top: 90px;left: 300px" >
	<div align="center" style="position: absolute;top: 71px;left: 160px;width:800px;">
		<font   style="font-size: 27px;color:#545454;">环境准备</font>
	
	
	<div class="layui-form-item layui-form-text" style="margin-top: 9px;">
		    <label class="layui-form-label" style="width:102px;">建库</label>
		    <div class="layui-input-block">
<!--  		    	<textarea placeholder="请输入库名，非代码" class="layui-textarea" name="dataname" id="dataname"></textarea>
 -->				<input type="text" name="dataname" id="dataname" lay-verify="title" autocomplete="off" placeholder="请输入库名，非代码" class="layui-input">
			</div>
  	</div>

  	<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label" style="width:102px;">建表</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入建表代码" class="layui-textarea" name="mytextarea" id="mytextarea"></textarea>
    		</div>
  	</div>

	<button id="mybutton" class="btn btn-info" style=" margin-bottom: 12px;width: 121px;" type="button" onclick="return assure()">执行</button>

	
  		<br>
  		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label" style="width:102px;">建库结果</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="建库结果" class="layui-textarea" name="dbresult" id="dbresult"></textarea>
    		</div>
  		</div>

		<div class="layui-form-item">
		    <label class="layui-form-label" style="width:102px;">建表结果</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="建表结果" class="layui-textarea" name="tableresult" id="tableresult"></textarea>
		    
<!-- 		      <input type="text" name="tableresult" id="tableresult" lay-verify="title" autocomplete="off" placeholder="建表结果" class="layui-input">
 -->		    </div>
  		</div>
	</div>
</div>

  </body>
</html>
<script>
	function assure(){
		var dataname = $('#dataname').val();
		var mytextarea = $('#mytextarea').val();
		if(dataname == "" || mytextarea == ""){
			layer.msg("建库建表语句不能为空！");
		}else{
		$.ajax({
			  url:"../ReadyServlet",
			  type:"post",
			  dataType:"json",
			  data:{
			  	"dataname":dataname,
			  	"mytextarea":mytextarea
			  },
			  async:true,
			  success:function(result){
			  	  layer.msg(result.result);
				  $('#dbresult').val(result.dbresult);
				  $('#tableresult').val(result.tableresult);
			  },
			  error:function(err){
				  layer.msg("系统繁忙，请稍后再试！");
			  }
		  });
		}
	}
</script>
