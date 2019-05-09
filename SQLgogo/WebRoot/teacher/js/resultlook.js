
function loadData(){
	
	var praid = document.URL.split('?')[1].split("&")[0].split("=")[1];
	var classid = document.URL.split('?')[1].split("&")[1].split("=")[1];
	$.ajax({
    	url:'../ResultServlet?status=getsubjects&pageNum=1&praid='+praid,
    	type:'get',
    	async: false,
    	success:function(data){		
	      data = eval(`(${data})`);   //这是将字符串转成对象的语句。
	      totalPage = data.totalPage;
	      $('#mytotalPage').val(data.totalPage);
		  $('.zxfinput').val(data.pageNum);
		  $("#mytbody").html("");
		  
	     for(let item of data.list){	    	 
	    	 $("#table").append(`<tr>
                <td>${item.subjectName}</td>
                <td>${item.setpro_score}</td>
                <td>${item.setres_score}</td>
                <td>${item.set_score}</td>
                
                <td><a href="./sturesultlook.jsp?praid=${data.praid}&subid=${item.subjectId}&classid=${classid}">查看</a></td>
                </tr>`)		   	   
	     }
    	}
	});

	
	/*var totalPage = 4;*/
	//翻页
	$(".zxf_pagediv").createPage({		    
			pageNum: $('#mytotalPage').val(),
			current: 1,
			backfun: function(data) {
				
				var pageNum = $(".current").text(); 
				$.ajax({
			    	url:'../ResultServlet?status=getsubjects&pageNum='+pageNum+'&praid='+praid,
			    	type:'get',
			    	async: false,
			    	success:function(data){	
				
				      data = eval(`(${data})`);   //这是将字符串转成对象的语句。
				      totalPage = data.totalPage;
				      $('#mytotalPage').val(data.totalPage);
					  $('.zxfinput').val(data.pageNum);
					  $("#mytbody").html("");
				     for(let item of data.list){	    	 
				    	 $("#table").append(`<tr>
			                <td>${item.subjectName}</td>
			                <td>${item.setpro_score}</td>
			                <td>${item.setres_score}</td>
			                <td>${item.set_score}</td>
			                
			                <td><a href="./resultlook.jsp?praid=${data.praid}&subid=${item.subjectId}&classid=${classid}">查看</a></td>
			                </tr>`)		   	   
				     }
			    	}
				});


			}
		});
	
}
	


