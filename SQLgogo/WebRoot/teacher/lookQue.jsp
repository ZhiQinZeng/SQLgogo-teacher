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
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/lookQue.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.js" ></script>
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
<div class="container-fluid" style="position: absolute;    top: 165px;left: 370px;
    width: 70%;" >
    
   <nav class="navbar navbar-default"style="display:  flex;align-items:  center;background-color: #26da66;">
    <div class="container-fluid">
     <div class="navbar-header">	
	
    <input id="lookid" style="display:none" value=<%=request.getParameter("lookid") %> >
	
	<div id="practice" class="prac_fore" >
		<font size="5" style="color:white">题库题目查看</font>
		
	</div>
	
	 </div>
    </div>
   </nav>
        
<div style="height:381px">
      <table class="table" id="table">
       <thead>
	      <tr>
	         <td >序号</td>
	         <td>问题名称</td>
	         <td>问题描述</td>
	         <td>问题答案</td>
	         <td>问题分数</td>
	         <td>得分关键字</td>
	         <td style="padding-left: 55px;">操作</td>
	      </tr>
       </thead>
       <tbody id="mytbody">
       </tbody>
     </table>
     </div>
      <!-- 分页 -->
	<div class="zxf_pagediv" style="z-index:10"></div>

	<input type="hidden" id="mytotalPage">
	
	 <div  class="prac_names" id="prac_names" style="top:94%;    align-items: center;">
			
	<span style="position: relative;left: 154px;color:gray;">
	<a href="<%=basePath%>/template/question.txt" >txt模板下载！！</a>
	(txt格式，名称，描述，答案，得分关键字，设置的分数，一行一个属性，具体参见模板！)</span>
	<button type="button" class="layui-btn" id="test1" style=" top: -48px;position: relative;">
	  <i class="layui-icon">&#xe67c;</i>上传txt
	</button>
			
	</div>
 </div>
 
  </body>
</html>
<script>
var lookid = $('#lookid').val();
layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  //指定允许上传的文件类型
  upload.render({
    elem: '#test1'
    ,url: '../UploadQue?id='+lookid
    ,accept: 'file'   //指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
    ,async:true
   	,multiple: true
  
   	,exts: 'txt' //允许上传的文件后缀
    ,done: function(result){
   	
      if(result.res == 1){
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