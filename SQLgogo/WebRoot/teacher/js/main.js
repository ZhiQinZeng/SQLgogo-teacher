var page = 1;

function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
        } else {
        return false;
        }
    }
function bind_star(){
	 $(".store").click(function(){
	    	
	    	let a = $("img", $(this)).attr("src");
	    	let id = $("img", $(this)).attr("id").split("star")[1];
	    	
	    	if(a=="icone/store.png"){
				$("img", $(this)).attr("src", "icone/store_fill.png");
				
				layer.msg('收藏成功',{ offset: '350px' })
				
				$.post("../QesDealServlet?status=star", {star:1,qes_id:id},function(){
		  		  console.log("收藏成了！！！！！！")
		  		});  
	    	}else{
	    		
	    		
	    		$("img", $(this)).attr("src", "icone/store.png");
	    		layer.msg('取消收藏',{ offset: '350px' })
	    		
	    		$.post("../QesDealServlet?status=star", {star:0,qes_id:id},function(){
		  		  console.log("取消成了！！！！！！")
		  		});  
	    		
	    	}
				
			})           	
}
function render(item,star){
		let hostport=document.location.host;
		
		let unixTimestamp = new Date( item.addtime.time )
		let addTime = unixTimestamp.toLocaleString();
		$("#qes_part").append(` 
				 <div class="qes_demo" id="qes_deom${item.questionId}">
			      <div id="user" class="user">
			        <div id="user_pic" class="user_pic">
			        <a href="/SQLgogo/teacher/question.jsp?Qes=${item.questionId}">
			          <img class="img-circle" src='http://${hostport}${item.filepath}' style="width:80px; height: 80px;">
			        </a>
			        </div>
			        <p class="user_name">${item.studentName}</p>
			      </div>

			      <div id="user_qes" class="user_qes">
			        <div class="qes_name">${item.title}</div>
			        <div class="qes_time">${addTime}</div>
			        <div class="qes_state">关注量：<span >${item.concern}</span></div>
			        <div class="store" title="点击收藏" style="width:min-content" ><img id="star${item.questionId}" src="icone/${star}" ></div>
			      </div>
			    </div>`)
			  
	}
	
	
	function loadData() {
       
		$.post("../Question?status=find",{page:page},function(data){
			 let sum = eval(`(${data})`).sum  
			 sum = Math.ceil(sum/4);
			 $("#page_count").text(sum);
			 
		    data = eval(`(${data})`).question_part;   //这是将字符串转成对象的语句。
		     console.log(data);
		    
             for(item of data){    //render
                  
                   let star = '';
		       		if(item.star==1){
			    		 star = 'store_fill.png';
				    	
				     }else{
				    	 star = 'store.png';			    	
				     }
			    	  render(item,star)
			    }
			    
			  bind_star();
		});
          $("#page").val(page)
	}

function doQes(qes){
		$(".qes_part").empty();
		 for(item of qes){
			 let star = '';
	       		if(item.star==1){
		    		 star = 'store_fill.png';
			    	
			     }else{
			    	 star = 'store.png';			    	
			     }
		    	  render(item,star)
		 }
		 bind_star();
		$("#page").val(page)
	}
	
	
function skip(){
	  let num = $("#page").val()
	  if(num>$("#page_count").text()){
	     layer.msg('页数输入有误！',{ offset: '350px' })
	     return;
	  }else if(!isNumber(num)){
	     layer.msg('页数输入有误！',{ offset: '350px' })
	     return;
	  }else if(num == 0){
	    layer.msg('页数输入有误！',{ offset: '350px' })
	    return;
	  }else{
	    $.post("../Question?status=find",{page:num},function(data){
	                    let question = eval(`(${data})`).question_part;
	                    doQes(question);
	        })
	  }
	  page = parseInt(num);
}
	 $(document).ready(function(){
	        $("#pre").bind("click",function(){
	        if(page == 1){
	           layer.msg('已经是第一页!',{ offset: '350px' })
	           return;
	        }
	                   page -= 1;
	                   $.post("../Question?status=find",{page:page},function(data){
	                    let question = eval(`(${data})`).question_part;
	                    doQes(question);
	          })
	               
	        })
	     
	         $("#next").bind("click",function(){
	         
	               page += 1;
	               console.log(page)
	          $.post("../Question?status=find",{page:page},function(data){
	        	       let question  = eval(`(${data})`).question_part; 
	                   if(question.length==0){
	                    layer.msg('没有更多数据了!',{ offset: '350px' })
	                    page-=1;
	                   return;
	                   }else{     
	                    doQes(question);
	                    }
	          })  
	        })
	        
	       

	 })    
	
	
	