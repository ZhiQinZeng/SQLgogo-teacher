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
    <script type="text/javascript" src="<%=request.getContextPath() %>/teacher/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="jquerypage/js/zxf_page.js"></script>
    
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <link rel="stylesheet" type="text/css" href="css/perInfo.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquerypage/css/zxf_page.css"/>
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
  
  <body onload="nav(),LoadData()">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  
  <div class="container-fluid" style="position: absolute;top: 50px;left: 300px" >
	
 	<div align="center" style="position: absolute;top: 60px;left: 160px;width:800px;>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
 			 <legend style="position: relative;top: 50px; font-size: 26px;border-bottom: 0px solid white;color:#545454">学生信息查看</legend>
		</fieldset>
		
	
	<input id="stuid" style="display:none;" value=<%=request.getParameter("id") %>>
	
	<div class="message_part" id="message_part" >
         <div class="message_main">
            <div id="head" style="    position: relative;right: 71px;">
               <span style="letter-spacing: 50px;">头像</span>
              <img  class="img-circle" src="" id="preview" style="width:85px; height: 85px;">
              <!--  <div class="btn btn-primary fileinput-button" id="choice_file" style="background-color:#008def;border-color:white" >
            	 <span>上传头像</span>
             <input type="file" id="fileId" style="opacity: 0;left: -7px;position: relative;top: -22px;width: 83px;" onchange="imgPreview(this)">
         	</div>		 -->
            </div>
            <div class="item">
             <span style="letter-spacing: 50px;">昵称</span>
               <input type="text" class="form-control" placeholder="Username" id="name" style="width: 40%;">
            </div>
            <div  class="item">
               <span style="letter-spacing: 50px;" >学号</span>
               <span  id="student_id"></span>
            </div>
            <div  class="item">
              <span style="letter-spacing: 8px;">专业名称</span>
              <span style="margin-left:40px" id="major">行为艺术</span>
            </div>
            <div  class="item">
               <span style="letter-spacing: 8px;">班级名称</span>
               <span  style="margin-left:40px" id="class">16软本二</span>
            </div>
            <div id="sex" class="item" style="margin-top: 25px;">
             <span style="letter-spacing: 50px;">性别</span>
               <input type="radio" name="sex" value="男"><span class="span_sex">男</span>
                <input type="radio" name="sex" style="margin-left:40px" value="女"><span class="span_sex">女</span>
            </div>
            <div  class="item">
                 <span style="letter-spacing: 8px;">邮箱地址</span>
                 <input type="email" class="form-control" placeholder="E-mail" id="email" style="width: 40%;margin-left: 40px;">
            </div>
          </div>
    </div>
    
	
	</div>
</div>

 
  </body>
</html>
<script>
function goback(){
	window.history.go(-1);
}
function LoadData(){
	var stuid = $('#stuid').val();
		$.ajax({
	    	url:"../StudentsServlet?method=lookStu",
	    	type:'post',
	    	dataType:"json",
			data: {
				"stuid": stuid,
			},
	    	async: false,
	    	success:function(data){
	    	console.log(data);
	    		console.log(data)
	    		user = data;
	    		
			     //console.log("user!!!!!!!::::",user)
			     var hostport=document.location.host;
			     $("#preview").attr("src","http://"+hostport+user.profile);
			     $("#name").val(user.realname);   
			     $("#email").val(user.e_mail);  
			     $("#student_id").text(user.username);
			     $("#major").text(user.major);
			     $("#class").text(user.classname);
			     
			     if(user.sex=="男"){
			     	$("input[name='sex']").get(0).checked=true; 
			     }else{
			     	$("input[name='sex']").get(1).checked=true; 
			     }
	    	},
	    	error:function(){
	    		layer.msg("获取信息失败！");
	    	},
	    });

}
	
</script>