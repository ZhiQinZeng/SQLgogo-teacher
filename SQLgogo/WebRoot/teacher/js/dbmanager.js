function loadData(){
	
	$.ajax({
    	url:'../DbServlet?status=getdbbyteauser&pageNum=1',
    	type:'get',
    	async: false,
    	success:function(res){			
			res = eval(`(${res})`);   //这是将字符串转成对象的语句。
		     totalPage = res.totalPage;
		     $('#mytotalPage').val(res.totalPage);
		     $('.zxfinput').val(res.pageNum);
		      var a = 1;
		     if(res.list == null){
		    	 $("#mytbody").append(`没有库信息，请先准备环境！`);
		     }else{
			     for(let item of res.list){ 
			    	 $("#mytbody").append(`<tr>
		                <td>${a++}</td>
		                <td>${item.dbname}</td>
		                <td>${item.creattime}</td>     
		                <td>
		                <a href="./lookdbtables.jsp?dbname=${item.dbname}">查看</a> &nbsp			               
							<a  href="#" onclick="confirm('${item.dbname}')">删除</a> &nbsp</td>
						</tr>`)		   	   
			     }
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
				console.log($(".current").text());//回调
				var pageNum = $(".current").text(); 
				$.ajax({
			    	url:'../DbServlet?status=getdbbyteauser&pageNum='+pageNum,
			    	type:'get',
			    	async: false,
			    	success:function(res){
			    		res = eval(`(${res})`);   //这是将字符串转成对象的语句。	console.log(res+"2222");
			    		$('.zxfinput').val(res.pageNum);
			    		$("#mytbody").html("");
			    		var a=1;
			    		if(res.list == null){
			    			$("#mytbody").append(`没有库信息，请先准备环境！`);
			    		}else{
				    		for(let item of res.list){			    		
				   	    	 $("#mytbody").append(`<tr>
				                <td>${a++}</td>
				                <td>${item.dbname}</td>
				                <td>${item.creattime}</td>     
				                <td>
				                <a href="./lookdbtables.jsp?dbname=${item.dbname}">查看</a> &nbsp			               
								<a  href="#" onclick="confirm('${item.dbname}')">删除</a> &nbsp</td>
								</tr>`)		   	   
				   	     	}
			    		}
			    	}
				});
			}
		});
	
}
	
function confirm(dbname){
	layer.confirm(
			'是否确定删除?', 
			{icon: 3, title:'提示'}, 
			function(index){
					$.ajax({
			    	url:'../DbServlet?status=deldbtable&dbname='+dbname,
			    	type:'get',
			    	async: false,
			    	success:function(res){						
						layer.close(index);		
						window.open("./dbmanager.jsp");
			    	}
			});
			
					
	});			
}		
		        


