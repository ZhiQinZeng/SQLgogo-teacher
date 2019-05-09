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
	  	 	var id = res[i].id;
	  	 	var className = res[i].className;
	  	 	$("#select").append(`<li><a href="#" onclick="choice_class('${className}',${id})">${className}</a></li>`);	   	   					     
	  	}
	  },
	  error:function(err){
		  alert("系统繁忙，请稍后再试！");
	  }
  });
}

function assure(){
  	
  	var studentsnum = $('#studentsnum').val();
  	var studentname = $('#studentname').val();
  	var studentclass = $('#class_choice').text();
  	
	let class_id = selval;
	alert(id)
	var seltext = studentclass;
	
  	if(studentname == "" || studentsnum == ""  || id =="" ){
  		layer.msg("都不能为空，请检查！");
  		return false;
  	}
 	
  $.ajax({
	  url:"../StudentsServlet?method=addStu",
	  type:"post",
	  dataType:"json",
	  async:true,
	  data: {
		 "studentname": studentname,
		"studentsnum": studentsnum ,
		"selval": class_id,
		"seltext":seltext		  					
  		},
	  success:function(result){	
	  	res = result.res;
	  	if(res == "1"){
	  	  layer.msg('添加成功！', {
            }, function(){
               $('#fanhui').click();
            });
	  	 }else if(res == "2") {
	  	 	layer.msg('此学号已经存在，添加失败！', {
            }, function(){
               
            });
	  	 }  
		  
	  },
	  error:function(err){
		  alert("系统繁忙，请稍后再试！");
	  }
  });
  	
  	
 }