function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
        } else {
        return false;
        }
    }



function exit(){
			if(window.confirm("确认要退出吗？")){
				window.open('login.jsp','_top')
			}else{
				return false;
			}
        }
        
        function textFocus(){
        var text = $("#textarea").val();
        if(text == "描述一下你要发布的试题"){
             $("#textarea").val("");
             $("#textarea").css("color","black");
             }
        }
        
        function textFocus2(){
         var text2 = $("#textarea2").val();
        if(text2 == "请输入试题答案"){
             $("#textarea2").val("");
             $("#textarea2").css("color","black");
             }
        }
        
        

	function submit(){
	
	    var  sub_name = $("#sub_name").val();  
        var  sub_protray = $("#textarea").val();
        var  sub_answer = $("#textarea2").val();
        var self_score =  $("#self_score").val();
        var subid =  $("#subid").val();
        var keys = "";
	    if(sub_name == ""){
	    	layer.msg('请输入题目名称！',{ offset: '350px' })
	    	return;
	    }else if(sub_protray=="描述一下你要发布的试题"){
	    	layer.msg('请输入试题描述！',{ offset: '350px' })
	    	return;
	    }else if(sub_answer=="请输入试题答案"){
	    	layer.msg('请输入试题答案！',{ offset: '350px' })
	    	return;
	    }else if(sub_answer=="请输入试题答案"){
	    	layer.msg('请输入试题答案！',{ offset: '350px' })
	    	return;
	    }else if(!isNumber(self_score)){
	    	layer.msg('分数输入有误！',{ offset: '350px' })
	    	return;
	    }
	   
	    
	    var likes = $("input[type='checkbox']:checked").each(function(){
	    	keys+=$(this).val()+',';
		 });
	    console.log(likes)
	    $.post("../SubjectServlet?status=addSubject",{subid:subid,sub_name:sub_name,sub_protray:sub_protray,sub_answer:sub_answer,keys:keys,total:self_score},function(data){
	    	layer.msg('提交成功',{ offset: '350px' })
	    	setTimeout(function(){ window.history.go(-1); }, 1500);
	});
	      
	}