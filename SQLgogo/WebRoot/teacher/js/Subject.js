var pro_portion;
var res_portion;

function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
function loadData() {
	
	var thisURL = document.URL; 
	
	var getval =thisURL.split('?')[1]; 
	getName = getval.split('&')[1];
	var id= getval.split("=")[1]; 
	var pra_id = id.split("&")[0];
	//var pra_name = document.URL.substring(document.URL.lastIndexOf("=")+1);
    var prename = getName.split("=")[1];
    var name = decodeURI(prename);
   console.log(name)
	
    $("#add").click(function(){
    	 window.location.href="/SQLgogo/teacher/AddSubject.jsp?Pid="+id+"="+name;    
    });
    
    $("#pick_sub").click(function(){
   	 window.location.href="/SQLgogo/teacher/pick_sub.jsp?Pid="+id+"="+name;    
   });
    
    $("#topic p").text(name);
    
    
    $.post("../PracticeServlet?status=findOne",{pra_id:pra_id},function(data){
    	
    	data = eval(`(${data})`);
    	console.log(data)
          
    	var now = new Date().getTime();
    	
    	if(now >data.one.deadline){
    		$("#expired").css("opacity","1")
    	}
    	let result_portion = data.one.result_portion;
    	res_portion = parseInt(result_portion)*0.1;
    	let process_portion = data.one.process_portion;
    	pro_portion = parseInt(process_portion)*0.1;
    	
    	var ques_id = data.one.ques_id;
    
      	$.get("../QueServlet?searchName=getName",{ques_id:ques_id},function(data){
      			
      			 $("#choice_db").text(data)
 	        });	
    	
    	$("#result_portion").text(result_portion);
    	$("#process_portion").text(process_portion);
    	
    });
    
    
	$.post("../SubjectServlet?status=look",{prac_id: id},function(data){
		  var a = 1;
	      data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	      
	      console.log(data)
	    
	     for(let item of data){
	    	   //let {practiceName} = item;
	    	var process_score = Math.ceil(parseInt(item.total) *pro_portion);
	    	var result_score =  Math.floor(parseInt(item.total) * res_portion);
	    	 $("#table").append(`<tr>
                  <td>${a}</td>
                  <td>${item.subjectName}</td>
                  <td>${process_score}</td>
                  <td>${result_score}</td>
                  <td>${item.total}</td>
                  <td><a onclick="s_show(${item.subjectId})" >查看</a><a onclick="s_delete(${item.subjectId},${pra_id})">删除</a></td>
                  </tr>`)	
                  a++;	   	   
	     }
	});

}

function all_deleted(){

	var url = location.search;
	console.log(url)
	var id = url.split("?")[1].split("&")[0].split("=")[1];
	console.log(id)
	layer.confirm('确定要全部删除吗？', { offset: '280px' },function(index2){
	     $.post("../SubjectServlet?status=all_delete",{prac_id:id},function(){
	    	 console.log("全部删除啊！！！！！！！！！！！")
			});
	  layer.close(index2);
	  history.go(0);
	});  
}



function s_delete(sub_id,pra_id){
	
   layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
     $.post("../SubjectServlet?status=delete",{sub_id:sub_id,pra_id:pra_id},function(data){
		});
  layer.close(index);
  history.go(0);
});  

}    
                  
function s_show(id){
	let path = decodeURI(document.URL.split("?")[1]);
	
                 window.location.href="/SQLgogo/teacher/SubjectDetail.jsp?Sub="+id+"&"+path;    

}