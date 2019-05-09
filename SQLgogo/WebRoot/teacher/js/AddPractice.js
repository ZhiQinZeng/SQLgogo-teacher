var class_id;
function loadData(){
	$.get("../DbServlet?status=list_db",function(data){
		 
	        data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	        console.log(data)
	        for(let item of data){
		    	 $("#class_option3").append(`<li><a href="#" onclick="choice('${item.dbname}')">${item.dbname}</a></li>`)		   	   
		     }
	  });
	
	$.get("../DbServlet?status=list_class",function(data){
		 
        data = eval(`(${data})`);   //这是将字符串转成对象的语句。
        console.log(data)
        for(let item of data){
	    	 $("#class_option2").append(`<li><a href="#" onclick="choice_class('${item.className}',${item.id})">${item.className}</a></li>`)		   	   
	     }
  });

}

function choice(name){
	$("#dropdownMenu3").text(name);
}
function choice_class(name,id){
	$("#dropdownMenu2").text(name);
	$("#class_id").text(id);
}
function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
        

function submit(){
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
	    if(prac_deadline==""){
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
	  
	    $(document).ready(function(){	
	           $.post("../PracticeServlet?status=add",{prac_name: prac_name,         //prac_protray:prac_protray,prac_answer:prac_answer,
	           							   prac_genre:prac_genre,
	           							   prac_class:prac_class,
	           							   prac_deadline:prac_deadline,
	           							result_portion:result_portion,
	           							process_portion:process_portion,
	           							db_choice:db_choice,
	           							class_id:class_id
	          },function(){
	        	  layer.msg('提交成功',{ offset: '350px' })
					setTimeout(function(){
                    window.history.go(-2);
                        }, 1500);
	          })
	    });
	    
	  }
		
$(document).ready(function(){	
	         var classes = $("#class_option li");
	         $("#class_option li").click(function(){//点击事件   
                   var count=$(this).index();//获取li的下标
                   var Tresult=classes.eq(count).text();
                   //console.log(Tresult)
                   $("#dropdownMenu1").html(Tresult);
              }) 
              

              var classes2 = $("#class_option2 li");
	         $("#class_option2 li").click(function(){//点击事件   
                   var count=$(this).index();//获取li的下标
                   var Tresult=classes2.eq(count).text();
                   $("#dropdownMenu2").html(Tresult);
              }) 
       
	});
