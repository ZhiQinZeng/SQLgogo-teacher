var qes_id = [];
var qes_name = [];


try{
	 let newArray  = localStorage.getItem("qesArray").split(',')
	   
	   qes_id = newArray.map(item=>{
          return parseInt(item);
    });
    
	   qes_name = localStorage.getItem("qesName").split(',')
	   console.log(qes_id)
	   console.log(qes_name)
}catch(e){
		
	}


function detail(id){
	window.location.href="/SQLgogo/teacher/SubDetailAdd.jsp?Sub="+id 
}

function loadData(){
   var thisURL = document.URL; 
	
	var getval =thisURL.split('?')[1]; 
	var getid = getval.split('&')[1];
	var getName = getval.split('&')[2];
	var Sid = getid.split('=')[1];
	 

	$.get("../Sub_Qes?status=list",{Sid:Sid},function(data){
		  let a = 1;
	        data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	        console.log(data)
	        for(let item of data){
		    	   
		    	 $("#table").append(`<tr>
                <td id="${item.subjectId}">${a}</td>
                <td>${item.subjectName}</td>
                <td>${item.subject_detail}</td>
                <td><a style="position: relative;left: -17px;"onclick="detail(${item.subjectId})">进入</a><a onclick="add(${item.subjectId},'${item.subjectName}')">添加</a></td>
                </tr>`)		
                a++;
		     }
		     
	  });	
}

function add(id,name){
  $("#jade").css("display","none");
  $("#submit").css("display","block");
 
      let i = qes_id.indexOf(id);
     
      if(i!=-1){
    	  layer.msg('请勿重复添加！',{ offset: '350px' })
    	  return;
      }
      
      qes_id.push(id)
      qes_name.push(name)
  $("#choiced").append(`<div class="choiced" id="choiced">
     <div id="" class="qes_item">
      <div class="qes_title">
        <span>${name}</span>
      </div>
        <button type="button" class="btn btn-primary btn-xs" onclick="check(${id})" style="background-color:#539cdf;border-color:white">查看</button>
         <button type="button" class="btn btn-danger btn-xs" onclick="delete_qes(${id},'${name}')" style="border-color:white;margin-left: 8px;" >删除</button>
     </div>
</div>`)		
			//数据持久化操作
           localStorage.setItem("qesName",qes_name);
           localStorage.setItem("qesArray",qes_id);  //自动覆盖,增长
		     }
		     
                