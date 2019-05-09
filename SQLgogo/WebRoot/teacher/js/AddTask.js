        
	function submit(){
	    var  qes_name = $("#qes_name").val();  
	    var  qes_protray= editor.txt.text()
	    console.log(qes_protray)
	    
	    
	    if(qes_name==""){
	    	layer.msg('请输入问题名称！',{ offset: '350px' })
	    	return;
	    }
	    if(qes_protray=="给学生的问题内容编辑"){
	    	layer.msg('请编辑问题内容！',{ offset: '350px' })
	    	return;
	    }
	    $.post("../TeaQesServlet?status=add",{qes_name:qes_name,qes_protray:qes_protray},function(data){
	    	layer.msg('提交成功',{ offset: '350px' })
	    	
	    	setTimeout(function(){ window.location.href="/SQLgogo/teacher/TeacherQes.jsp"  }, 1500);
	    });

	}
	
