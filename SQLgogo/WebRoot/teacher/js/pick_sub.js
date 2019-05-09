	function pick(id,name){
		 var thisURL = document.URL; 
			
			var getval =thisURL.split('?')[1]; 
			var pra_id = getval.split('&')[0].split('=')[1];
			var pra_name = getval.split('&')[1].split('=')[1];
		  console.log(id,name)
		  window.location.href="/SQLgogo/teacher/sel_qes.jsp?Pid="+pra_id+"&Sid="+id+"&Sna="+name+"&Pna="+pra_name; 
	}
function loadData(){
	$.get("../QueServlet?method=gettable&pageNum=1",function(data){
		   var a = 1;
	        data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	      
	        
	        for(let item of data.list){
		    	   console.log(item)
		    	   var date = Date.now();
		    	 $("#table").append(`<tr>
                   <td>${item.id}</td>
                   <td>${item.quename}</td>
                   <td>${item.quecontext}</td>
	               <td>${item.thedate}</td>
                 
                   <td><a onclick="pick(${item.id},'${item.quename}')">查看</a></td>
                   </tr>`)		   	   
		     }
	  });	

}
$(document).ready(function(){
	
	
	function pick(id,name){
		  alert(id,name)
		
	}



});