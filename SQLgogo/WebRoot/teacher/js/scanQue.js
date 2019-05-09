
function loadData(){	
	$.ajax({
    	url:'../QueServlet?method=gettable&pageNum=1',
    	type:'get',
    	async: false,
    	success:function(res){			
			res = eval(`(${res})`);   //这是将字符串转成对象的语句。
		     totalPage = res.totalPage;
		     $('#mytotalPage').val(res.totalPage);
		     $('.zxfinput').val(res.pageNum);
		      var a = 1;
		     if(res.list == null){
		    	 $("#mytbody").append(`没有题库信息，请先新建题库！`);
		     }else{
			     for(let item of res.list){ 
			    	 $("#mytbody").append(`<tr>
		                <td>${a++}</td>
		                <td>${item.quename}</td>
		                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:280px; white-space:nowrap;display:block;">${item.quecontext}</div></td>
		                <td>${item.thedate}</td>        
		                <td><a href="./addQue.jsp">添加</a> &nbsp
							<a href="./updateQue.jsp?upid=${item.id}&quename=${item.quename}&quecontext=${item.quecontext}" >修改</a> &nbsp
							<a  href="#" onclick="delconfirm('${item.id}')">删除</a> &nbsp
							<a href="./lookQue.jsp?lookid=${item.id}">进入</a> &nbsp</td>
		                </tr>`)		   	   
			     }
		     }
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
			    	url:'../QueServlet?method=gettable&pageNum='+pageNum,
			    	type:'get',
			    	async: false,
			    	success:function(res){
			    		res = eval(`(${res})`);   //这是将字符串转成对象的语句。	console.log(res+"2222");
			    		$('.zxfinput').val(res.pageNum);
			    		$("#mytbody").html("");
			    		var a=1;
			    		if(res.list == null){
			    			$("#mytbody").append(`没有题库信息，请先新建题库！`);
			    		}else{
				    		for(let item of res.list){ 
					    	 $("#mytbody").append(`<tr>
				                <td>${a++}</td>
				                <td>${item.quename}</td>
				                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:280px; white-space:nowrap;display:block;">${item.quecontext}</div></td>
				                <td>${item.thedate}</td>        
				                <td><a href="./addQue.jsp">添加</a> &nbsp
									<a href="./updateQue.jsp?upid=${item.id}&quename=${item.quename}&quecontext=${item.quecontext}" >修改</a> &nbsp
									<a  href="#" onclick="delconfirm('${item.id}')">删除</a> &nbsp
									<a href="./lookQue.jsp?lookid=${item.id}">进入</a> &nbsp</td>
				                </tr>`)		   	   
				    		}
			    		}
			    	}
				});
			}
		});

}
				                   
function delconfirm(delid){
	layer.confirm(
			'是否确定删除?', 
			{icon: 3, title:'提示'}, 
			function(index){
					$.ajax({
			    	url:'../QueManager?method=deleteQue&delid='+delid,
			    	type:'get',
			    	async: false,
			    	success:function(res){						
						layer.close(index);	
						layer.msg("删除成功！");	
						window.open("./scanQue.jsp");
			    	}
			});
			
					
	});			
}		