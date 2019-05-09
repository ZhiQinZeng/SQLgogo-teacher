function load_choice(){
 try{
  let qes_id = localStorage.getItem("qesArray").split(',');
  let qes_name = localStorage.getItem("qesName").split(',');
  var len = qes_id.length;
  $("#jade").css("display","none");
  $("#submit").css("display","block");
  for(let i =0;i<len;i++){
	  
	  $("#choiced").append(`<div class="choiced" id="choiced">
			     <div id="" class="qes_item">
			      <div class="qes_title">
			        <span>${qes_name[i]}</span>
			      </div>
			        <button type="button" class="btn btn-primary btn-xs" onclick="check(${qes_id[i]})" style="background-color:#539cdf;border-color:white">查看</button>
			         <button type="button" class="btn btn-danger btn-xs" onclick="delete_qes(${qes_id[i]},'${qes_name[i]}')" style="border-color:white;margin-left: 8px;" >删除</button>
			     </div>
			</div>`)
	  
	  
  }

 }catch(e){
		
	}
	
}
function check(id){
	window.location.href="/SQLgogo/teacher/SubDetailAdd.jsp?Sub="+id 

}
function delete_qes(id,name){
      let qes_id = localStorage.getItem("qesArray").split(',');
      let qes_name = localStorage.getItem("qesName").split(',');
      
      console.log(qes_id,qes_name)
      var del_id = qes_name.indexOf(name) //获取删除位置
      

       layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
               qes_id.splice(del_id,1)
               qes_name.splice(del_id,1)
			   localStorage.setItem("qesName",qes_name);
               localStorage.setItem("qesArray",qes_id);  //自动覆盖,增长
               
               if(qes_id.length==0){
                   localStorage.removeItem("qesName");　
                   localStorage.removeItem("qesArray");　
                   $("#submit").css("display","none");
               }
               console.log(qes_id,qes_name)  //inspect after deleted
      
               let thisURL = document.URL; 
			  setTimeout(function(){ location.href= thisURL });
			});  
}

function submit(){
	let qes_id = localStorage.getItem("qesArray");
	console.log(qes_id)

     var thisURL = document.URL; 
	
	 var getval =thisURL.split('?')[1]; 
     var pra_id = getval.split('&')[0].split('=')[1];
	
	
    layer.confirm('确定要提交吗？', { offset: '280px' },function(index){ 
    	  console.log(qes_id)
    	  $.post("../SubjectServlet?status=add_choiced",{qes_id:qes_id,pra_id:pra_id},function(data){
    		  console.log(data)
    		  if(data =='true'){
    			  layer.msg('提交成功',{ offset: '350px' });
    			  setTimeout(()=>{ window.history.back(-2);},1500);
    			  localStorage.removeItem("qesName");　
                  localStorage.removeItem("qesArray");
    			 
    		  }else{
    			  layer.msg('有题目已经存在于练习！',{ offset: '350px' });
    		  }
  	    	
    	})
    })
}