<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
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
    <script type="text/javascript" src="js/studentsAdmin.js"></script>
	<script type="text/javascript" src="jquerypage/js/zxf_page.js"></script>
    
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <link rel="stylesheet" type="text/css" href="css/classAdmin.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquerypage/css/zxf_page.css"/>
    <title>SQLgogo自动评测</title>
	<style type="text/css">
	table a{
	  color:gray;
	}
	table   a:hover {
	 		color:#009688 !important;
            text-decoration: none;
           
        }
          #left-menu ul{
              margin-left: 40px;
              
          }
    </style>
	
  </head>
  
  <body onload="nav(),LoadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  
  	
</br>


 <div class="container-fluid" style="position: absolute;    top: 170px;left: 321px;width: 79%;" >
    
   <nav class="navbar navbar-default"style="display:  flex;align-items:  center;    margin-bottom: 0px;">
    <div class="container-fluid">
     <div class="navbar-header">	
	
    
	<div id="practice" class="prac_fore" >
		<font size="5">学生管理</font>
		<div  id="prac_names">
			<button type="button" style="position: relative;left: 350px;" class="btn btn-default" onclick="addStudent();"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">添加学生</font></font></button>
		</div>
	</div>
	
	 </div>
    </div>
   </nav>
        <input id="myclassid" style="display:none;" value=<%=request.getParameter("classid") %>>
		<input id="myclassName" style="display:none;" value=<%=request.getParameter("className") %>>
		
	<div style="height:350px">
 	<table class="layui-table" id="table">
       <thead>
	      <tr>
	         <td >序号</td>
	         <td>学生名称</td>
	         <td>班级</td>
	         <td>学号</td>	         
	         <td style="padding-left: 55px;">学生操作</td>
	      </tr>
       </thead>
       <tbody id="mytbody">
       </tbody>
     </table>
     </div>
      <!-- 分页 -->
	<div class="zxf_pagediv" ></div>

	<input type="hidden" id="mytotalPage">
	<div class="file_button">
	<button type="button" style="position: relative;top: -59px;" class="layui-btn" id="test3"><i class="layui-icon"></i>上传Excel</button>
	<span ><a href="<%=basePath%>/template/excel.xls" >模板下载！！</a>
	（请严格按照模板上传!.xls扩展名，第一行标题，姓名，学号！学号相同的学生会被自动删除）</span>
</div>
</div>
 <script>
function addStudent(){
window.location.href="./addStudent.jsp?"+document.URL.split("?")[1];
		
}

var classid = $('#myclassid').val();
var className = $('#myclassName').val();
layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  //指定允许上传的文件类型
  upload.render({
    elem: '#test3'
    ,url: '../StudentsUpload?classid='+classid+'&className='+className
    ,accept: 'file'
    ,async:true
   	,multiple: true
   	,exts: 'xls' //允许上传的文件后缀
    ,done: function(res){
   		
      if(res.code != ""){
          layer.msg('导入成功！', {
            }, function(){
               location.reload(); 
            });
      }else{
          layer.msg('导入失败！', {
            }, function(){
               location.reload(); 
            });
      }
    }
    ,error: function(){
     
          layer.msg('导入失败！', {
            }, function(){
              location.reload();
            });
     
    }
    
  });
 });

</script>
  </body>
  
  
</html>
