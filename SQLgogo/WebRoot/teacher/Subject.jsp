<%@page import="bean.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css"
	href="layui-v2.4.3/layui/css/layui.css" media="screen">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Subject.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/Subject.css">
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <title>SQLgogo自动评测</title>
   <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              
          }
          #left-menu ul li{

          }
    </style>
  </head>
  
  <body onload="nav(),loadData();">
<jsp:include page="/teacher/menu.jsp"></jsp:include>
<div class="container-fluid" style="position: absolute;top: 160px;left: 290px" >
      <div id="contain" style="    display: flex;flex-direction: column; width: 1100px;justify-content: center;align-items: center;">
          <div id="topic">
          <p style="font-size:40px;"></p>
             <div style="position: relative;left: 87%;">
                  <span>使用库:<span id="choice_db" style="color:#fc9903de"></span>&nbsp&nbsp过程分占比:</span><span id="process_portion" style="color:#fc9903de"></span>
  				  <span>&nbsp结果分占比:</span><span id="result_portion" style="color:#fc9903de"></span>
  			 </div>
          </div>
            
          <div id="status">
            <div class="panel panel-default">
  				<!-- Default panel contents -->
  				<div class="panel-heading" style="display:flex; justify-content:space-between;width:842px;">
  				  <p id="expired" style="margin-bottom:0px;padding-top: 8px;opacity: 0;   color: #ff2222;font-size: 15px;font-weight: bold;">这个练习好像已经结束了嚯</p>
  				  <div>
  				   <button type="button" class="btn btn-default" id="pick_sub" ><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">从题库中选择</font></font></button>
  				  <button type="button" class="btn btn-default" id="add" ><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">自定义问题</font></font></button>
  				  <button type="button" class="btn btn-default" id="all_deleted" onclick="all_deleted()"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">全部清空</font></font></button>
  				  <button type="button" class="btn btn-default" ><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">答题统计</font></font></button>
  				  </div>
  				</div>

 				 <table class="table" id="table">
    			   <tr>
    			     <td style="padding-left:20px;">序号</td>
    			     <td>试题名称</td>
    			     <td style="padding-left:2px;">过程分</td>
    			     <td style="padding-left:8px;padding-right:0px;">结果分</td>
    			     <td style="padding-left:0px;">运行总分</td>
    			    
    			      <td style="padding-left: 30px;">操作</td>
    			   </tr>
  			     </table>
</div>
          
          
          </div>
          
        
      </div>
 </div>

  </body>
</html>

