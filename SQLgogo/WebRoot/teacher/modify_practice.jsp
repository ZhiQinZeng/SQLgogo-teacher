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
   
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" rel="stylesheet" >

    <link rel="stylesheet" type="text/css"href="layui-v2.4.3/layui/css/layui.css" >
    
    <link rel="stylesheet" type="text/css" href="css/AddPractice.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/AddPractice.js"></script>
<script type="text/javascript" src="js/modify_Practice.js"></script>
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    
   
   
    <title>SQLgogo自动评测</title>
    <style type="text/css">
          #left-menu ul{
              margin-left: 40px;
              margin-top:2px;
          }
          #left-menu ul li{
              margin-bottom:4px;
          }
    </style>
  </head>
  <body onload="nav(),loadData(),loadData_modify()">

<jsp:include page="/teacher/menu.jsp"></jsp:include>
<div class="container-fluid" style="position: absolute;top: 160px;left: 330px" >
       <div id="contain" style="display:flex; flex-direction: column;" >
          <p style="font-size: 30px;font-weight: bold;">修改练习</p>
          
          <div >
           <p>请输入需要发布的练习名称：</p>
           <input class="form-control" aria-describedby="basic-addon1" style="width: 300px; margin-top: 7px;" id="prac_name" type="text" placeholder="">
          </div>
          
          
           <div id="prac_belong" style="margin-top:20px;">
           <p>请选择所属课程名称</p>
           <div class="dropdown"  style="margin-top: 7px;">
            <button class="btn btn-default dropdown-toggle"   type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    请选择发布的课程
            <span class="caret"></span>
           </button>
             <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="class_option">
           	  <li><a href="#">高级数据库</a></li>
          	  <li><a href="#">数据库原理</a></li>
          	  <li><a href="#">数据库设计</a></li>
         	  <li role="separator" class="divider"></li>
         	  <li><a href="#">请确认</a></li>
             </ul>
          </div>
        
        
<div style="margin-top:25px;">
  <div class="container">
    <form action="" class="form-horizontal"  role="form">
        <fieldset> 
            <div class="form-group">
                <label for="dtp_input1" class="control-label">作业截止日期</label>
                <div class="input-group date form_datetime col-md-5" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input class="form-control"  id="time" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
        </fieldset>
    </form>
  </div>
</div>
           </div>
        
      
       
        <div id="prac_class" style="position: relative;display: flex;top: -9px;">
          <div>
           <p>请选择你要发布的班级</p><span style="display:none" id="class_id"></span>
            <div class="dropdown" style="margin-top: 7px;">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                       选择班级
                <span class="caret"></span>
               </button>
                 <ul class="dropdown-menu" aria-labelledby="dropdownMenu2" id="class_option2">
           	    
                </ul>
             </div>
            </div> 
            
           <div style="position: relative;left: 6%;">
             <p>请选择操作的库</p>
         <div class="dropdown"  style="margin-top: 7px;">
            <button class="btn btn-default dropdown-toggle"   type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" >选择操作库
            <span class="caret"></span>
           </button>
             <ul class="dropdown-menu" aria-labelledby="dropdownMenu3" id="class_option3">
           	  
             </ul>
          </div>
             </div>
           
           
           </div> 
         </div>
         
       <div class="prac_score" style="margin-top:20px;">
           <p>请输入分数占比</p>
           <div class="score_row">
            <div>
              <input class="form-control" aria-describedby="basic-addon1" style="width: 60px;" id="result_score" type="text" placeholder="">
              <p>结果占比</p>
            </div>
            <div >
              <input class="form-control" aria-describedby="basic-addon1" style="width: 60px;" id="process_score" type="text" placeholder="">
              <p>过程占比</p>
            </div>
           </div>
           <p style="position: relative;left: -10px;top: 5px;font-size: 13px;">（请注意两者占比之和必须为10）</p>
            <button type="button" class="btn btn-warning" style="position: absolute;top: 105%;background-color: #ffb22b;color: white;" onclick="modify()" >修改了哦</button>
       </div>  
 <div>
 </div>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
 <script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
</script>

  </body>
</html>