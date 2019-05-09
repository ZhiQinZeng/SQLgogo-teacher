
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
    <script type="text/javascript" src="js/dbmanager.js"></script>
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
              </style>
  </head>
  
  <body onload="nav(),loadData();">
  <jsp:include page="/teacher/menu.jsp"></jsp:include>
  

<br/>
<div class="container-fluid" style="position: absolute;top: 168px;left: 370px;width: 70%;" >
    
   <nav class="navbar navbar-default" style="display:  flex;align-items:  center;background-color: #26da66;">
    <div class="container-fluid">
     <div class="navbar-header">	
	
    
	<div id="practice" class="prac_fore" >
		<font size="5" style="color:white">表结构</font>
		
	</div>
	
	 </div>
    </div>
   </nav>
      
	<input style="display:none;" id="mydbname" value=<%=request.getParameter("dbname") %>>
	<div id="tables" style="width:1032px;height:400px;">
		
	</div>
	</div>
 
  </body>
</html>
<script>
	var dbname=$('#mydbname').val();
	$.ajax({
	    	url:'../DbServlet?status=seldbtables&dbname='+dbname,
	    	type:'get',
	    	async: false,
	    	success:function(res){
					 $('#tables').html("");
					 var res = $.parseJSON(res);// 转成JSON格式
					 if(res == ""){
					 	$('#tables').append("<div>此数据库没有表!</div>");
					 }else{
						for(var i=0;i<res.length;i++){					
							$('#tables').append(`<div style="font-size:15px;"><a href="#" onclick='window.open("./table.jsp?dbname=`+dbname+`&tabname=`+res[i]+`","_blank","resizable=yes,left=600,top=200,width=600,height=400")'>`+i+". "+res[i]+`</a></div>`);
						}	
					}				             
	    	}
	    	
	    });		    
</script>