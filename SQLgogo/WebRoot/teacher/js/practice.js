var page = 1;

function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
        } else {
        return false;
        }
    }

function render(item){
	 let {practiceName} = item;

	   let li = document.createElement("li")
	   li.innerHTML = `${practiceName}`;
	   $("#prac_name").append(li);
	   

	 $("tbody").append(`<tr>
             <td>${item.practiceName}</td>
             <td>${item.practiceGenre}</td>
             <td>${item.className}</td>
             <td>${item.publishDay}</td>
             <td>${item.deadline}</td>
             <td><a onclick="detail(${item.practiceId},'${item.practiceName}')">查看</a>
             <a href="./modify_practice.jsp?Pna=${item.practiceName}&Pid=${item.practiceId}">修改</a><a onclick="p_delete(${item.practiceId})">删除</a></td>
             </tr>`)	
		  
}
function loadData() {
	 
		$.post("../PracticeServlet?status=list",{page:page},function(data){
			   data = eval(`(${data})`);
			   let practice = eval(`(${data.practice})`);
			   let sum = data.sum;
		      

				 sum = Math.ceil(sum/7);
				 $("#page_count").text(sum);
		      console.log(data)
		      
		     
		      $("#prac_names").empty();
		      $("#prac_names").append(`<ol class="breadcrumb" style="margin-bottom: 10px;margin-top: 10px;" id="prac_name" ><ol>`);
		      console.log(data);
		     
		     for(let item of practice){	    	  
		    	   render(item);
		     }
		});
		$("#page").val(page)
	}

function doQes(qes){
	$("#table").empty();
	 let tbody = document.createElement("tbody")
	 $("#table").append(tbody);
	 $("tbody").append(` <tr>
            <td >练习名称</td>
            <td>所属课程</td>
            <td>适用班级</td>
            <td>发布时间</td>
            <td>截止时间</td>
            <td style="padding-left: 55px;">操作</td>
         </tr>`)
      for(item of qes){	
	    	  render(item)
	 }
	
	$("#page").val(page)
}

function detail(id,name){

        window.location.href="/SQLgogo/teacher/Subject.jsp?Pid="+id+"&Pna="+name; 
        
  }
function p_delete(id){
   layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
     $.post("../PracticeServlet?status=delete",{prac_id:id},function(data){
		});
  layer.close(index);
  history.go(0);
});  
}


function skip(){
	  let num = $("#page").val()
	  if(num>$("#page_count").text()){
	     layer.msg('页数输入有误！',{ offset: '350px' })
	     return;
	  }else if(!isNumber(num)){
	     layer.msg('页数输入有误！',{ offset: '350px' })
	     return;
	  }else if(num == 0){
	    layer.msg('页数输入有误！',{ offset: '350px' })
	    return;
	  }else{
	    $.post("../PracticeServlet?status=list",{page:num},function(data){
	    	 data = eval(`(${data})`);
		         let practice = eval(`(${data.practice})`);
		         $("#prac_names").empty();
    		      $("#prac_names").append(`<ol class="breadcrumb" style="margin-bottom: 10px;margin-top: 10px;" id="prac_name" ><ol>`);
	                    doQes(practice);
	        })
	  }
	  page = parseInt(num);
	}
	 $(document).ready(function(){
	        $("#pre").bind("click",function(){
	        if(page == 1){
	           layer.msg('已经是第一页!',{ offset: '350px' })
	           return;
	        }
	                   page -= 1;
	                   $.post("../PracticeServlet?status=list",{page:page},function(data){
	                	   data = eval(`(${data})`);
	 				      let practice = eval(`(${data.practice})`);
	 				     $("#prac_names").empty();
	 				      $("#prac_names").append(`<ol class="breadcrumb" style="margin-bottom: 10px;margin-top: 10px;" id="prac_name" ><ol>`);
	                    doQes(practice);
	          })
	               
	        })
	     
	         $("#next").bind("click",function(){

	               page += 1;
	               console.log(page)
	          $.post("../PracticeServlet?status=list",{page:page},function(data){
	        	          data = eval(`(${data})`);
				      let practice = eval(`(${data.practice})`);
	                   if(practice.length==0){
	                    layer.msg('没有更多数据了!',{ offset: '350px' })
	                    page-=1;
	                   return;
	                   }else{     
	                	   $("#prac_names").empty();
	         		      $("#prac_names").append(`<ol class="breadcrumb" style="margin-bottom: 10px;margin-top: 10px;" id="prac_name" ><ol>`);
	                    doQes(practice);
	                    }
	          })  
	        })
	        
	       	 

	 })    
