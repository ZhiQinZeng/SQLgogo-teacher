var selval;
function choice_class(name,id){
	$("#class_choice").text(name);
	selval = id;
}
function LoadData(){	
	$.ajax({
	  url:"../StudentsServlet?method=getclasses",
	  type:"post",
	  dataType:"json",
	  async:true,
	  success:function(result){
	  	res = result.res;	  			  
	 	 for(var i=0;i<res.length;i++){
	  	 	let id = res[i].id;
	  	 	let className = res[i].className;
	 
		 	$("#select").append(`<li><a href="#" onclick="choice_class('${className}',${id})">${className}</a></li>`);		   	   					     
	  	}
	  },
	  error:function(err){
		  alert("请先登录！");
	  }
  });
}

function assure(){
  	
  	var studentsnum = $('#studentsnum').val();
  	var studentname = $('#studentname').val();
  	var studentclass =$('#class_choice').text();
  	var stuid = $('#stuid').val();
  	
  	let class_id = selval;
	var seltext = studentclass;
	
  	if(studentname == "" || studentsnum == ""  || stuid == "" || class_id =="" ){
  		layer.msg("都不能为空，请检查！");
  		return false;
  	}
 	
  	$.ajax({
	  url:"/SQLgogo/StudentsServlet?method=updateStu",
	  type:"post",
	  dataType:"json",
	  async:true,
	  data: {
	  	"stuid":stuid,
		 "studentname": studentname,
		"studentsnum": studentsnum ,
		"selval": class_id,
		"seltext":seltext		  					
  		},
	  success:function(result){	
	  	res = result.res;
	  	if(res == "1"){
	  	  layer.msg('修改成功！', {
            }, function(){
               $('#fanhui').click();
            });
	  	 }else if(res == "2") {
	  	 	layer.msg('此学号已经存在，修改失败！', {
            }, function(){
               $('#fanhui').click();
            });
	  	 }  
		  
	  },
	  error:function(err){
		  alert("系统繁忙，请稍后再试！");
	  }
  });
  	
  	
 }