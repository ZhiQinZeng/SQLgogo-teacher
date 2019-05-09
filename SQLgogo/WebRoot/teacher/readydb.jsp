
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

	<script type="text/javascript">

        function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }

   </script>
  </head>
 <body onload="nav()">

  <jsp:include page="/teacher/menu.jsp"></jsp:include>

<br/>
 <div class="container-fluid" style="position: absolute;top: 90px;left: 300px" >
	<div align="center" style="position: absolute;top: 30px;left: 160px;width:800px;">
		<font size="5">环境准备</font>
		<hr>
	<!-- <form method="post" action="../ReadyServlet">
		建库：<input type="text" name="dataname"><br>
	           建表：<textarea name="mytextarea" rows="10" cols="80"></textarea>

		<input type="submit" value="确定" />
	</form> -->
	<!-- <div>
		 <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
	</div> -->
	
	
	<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">建库:</label>
		    <div class="layui-input-block">
<!--  		    	<textarea placeholder="请输入库名，非代码" class="layui-textarea" name="dataname" id="dataname"></textarea>
 -->				<input type="text" name="dataname" id="dataname" lay-verify="title" autocomplete="off" placeholder="请输入库名，非代码" class="layui-input">
			</div>
  	</div>

  	<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">建表:</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="请输入建表代码" class="layui-textarea" name="mytextarea" id="mytextarea"></textarea>
    		</div>
  	</div>

	<button id="mybutton" class="btn btn-info" type="button" onclick="return assure()">确定</button>

	
  		<br>
  		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">建库结果:</label>
		    <div class="layui-input-block">
		    	<textarea placeholder="建库结果" class="layui-textarea" name="dbresult" id="dbresult"></textarea>
    		</div>
  		</div>

		<div class="layui-form-item">
		    <label class="layui-form-label">建表结果:</label>
		    <div class="layui-input-block">
		      <input type="text" name="tableresult" id="tableresult" lay-verify="title" autocomplete="off" placeholder="建表结果" class="layui-input">
		    </div>
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
