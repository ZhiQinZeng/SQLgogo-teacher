
function loadData(){
	
	$.ajax({
    	url:'../ClassManager?method=gettable&pageNum=1',
    	type:'get',
    	async: false,
    	success:function(res){			
			res = eval(`(${res})`);   //这是将字符串转成对象的语句。
		     totalPage = res.totalPage;
		     $('#mytotalPage').val(res.totalPage);
		     $('.zxfinput').val(res.pageNum);
		     console.log(res.pageNum+"当前");
		      console.log(res.list+"list");
		      if(res.list == null){
		    	  $("#mytbody").append(`没有班级信息！请先创建！`);
		      }
		      else{
			     for(let item of res.list){
			    
			    	 $("#mytbody").append(`<tr>
		                
		                <td>${item.className}</td>
		                <td>${item.teacherName}</td>
		                <td>${item.studentsNumber}</td>
		                <td>${item.addtime}</td>
		                <td> &nbsp
							<a href="./updateClass.jsp?upid=${item.id}&className=${item.className}&stuNum=${item.studentsNumber}" >修改</a> &nbsp
							<a href="#" onclick="confirm(${item.id})">删除</a> &nbsp
							<a href="./studentsAdmin.jsp?classid=${item.id}&className=${item.className}" >查看</a></td>	                
		                </tr>`)		   	   
			     
			     }
		     }
    	},error:function(err){
			  alert("请先登录哦！");
		 }
	});
	
	
	//翻页
	$(".zxf_pagediv").createPage({		    
			pageNum: $('#mytotalPage').val(),
			current: 1,
			backfun: function(data) {
				console.log($(".current").text());//回调
				var pageNum = $(".current").text(); 
				$.ajax({
			    	url:'../ClassManager?method=gettable&pageNum='+pageNum,
			    	type:'get',
			    	async: false,
			    	success:function(res){
			    		res = eval(`(${res})`);   //这是将字符串转成对象的语句。			    		console.log(res+"2222");
			    		$('.zxfinput').val(res.pageNum);
			    		$("#mytbody").html("");
			    		for(let item of res.list){			    		
			    			 $("#mytbody").append(`<tr>
			 		                
		 		                <td>${item.className}</td>
		 		                <td>${item.teacherName}</td>
		 		                <td>${item.studentsNumber}</td>
		 		                <td>${item.addtime}</td>
		 		                <td> &nbsp
		 							<a href="./updateClass.jsp?upid=${item.id}&className=${item.className}&stuNum=${item.studentsNumber}" >修改</a> &nbsp
		 							<a href="#" onclick="confirm(${item.id})">删除</a> &nbsp
		 							<a href="./studentsAdmin.jsp?classid=${item.id}&className=${item.className}" >查看</a></td>	                
		 		                </tr>`)		   	   
			   	     }
			    	}
				});
			}
		});
	}
  
function confirm(delid){
	layer.confirm(
			'是否确定删除此班级以及班级内所有学生信息?', 
			{icon: 3, title:'提示'}, 
			function(index){
					$.ajax({
			    	url:'../ClassManager?method=deleteClass&delid='+delid,
			    	type:'get',
			    	async: false,
			    	success:function(res){						
						layer.close(index);		
						window.open("./classAdmin.jsp");
			    	}
			});
			
					
	});			
}		
		        


