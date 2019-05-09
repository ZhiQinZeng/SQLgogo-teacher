   	  function LoadData(){ 
	   		var classid = document.URL.split("?")[1].split("&")[0].split("=")[1];	
	   		var className = decodeURI(document.URL.split("?")[1].split("&")[1].split("=")[1]);
	   		var class_url = decodeURI(document.URL.split("?")[1]);
  			$.ajax({
  				url: "../StudentsServlet?method=getstudents&pageNum=1",
  				type: "post", 
  				dataType:"json",
  				data: {
  					 "classid": classid,					
  				},
  				async:false,
  				success: function(res){	  					
  					data = res.list;
  					
  					$('.zxfinput').val(res.end);
  				    $('#mytotalPage').val(res.totalPage);
  				    $(".zxf_pagediv").html("");  //每次改变时，分页也改变
  				    $(".zxf_pagediv").createPage({		    
	  	  				pageNum:  $('#mytotalPage').val(),
	  	  				current: 1,
  				    });
  					if(data==null || data == ""){
  						$("#mytbody").html("");
  						$("#mytbody").html("<span>此班级没有学生信息！</span>");
  					}
  					else{
  						var html;
	  					for(var i = 0;i<data.length;i++) {
	  						html += '<tr><td>'+ parseInt(i%10+1) +'</td><td>'+ data[i].realname +'</td><td>'+ className +'</td><td>'+ data[i].username 
	  						+'</td><td>' 
	  						+'&nbsp<a href="updateStudent.jsp?id='+data[i].id+'&realname='+ data[i].realname +'&classid='+classid+'&classname='+data[i].classname+'&username='+data[i].username
	  						+'">修改</a>&nbsp<a href="#" onclick="confirm('+data[i].id+')"' 
	  						+'">删除</a>&nbsp<a href="lookStudent.jsp?id='+ data[i].id +'&'+class_url+' ">查看</a></td></tr>';

	  					}
	  					$("#mytbody").html(html);
  					}
  				},
  				error: function(err){
  					try{
  						alert("错了");
  					}catch(e){
  						console.log(e);
  					}
  				}
  			});
  			
  			
  		  	//翻页
  		  	$(".zxf_pagediv").createPage({		    
  		  				pageNum:  $('#mytotalPage').val(),
  		  				current: 1,
  		  				backfun: function(data) {
  		  					
  		  					var pageNum = $(".current").text(); 
  		  					
  		  					$.ajax({
  		  				    	url:"../StudentsServlet?method=getstudents&pageNum="+pageNum,
  		  				    	type:'post',
  		  				    	dataType:"json",
  		  		  				data: {
  		  		  				 "classid": classid,
  		  		  				},
  		  				    	async: false,
  		  				    	success:function(res){
  		  				    		$('.zxfinput').val(res.pageNum);
  		  				    		data = res.list; 					
  		  		  					$('#mytotalPage').val(res.totalPage);
  		  		  					
  		  		  					if($.isEmptyObject(data)){
  		  		  						$("#mytbody").html("");
  		  		  						$("#mytbody").html("<span>此班级没有学生信息！</span>");
  		  		  					}else{
  		  		  					var html;
	  		  	  					for(var i = 0;i<data.length;i++) {
	  		  	  						html += '<tr><td>'+ parseInt(i%10+1) +'</td><td>'+ data[i].realname +'</td><td>'+ className +'</td><td>'+ data[i].username 
	  		  	  						+'</td><td>' 
	  		  	  						+'&nbsp<a href="updateStudent.jsp?id='+data[i].id+'&realname='+ data[i].realname +'&classid='+classid+'&classname='+data[i].classname+'&username='+data[i].username
	  		  	  						+'">修改</a>&nbsp<a href="#" onclick="confirm('+data[i].id+')"' 
	  		  	  						+'">删除</a>&nbsp<a href="lookStudent.jsp?id='+ data[i].id +'&'+class_url+'">查看</a></td></tr>';
	
	  		  	  					}
  		  			  					$("#mytbody").html(html);
  		  		  					}
  		  		  				},
  			  		  			error: function(err){
  			  	  					try{
  			  	  						alert("错了");
  			  	  					}catch(e){
  			  	  						console.log(e);
  			  	  					}
  			  	  				}
  		  					});
  		  				}
  		  			});
  		  		
	  	
	  }
	  
  	
  	function confirm(delid){
  		layer.confirm(
  				'是否确定删除此学生信息？', 
  				{icon: 3, title:'提示'}, 
  				function(index){
  						$.ajax({
  				    	url:'../StudentsServlet?method=deleteStu&delid='+delid,
  				    	type:'get',
  				    	async: false,
  				    	success:function(res){						
  							layer.close(index);		
  							location.reload();
  				    	}
  				});
  				
  						
  		});			
  	}		
		
	