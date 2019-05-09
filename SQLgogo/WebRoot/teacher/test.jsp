<%@page import="java.sql.ResultSet"%>
<%@page import="bean.ClassInfo"%>
<%@page import="dao.ClassDao"%>
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
    <title>SQLgogo自动评测</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
        
        function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
        
        
        function last(){
        	
        }
        
        
        function next(){
       	}
   </script>
    
    <style type="text/css">
            table {
                border: 1px solid black;
                margin: 0 auto;
            }
            
            td{
                width: 150px;
                border: 1px solid black;
                text-align: left;
            }
    </style>
   
   
   
	
  </head>
  
  <body>
  
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">

            <div class="navbar-header">
                <button class="navbar-toggle" data-toggle="collapse" data-target="#togglemenu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">SQLgogo Judge</a>

            </div>
            <div class="collapse navbar-collapse" id="togglemenu">
                <ul class="nav navbar-nav navbar-right"><!--定义导航中的菜单-->
                    <li class="active"><a href="/SQLgogo/teacher/main.jsp">欢迎您，<%=session.getAttribute("realname")%></a></li>
                    <li><a href="/SQLgogo/teacher/perInfo.jsp">个人主页</a></li>
                    <li><a href="/SQLgogo/teacher/help.jsp">帮助</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="/SQLgogo/teacher/changepwd.jsp">密码修改</a></li>
                    <li><a href="javascript:void(0):" onclick="exit()">退出</a></li>
                </ul>
            </div>

    </div>
</nav>
  
  
  <a href="/SQLgogo/teacher/main.jsp">&lt&lt返回 </a>
    <br><br>
<div align="center">
	<font size="5">班级管理</font>
</div>



<br/>
<div  style="margin-left:220px; width: 900px; height:400px; border:1px solid black;">
	<form  action="post">
	
		<div  style="margin-top: 23px; margin-left: 650px; font-size:16px; font-style:italic;" >
		   
			<a title="添加" onclick="location=location">添加</ a>  &nbsp
			<a title="删除" id="id-delete">删除</ a>  &nbsp
			<a title="修改" > 修改</ a>  &nbsp
			<a title="刷新" id="id-delete">刷新</ a>  &nbsp
		</div>
		
		<div align="center">
			<table id="tableid">
					<thead>
						<tr>

							<td>班级名称</td>
							<td>教师</td>
							<td>班级人数</td>
							<td>添加日期</td>
							<td colspan="5" align="center">班级学生操作</td>
						</tr>
					</thead>
						<tbody id="tbodyid">
						<%
							int number=7;     //每一页显示的条数
							int pagenow=1;    //当前第几页
							int pagecount=0;  //一共多少页
							ClassDao classDao=new ClassDao();
							ClassInfo classInfo=new ClassInfo();
							int rows = classDao.getCount();   //一共有几条数据
							
							//计算总页数
							if(rows%number==0){
								pagecount=(int)(rows/number);
							}else{
								pagecount=(int)(rows/number)+1;
							}
							//查找数据
							int start=(pagenow-1)*number;
							
							List<ClassInfo> list=classDao.getClass(start, number);
							
						 	if(list == null || list.size() == 0){
						 %>
						 	<tr>
						 		<td colspan="5">还没有创建班级</td>
						 	</tr>
						<%
							}else{ 
								for(int i=0;i<list.size();i++) {
						%>
     						<tr>
     							<td><%=list.get(i).getClassName() %></td>
     							<td><%=list.get(i).getTeacherName() %></td>
     							<td><%=list.get(i).getStudentsNumber() %></td>
     							<td><%=list.get(i).getAddtime() %></td>
     							<td><a href="">查看</a> &nbsp
     								<a href="">添加</a> &nbsp
     								<a href="">修改</a> &nbsp
     								<a href="">删除</a> &nbsp
     								<a href="">Excel导入</a> &nbsp
     							</td>
     							
    							
     						</tr>
     					<%
     						}
     					}
     					 %>
     					 
     				
					</tbody>
				</table>
		</div>
	
 		<div style="margin-left:400px; position:absolute; bottom:60px;">
 			<input type="button" value="上一页" onclick="last();"/>
 			<input type="button" value="下一页" onclick="next();"/>
 			
 		</div>
	</form>
 </div>

  </body>
</html>
