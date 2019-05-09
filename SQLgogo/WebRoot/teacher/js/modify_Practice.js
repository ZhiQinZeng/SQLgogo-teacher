var id = document.URL.split('&')[1].split('=')[1];
var time_m;
var frist_time;
if(id.indexOf("#")!=-1){
	id = id.split("#")[0];
}
function loadData_modify(){
	$.post("../PracticeServlet?status=findOne",{pra_id:id},function(data){
		 data = eval(`(${data})`).one;   //这是将字符串转成对象的语句。
		 console.log(data);
		 $("#prac_name").val(data.practiceName);
		 $("#dropdownMenu1").text(data.practiceGenre);
		 time_m = data.deadline;
		 let unixTimestamp = new Date( data.deadline );
		 let deadline = unixTimestamp.toLocaleString();
		 frist_time = deadline;
		 $("#time").val(deadline);
		 $("#dropdownMenu2").text(data.className);
		 $("#dropdownMenu3").text(data.dbname);
		 $("#result_score").val(data.result_portion);
		 $("#process_score").val(data.process_portion);
		 $("#class_id").text(data.classid);
			});
}

function modify(){
    var  prac_name = $("#prac_name").val();  
  
    var  prac_genre = $("#dropdownMenu1").text().trim();
    var  prac_class = $("#dropdownMenu2").text().trim();
    var  db_choice = $("#dropdownMenu3").text().trim();
    var  prac_deadline = $("#dtp_input1").val();
    
    console.log(db_choice)
    var result_portion = parseInt($("#result_score").val());
    var process_portion = parseInt($("#process_score").val());
    var class_id = $("#class_id").text();
    
    
    if(prac_name==""){
    	  layer.msg('请编辑练习名',{ offset: '350px' });
    	  return;
    }
    if(prac_deadline==""&&frist_time==""){
    	  layer.msg('请确认截止时间',{ offset: '350px' });
    	  return;
    }
    if(result_portion+process_portion!=10){
    	 layer.msg('得分比例有误！',{ offset: '350px' });
    	  return;
    }
    if(prac_genre=="请选择发布的课程" || prac_class=="请选择你要发布的班级"){
    	layer.msg('必须选择课程和班级！！！',{ offset: '350px' });
    	return;
    }
    if(db_choice =="选择操作库"){
    	layer.msg('请选择操作库！！！',{ offset: '350px' });
    	  return;
    }
    let submit_time;
    let flag;
  if(prac_deadline!==""){ //改动过
	  submit_time = prac_deadline;  //传字符串
	  flag = "change";
  }else{
	  submit_time = time_m; //传毫秒数
	  flag = "unchange";
  }
  
    $(document).ready(function(){	
           $.post("../PracticeServlet?status=modify",{prac_name: prac_name,         //prac_protray:prac_protray,prac_answer:prac_answer,
           							   prac_genre:prac_genre,
           							   prac_class:prac_class,
           							submit_time:submit_time,
           							result_portion:result_portion,
           							process_portion:process_portion,
           							db_choice:db_choice,
           							class_id:class_id,
           							prac_id:id,
           							flag:flag
          },function(){
        	  layer.msg('修改成功',{ offset: '350px' })
				setTimeout(function(){
                window.history.go(-2);
                    }, 1500);
          })
    });
    
  }