
function loadData(){
	var praid = $('#praid').val();
	var subid = $('#subid').val();
	var classid = $('#classid').val();
	$.ajax({
    	url:'../ResultServlet?status=findResult&pageNum=1&praid='+praid+'&subid='+subid,
    	type:'get',
    	async: false,
    	success:function(data){		
	      data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	      totalPage = data.totalPage;
	      $('#mytotalPage').val(data.totalPage);
		  $('.zxfinput').val(data.pageNum);
		  $("#mytbody").html("");
		  console.log(data.list+"1111");
		  var pro_score = 0;
		  var res_score = 0;
		  var total_score = 0;
	    for(let item of data.list){
	    	var update = trans(item[4]);
	    	pro_score += item[0]; 
	    	res_score += item[1];
	    	total_score += item[2];
	    	 $("#table").append(`<tr>
                <td>${item[5]}</td>
                <td>${item[6]}</td>
                <td>${update}</td>
                <td>${item[0]}</td>
                <td>${item[1]}</td>
                <td>${item[2]}</td>
                <td>${item[3]}</td>                               
                </tr>`)		   	   
	     }
	    var jun_pro_score = pro_score / data.list.length;
	    var jun_res_score = res_score / data.list.length;
	    var jun_total_score = total_score / data.list.length;
	    $('#pro_score').val(jun_pro_score);
	    $('#res_score').val(jun_res_score);
	    $('#total_score').val(jun_total_score);
	    
    	},
    	error:function(){
    		alert("访问此练习结果出错了!");
    	}
		 
	});
	//日期转化
	function trans(date){
        var year = date.year + 1900;
		var month = (date.month + 1)>=10 ? (date.month + 1):"0"+(date.month + 1);
		var day = date.date>=10 ? date.date:"0"+date.date;
		var hours = date.hours>=10 ? date.hours:"0"+date.hours;
		var minutes = date.minutes>=10 ? date.minutes:"0"+date.minutes;
		var seconds = date.seconds>=10 ? date.seconds:"0"+date.seconds;
		return ""+year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	}
	
	/*var totalPage = 4;*/
	//翻页
	$(".zxf_pagediv").createPage({		    
			pageNum: $('#mytotalPage').val(),
			current: 1,
			backfun: function(data) {
				
				var pageNum = $(".current").text(); 
				$.ajax({
					url:'../ResultServlet?status=findResult&pageNum='+pageNum+'&praid='+praid+'&subid='+subid,
			    	type:'get',
			    	async: false,
			    	success:function(data){		
			  	      data = eval(`(${data})`);   //这是将字符串转成对象的语句。
			  	      totalPage = data.totalPage;
			  	      $('#mytotalPage').val(data.totalPage);
			  		  $('.zxfinput').val(data.pageNum);
			  		  $("#mytbody").html("");
			  		  console.log(data.list+"1111");
			  		  var pro_score = 0;
			  		  var res_score = 0;
			  		  var total_score = 0;
			  	    for(let item of data.list){
			  	    	var update = trans(item[4]);
			  	    	pro_score += item[0]; 
			  	    	res_score += item[1];
			  	    	total_score += item[2];
			  	    	 $("#table").append(`<tr>
			                  <td>${item[5]}</td>
			                  <td>${item[6]}</td>
			                  <td>${update}</td>
			                  <td>${item[0]}</td>
			                  <td>${item[1]}</td>
			                  <td>${item[2]}</td>
			                  <td>${item[3]}</td>                               
			                  </tr>`)		   	   
			  	     }
			  	    var jun_pro_score = pro_score / data.list.length;
			  	    var jun_res_score = res_score / data.list.length;
			  	    var jun_total_score = total_score / data.list.length;
			  	    $('#pro_score').val(jun_pro_score);
			  	    $('#res_score').val(jun_res_score);
			  	    $('#total_score').val(jun_total_score);
			  	    
			      	}
				});


			}
		});
	
	//得到未交作业人的信息
	$.ajax({
    	url:'../ResultServlet?status=notsubmit&praid='+praid+'&subid='+subid+'&classid='+classid,
    	type:'get',
    	async: false,
    	success:function(data){	
    		 data = eval(`(${data})`);   //这是将字符串转成对象的语句。
    		 var name="";
    		 for(let item of data){
    			 name += item;
    		 }
    		 $('#donot_submit').val(name);
    		 
    	},
    	error:function(){
    		alert("访问未提交人名出错了!");
    	}
	});
	
}
	


