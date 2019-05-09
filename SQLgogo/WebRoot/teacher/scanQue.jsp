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
    <script type="text/javascript" src="js/scanQue.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="jquerypage/js/zxf_page.js"></script>
    
    
    <link rel="stylesheet" type="text/css" href="css/scanQue.css">
    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="jquerypage/css/zxf_page.css"/>
    <title>SQLgogo自动评测</title>

	<style type="text/css">
          #left-menu ul{
              margin-left: 40px;
          }
             body{
          background-color:#eef5f9;
          }
    </style>
  </head>
  
  <body onload="nav(),loadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  

<br/>
<div class="container-fluid" style="position: absolute;top: 170px;left: 370px;
    width: 70%;" >
    
   <nav class="navbar navbar-default"style="display:  flex;align-items:  center;background-color: #26da66;">
    <div class="container-fluid">
     <div class="navbar-header">	
	
    
	<div id="practice" class="prac_fore" >
		<font size="5" style="color:white">题库管理</font>
		
	</div>
	
	 </div>
    </div>
   </nav>
        
<div style="height:381px">
      <table class="table" id="table">
       <thead>
	      <tr>
	         <td >序号</td>
	         <td>题库名称</td>
	         <td>题库内容</td>
	         <td>发布时间</td>
	         
	         <td style="padding-left: 55px;">操作</td>
	      </tr>
       </thead>
       <tbody id="mytbody">
       </tbody>
     </table>
     </div>
      <!-- 分页 -->
	<div class="zxf_pagediv" ></div>

	<input type="hidden" id="mytotalPage">
	
	 <div style="" class="prac_names" id="prac_names">
			
			<!-- <form action="../UploadQue" method="post" enctype="multipart/form-data">
		
        
        <div class="btn btn-success fileinput-button" id="choice_file">
            <span>选择文件</span>
            <input type="file" id="fileId" style="  opacity: 1;
    												height: 50px;
    												right: 0;
    												top: 0;
    												position: absolute;">
        </div>					
			<button type="submit" style="float:left;background-color:#1e88e5;outline:none;border-color:#1e88e5;margin-right: 15px;" class="btn btn-primary fileinput-button"  id="test3">上传题目</button>
					
			</form> -->
			<a href="./addQue.jsp"><button type="button" id="new_sub" class="btn btn-success fileinput-button" style="background-color:#ffb22b;outline:none;border-color:#ffb22b">新建题库</button></a>
			
		</div>
 </div>
 
  </body>
</html>
<script>
/* layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  //指定允许上传的文件类型
  upload.render({
    elem: '#test3'
    ,url: '../UploadQue'
    ,accept: 'file'
    ,async:true
   	,multiple: true
   	,exts: 'xls' //允许上传的文件后缀
    ,done: function(res){
   		
   		alert(res.code);
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
 }); */
</script>