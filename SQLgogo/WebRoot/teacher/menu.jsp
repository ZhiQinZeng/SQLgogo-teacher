
<%@ page language="java" import="javax.servlet.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    
    <link href="./icon/menu/iconfont.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/menu.js"></script>
    <script>
function r_page_main(page_name){

	      $("#page_nav").empty();
	
		  let li = document.createElement("li");
		  li.innerHTML = `${page_name}`;
		 
		  $("#page_nav").append(li);

}
function router(path){
    switch(path)
{
case 'main':
   window.location.href="/SQLgogo/teacher/main.jsp?route=main";
  break;
case 'practice':
   window.location.href="/SQLgogo/teacher/practice.jsp?route=practice";
  break;
case 'ambient':
   window.location.href="/SQLgogo/teacher/ready.jsp?route=ambient";
  break;
case 'ambient_prepare':
   window.location.href="/SQLgogo/teacher/dbmanager.jsp?route=ambient_prepare";
  break;
case 'warehouse':
   window.location.href="/SQLgogo/teacher/scanQue.jsp?route=warehouse";
  break;
case 'result':
   window.location.href="/SQLgogo/teacher/result.jsp?route=result";
  break;
/* case 'compete':
   window.location.href="/see_testfiles";
  break; */
case 'class_regulate':
   window.location.href="/SQLgogo/teacher/classAdmin.jsp?route=class_regulate";
  break;
case 'tea_regulate':
   window.location.href="/SQLgogo/teacher/teacherAdd.jsp?route=tea_regulate";
  break; 

case 'cart':
   try{
	  var user = localStorage.getItem("user")
	  console.log(user)
	  window.location.href="../cart/cart.jsp";
	 }catch(e){
	   layer.msg('请先登录!',{ offset: '350px' })
	 }
  break;
default:
}
   }
  
</script>
  </head>
  <body onload="nav()">
    <div class="header"> 
       <div class="logo">
         <p class="logoer">SQLGOGO</p>
       <div id="menu_user" class="menu_user">
      
       </div>
       </div>
       
       
    </div>
    <div class="mine">
         <ol class="breadcrumb page_nav"  id="page_nav">
          			 <li>page guide</li>
          			 </ol>
    </div>
   <div class="left-menu">
       <div class="main-menu">
            <ul class="menu">
              <li id="main"><a>
                <i class="iconfont icon-yemian" onclick="router('main')">
                <span>首页</span>
                </i>
              </a></li>
              <li id="practice"><a>
                <i class="iconfont icon-lianxi-" onclick="router('practice')">
                <span>发布练习</span>
                </i>
              </a></li>
              <li id="ambient"><a>
                <i class="iconfont icon-huanjing" onclick="router('ambient')">
                <span style="position: relative;left: -10px; ">环境准备</span>
                </i>
              </a></li>
              <li id="ambient_prepare"><a>
                <i class="iconfont icon-huanjing" onclick="router('ambient_prepare')">
                <span style="position: relative;left: -10px; ">环境库管理</span>
                </i>
              </a></li>
              <li id="result"><a>
                <i class="iconfont icon-jieguo" onclick="router('result')">
                <span>学生评测结果查询</span>
                </i>
              </a></li>
              <li id="warehouse"><a>
                <i class="iconfont icon-tikuguanli" onclick="router('warehouse')">
                <span>题库管理</span>
                </i>
              </a></li>
              <!-- <li id="compete"><a>
                <i class="iconfont icon-competition" onclick="router('compete')">
                <span>竞赛管理</span>
                </i>
              </a></li> -->
              <li id="class_regulate"><a>
                <i class="iconfont icon-renwu-tuandui" onclick="router('class_regulate')">
                <span>班级管理</span>
                </i>
              </a></li>
              <li id="tea_regulate"><a>
                <i class="iconfont icon-xueshengguanli" onclick="router('tea_regulate')">
                <span>教师添加</span>
                </i>
              </a></li>
             
 			<!-- <li id="stu_log"><a>
                <i class="iconfont icon-huabanfuben" onclick="router('stu_log')">
                <span>学生导入日志</span>
                </i>
              </a></li> -->
            </ul>
       </div>
       <div class="option">
          <div>
             <a><i class="iconfont icon-shezhi"></i></a>
              <a><i class="iconfont icon-youxiang"></i></a>
              <a id="login_out"> <i class="iconfont icon-guanji" title="注销"></i></a>
          </div>
       </div>
   </div>
   
  </body>
</html>
