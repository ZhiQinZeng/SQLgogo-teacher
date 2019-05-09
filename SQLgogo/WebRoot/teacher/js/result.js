
function loadData(){	
	$.ajax({
    	url:'../ResultServlet?status=list&pageNum=1',
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
                <td>${item.practiceName}</td>
                <td>${item.practiceGenre}</td>
                <td>${item.className}</td>
                <td>${item.publishDay}</td>
                <td>${item.deadline}</td>
                <td><a href="./resultlook.jsp?praid=${item.practiceId}&classid=${item.classid}">查看</a></td>
                </tr>`)		   	   
	     }
    	},error:function(err){
			  alert("请先登录哦！");
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
			    	url:'../ResultServlet?status=list&pageNum='+pageNum,
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
			                <td>${item.practiceName}</td>
			                <td>${item.practiceGenre}</td>
			                <td>${item.className}</td>
			                <td>${item.publishDay}</td>
			                <td>${item.deadline}</td>
			                <td><a href="./resultlook.jsp?praid=${item.practiceId}&classid=${item.classid}">查看</a></td>
			                </tr>`)		   	   
				     }
			    	}
				});


			}
		});
	
}
	



