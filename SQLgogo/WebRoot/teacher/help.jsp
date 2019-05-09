<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title>SQLgogo自动评测</title>
    <style type="text/css">
        a, a:link, a:hover, a:active, a:visited {
            text-decoration: none;
            color: #666;
        }
        a:hover {
            color: #259;
        }
    .testDIV{
        width: 520px;
        text-overflow:ellipsis;
        white-space:nowrap;
        overflow:hidden;
        }

    </style>
    
    <script>
   		function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
   </script>
    
</head>

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
                    <li><a href="/SQLgogo/teacher/personal.jsp">个人主页</a></li>
                    <li><a href="/SQLgogo/teacher/help.jsp">帮助</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">密码修改</a></li>
                    <li><a href="javascript:void(0):" onclick="exit()">退出</a></li>
                </ul>
            </div>

    </div>
</nav>
<div style="position: absolute;left: 350px;top: 200px">
    <strong>联系我们</strong>
    <br/>
    <br/>
    <div style="font-size: 13px">
        北京城市学院软件创新大赛项目，SQLgogo自动评测系统！
        作者班级:16级软本2班
        <br/>
        预期成果:
        <br/>
        我们将开发一套学生作业自动测评网站，分为老师端和教师端两个网页，老师端 可以实现 <br/>
        1.教师端登录，练习发布功能 <br/>
        2.学生测评结果查询功能 <br/>
        3.题库的上传，维护功能 <br/>
        4.学生登录操作网页的信息记录日志功能 <br/>
        5.系统模块个人主页，帮助， <br/>
        6.教师，学生互相问答 <br/>
        关于，退出等。 <br/>
        学生端可以实现: <br/>
        1.学生端登入 <br/>
        2.学生练习结果 上传，自动测评 <br/>
        3.下载题库 <br/>
        4.学生，教师互相问答 <br/>
        <BR/>
应用情况:
        <BR/>
        应用于全国各大高校，提高数据库老师的教学效率和学生的学习效率，使得老师 <br/>
        不必花大部分精力在作业批改上面， 转而把更多精力投入到研究备课，和与学生 <br/>
        的交 流当中，提高师生的学习时间利用率。 学生可以通过此平台更加方便的学习， <br/>
        不懂的 问题可以通过在线答疑得到及时有效的解决， 通过各种优秀作业可以学习到 <br/>
        各种不同 的思路，拓展学生思维。而且此应用面向全国学习此课程的学生，面对面很广。 <br/>
    </div>
    <br/>
    <div style="font-size: 13px">请通过以下方式联系我们：）</div>
    <br>

    <div style="font-size: 13px">wechat:18210915622</div>
    <br/>
    <div style="font-size: 13px">违法和不良信息举报：010-82716601</div>

    <br/>

</div>


