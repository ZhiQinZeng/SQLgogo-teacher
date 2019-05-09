function loadData(){	
	var lookid = document.URL.split("?")[1].split("=")[1];
	$.ajax({
    	url:'../QueManager?method=lookQue&lookid='+lookid+'&pageNum=1',
    	type:'get',
    	async: false,
    	success:function(res){			
			res = eval(`(${res})`);   //这是将字符串转成对象的语句。
		     totalPage = res.totalPage;
		     $('#mytotalPage').val(res.totalPage);
		     $('.zxfinput').val(res.pageNum);
		     $("#mytbody").html("");
		      var a = 1;
		     if(res.list == null){
		    	 $("#mytbody").append(`没有题目信息，请先新建题库！`);
		     }else{
			     for(let item of res.list){ 
			    	 $("#mytbody").append(`<tr>
		                <td>${a++}</td>
		                <td>${item.subjectName}</td>
		                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:220px; white-space:nowrap;display:block;">${item.subject_detail}</div></td>
		                <td>${item.subject_answer}</td>
		                <td>${item.set_score}</td>
		                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:200px; white-space:nowrap;display:block;">${item.key_choice}</div></td>
		                <td><a href="./AddSubject_que.jsp?addid=${res.queid}&lookid=${lookid}">添加</a> &nbsp
						<a href="./updateSubject.jsp?subject_answer=${item.subject_answer}&upid=${item.subjectId}&subjectName=${item.subjectName}&subject_detail=${item.subject_detail}&set_score=${item.set_score}&key_choice=${item.key_choice}&lookid=${lookid}" >修改</a> &nbsp
							<a  href="#" onclick="delconfirm('${item.subjectId}')">删除</a> &nbsp
							
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
			    	url:'../QueManager?method=lookQue&lookid='+lookid+'&pageNum='+pageNum,
			    	type:'get',
			    	async: false,
			    	success:function(res){			
						res = eval(`(${res})`);   //这是将字符串转成对象的语句。
					     totalPage = res.totalPage;
					     $('#mytotalPage').val(res.totalPage);
					     $('.zxfinput').val(res.pageNum);
					     $("#mytbody").html("");
					      var a = 1;
					     if(res.list == null){
					    	 $("#mytbody").append(`没有题目信息，请先新建题库！`);
					     }else{
						     for(let item of res.list){ 
						    	 $("#mytbody").append(`<tr>
					                <td>${a++}</td>
					                <td>${item.subjectName}</td>
					                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:220px; white-space:nowrap;display:block;">${item.subject_detail}</div></td>
					                <td>${item.subject_answer}</td>
					                <td>${item.set_score}</td>
					                <td><div style="overflow: hidden;-o-text-overflow: ellipsis; text-overflow: ellipsis;width:200px; white-space:nowrap;display:block;">${item.key_choice}</div></td>
					                <td><a href="./AddSubject_que.jsp?addid=${res.queid}&lookid=${lookid}">添加</a> &nbsp
									<a href="./updateSubject.jsp?subject_answer=${item.subject_answer}&upid=${item.subjectId}&subjectName=${item.subjectName}&subject_detail=${item.subject_detail}&set_score=${item.set_score}&key_choice=${item.key_choice}" >修改</a> &nbsp
										<a  href="#" onclick="delconfirm('${item.subjectId}')">删除</a> &nbsp
										
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
			    	url:'../QueManager?method=deleteSubject&delid='+delid,
			    	type:'get',
			    	async: false,
			    	success:function(res){						
						layer.close(index);	
						layer.msg("删除成功！");	
						location.reload();
			    	}
			});
			
					
	});			
}		