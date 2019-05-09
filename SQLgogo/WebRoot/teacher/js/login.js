

function confirm(){
	
	let user = $("#username").val();
	let pwd = $("#pwd").val();
	
	if(user==""){
		layer.msg('用户名不能为空！',{ offset: '350px' })
		return;
	}else if(pwd==""){
		layer.msg('密码不能为空！',{ offset: '350px' })
		return;
	}
	
	$.post("../Login",{username:user,password:pwd},function(data){	
		
		if(data=="none"){
			layer.msg('账号密码错误',{ offset: '350px' })
		}else{
			layer.msg('登录成功',{ offset: '350px' })
			setTimeout(()=>{window.location.href="/SQLgogo/teacher/main.jsp"},1000)
		}
   
	})
	
}
