function nav(){
	
	
$(".menu li").attr("class","none")

console.log(document.URL)
var thisURL = document.URL
var getval =thisURL.split('?')[1]; 
var path = "main";


var use_page_url = document.URL.substring(0,document.URL.lastIndexOf('/')+1);  // http://localhost:8080/SQLgogo/teacher/

let page_name  = document.URL.substring(document.URL.lastIndexOf("/")+1,document.URL.lastIndexOf('.'));

let page_nav = [];   //ol配装数组
let page_link = [];
function r_page_nav(arr,arr_link){
	$("#page_nav").empty();
	  for(let i=0;i<arr.length;i++){
		  
		  
		  let li = document.createElement("li");
		  li.innerHTML = `<a href="${arr_link[i]}">${arr[i]}</a>`;
		 
		  $("#page_nav").append(li);
		  
	  }
}

switch(page_name)    //这可能是我写过最蠢的代码了
{
case "question":
	$("#main").attr("class","activity");
	page_nav = ['首页','问题'];
	page_link = [use_page_url+'main.jsp',thisURL]
	r_page_nav(page_nav,page_link);
    break;
case "NewQes":
	$("#main").attr("class","activity");
	break;
case "TeaFocus":
	$("#main").attr("class","activity");
	break;
case "TeacherQes":
	$("#main").attr("class","activity");
	break;
case "Answer":
	$("#main").attr("class","activity");
	break;
case "main":
	$("#main").attr("class","activity");
	break;
case "AddTask":
	$("#main").attr("class","activity");
	page_nav = ['首页','提出问题'];
	page_link = [use_page_url+'main.jsp',thisURL]
	r_page_nav(page_nav,page_link);
	break;
case "AddPractice":
	$("#practice").attr("class","activity");
	page_nav = ['发布练习','添加练习'];
	page_link = [use_page_url+'practice.jsp?route=practice',thisURL]
	r_page_nav(page_nav,page_link);
	break;	
case "modify_practice":
	$("#practice").attr("class","activity");
	page_nav = ['发布练习','修改练习'];
	page_link = [use_page_url+'practice.jsp?route=practice',thisURL]
	r_page_nav(page_nav,page_link);
	break;	
case "Subject":
	$("#practice").attr("class","activity");
	page_nav = ['发布练习',decodeURIComponent(document.URL.split('=')[2])];
	page_link = [use_page_url+'practice.jsp?route=practice',thisURL]
	r_page_nav(page_nav,page_link);
	break;
case "pick_sub":
	$("#practice").attr("class","activity");
	page_nav = ['发布练习',decodeURIComponent(document.URL.split('=')[2]),'从题库中选择'];
	page_link = [use_page_url+'practice.jsp?route=practice',use_page_url+'Subject.jsp?'+decodeURIComponent(document.URL.split("?")[1]),thisURL]
	r_page_nav(page_nav,page_link);
	break;
case "sel_qes":
	$("#practice").attr("class","activity");
	
    let use_page =  decodeURIComponent(document.URL.split("?")[1]);
    let use_pna = use_page.substring(use_page.lastIndexOf('&'));
    let use_pid = use_page.substring(0,use_page.indexOf('&'));
    
    page_nav = ['发布练习',decodeURIComponent(document.URL.split('=')[4]),'从题库中选择','题目选择'];
	page_link = [use_page_url+'practice.jsp?route=practice',use_page_url+'Subject.jsp?'+use_pid+use_pna,use_page_url+'pick_sub.jsp?'+use_pid+use_pna,thisURL]
	r_page_nav(page_nav,page_link);
	break;
case "AddSubject":
	$("#practice").attr("class","activity");
	page_nav = ['发布练习',decodeURIComponent(document.URL.split('=')[2]),'自定义问题'];
	page_link = [use_page_url+'practice.jsp?route=practice',use_page_url+'Subject.jsp?'+decodeURIComponent(document.URL.split('?')[1]),thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "SubjectDetail":
	$("#practice").attr("class","activity");
	let s_detail_pna = decodeURI(document.URL.split("?")[1]).split('&')[2].split("=")[1];
	page_nav = ['发布练习',s_detail_pna,'问题详情'];
	page_link = [use_page_url+'practice.jsp?route=practice',use_page_url+'Subject.jsp?'+decodeURI(document.URL.split("?")[1]).split('&')[1]+"&"+decodeURI(document.URL.split("?")[1]).split('&')[2],thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "SubDetailAdd":
	$("#practice").attr("class","activity");
	
	page_nav = ['发布练习','问题详情'];
	page_link = [use_page_url+'practice.jsp?route=practice',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "lookdbtables":
	$("#ambient_prepare").attr("class","activity");
	page_nav = ['环境库管理',"表结构"];
	page_link = [use_page_url+'dbmanager.jsp?route=ambient_prepare',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "resultlook":
	$("#result").attr("class","activity");
	page_nav = ['测评结果查询',"结果详情"];
	page_link = [use_page_url+'result.jsp?route=result',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "sturesultlook":
	$("#result").attr("class","activity");
	
	page_nav = ['测评结果查询',"结果详情","单个问题详情查看"];
	
	page_link = [use_page_url+'result.jsp?route=result',use_page_url+'resultlook.jsp?'+decodeURI(document.URL.split("?")[1]).split("&")[0]+"&"+decodeURI(document.URL.split("?")[1]).split("&")[2],thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "addQue":
	$("#warehouse").attr("class","activity");
	page_nav = ['题库管理',"新增题库"];
	page_link = [use_page_url+'scanQue.jsp?route=warehouse',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "updateQue":
	$("#warehouse").attr("class","activity");
	page_nav = ['题库管理',"修改题库"];
	page_link = [use_page_url+'scanQue.jsp?route=warehouse',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "lookQue":
	$("#warehouse").attr("class","activity");
	page_nav = ['题库管理',"题库题目查看"];
	page_link = [use_page_url+'scanQue.jsp?route=warehouse',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "AddSubject_que":
	$("#warehouse").attr("class","activity");
	page_nav = ['题库管理',"题库题目查看","问题添加"];
	page_link = [use_page_url+'scanQue.jsp?route=warehouse',use_page_url+'lookQue.jsp?'+document.URL.split("?")[1].split("&")[1],thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "updateSubject":
	$("#warehouse").attr("class","activity");
	var arr = document.URL.split("&");
	let update_lookid;
	for(item of arr){
		if(item.indexOf("lookid")!=-1){
			update_lookid = item;
		}
	}
	page_nav = ['题库管理',"题库题目查看","问题修改"];
	page_link = [use_page_url+'scanQue.jsp?route=warehouse',use_page_url+'lookQue.jsp?'+update_lookid,thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "addClass":
	$("#class_regulate").attr("class","activity");
	page_nav = ['班级管理',"创建班级"];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "updateClass":
	$("#class_regulate").attr("class","activity");
	page_nav = ['班级管理',"班级信息修改"];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',thisURL];
	r_page_nav(page_nav,page_link);
	break;
	
case "studentsAdmin":
	$("#class_regulate").attr("class","activity");
	page_nav = ['班级管理',decodeURI(document.URL.split("?")[1].split("&")[1].split("=")[1])];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "addStudent":
	$("#class_regulate").attr("class","activity");
	page_nav = ['班级管理',decodeURI(document.URL.split("?")[1].split("&")[1].split("=")[1]),"增添学生"];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',use_page_url+'studentsAdmin.jsp?'+document.URL.split("?")[1],thisURL];
	r_page_nav(page_nav,page_link);
	break;
	
case "lookStudent":
	$("#class_regulate").attr("class","activity");
	page_nav = ['班级管理',decodeURI(document.URL.split("?")[1].split("&")[2].split("=")[1]),"信息查看"];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',use_page_url+'studentsAdmin.jsp?'+decodeURI(document.URL.split("?")[1].substring(document.URL.split("?")[1].indexOf('&')+1)),thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "updateStudent":
	$("#class_regulate").attr("class","activity");
	var cl_re_classId = document.URL.split("?")[1].split("&")[2];
	var cl_re_className = decodeURI(document.URL.split("?")[1].split("&")[3]);
	var cl_back = cl_re_classId+'&'+cl_re_className;
	page_nav = ['班级管理',decodeURI(document.URL.split("?")[1].split("&")[3].split("=")[1]),"学生信息修改"];
	page_link = [use_page_url+'classAdmin.jsp?route=class_regulate',use_page_url+'studentsAdmin.jsp?'+cl_back,thisURL];
	r_page_nav(page_nav,page_link);
	break;
case "classAdmin":
		$("#class_regulate").attr("class","activity");
		break;
default:
	
}


try{ 
	path = getval.split("=")[1];
	$("#"+path).attr("class","activity")
 
}catch(e){
 
}


try{
	$.post("../check",function(data){				
		 data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	     console.log(data,"user!!!!!");
	     if(data!=null){
	    	 localStorage.setItem("userId",data.userId)
	     
		     $("#menu_user").append(`
		           <a href="">
		            <img src="${data.userphoto}" class="img-circle" style="width:50px; height:50px;" title="查看信息"></img>
		    		<span style="font-family: initial;color: white;position: relative;left: 6px;font-size: 18px;top: 3px;">${data.realname}</span>
		    	   </a>
		     `);
	     }else{
	    	 $("#menu_user").append(`
			         <a href="/SQLgogo/teacher/login.jsp">
	    			 	<i class="iconfont icon-yonghu" onclick="" title="暂未登录" style="font-size: 43px;color:#fdfcfc"> </i>
	    			 </a>
			     `);
	    	 
	     }
	    
	})
	
	
}catch(e){
	 console.log("未登录！！！");
}
}
$(document).ready(function(){
	$("#login_out").bind("click",()=>{
		layer.confirm('确定要注销吗', { offset: '280px' },function(index){	
			$.post("../log_out",function(data){
				layer.msg('注销成功',{ offset: '350px' })
				setTimeout(()=>{window.location.href="/SQLgogo/teacher/login.jsp"},1000)
				
			})	
		})	
	})
})
