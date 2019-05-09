var page = 1;
var photo;
function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
        } else {
        return false;
        }
    }

function render(item,photo){
    console.log(item);
	let unixTimestamp = new Date( item.addtime.time )
	let addTime = unixTimestamp.toLocaleString();
	$(".qes_part").append(` 
            <div class="qes_demo" id="tea_deom${item.teaQuestionId}">
					<div id="user" class="user">
						<div id="user_pic" class="user_pic">
							<a href="/SQLgogo/teacher/question.jsp?teaQes=${item.teaQuestionId}"> <img class="img-circle" src="${photo}"
								style="width:80px; height: 80px;">
							</a>
						</div>
						<p class="user_name">${item.teaName}</p>
					</div>
					<div id="user_qes" class="user_qes">
						<div class="qes_name">${item.qesName}</div>
						<div class="qes_time">${item.addtime}</div>
						<div class="qes_state">
							关注量：<span id="mentions">${item.concern}</span>
						</div>
					</div>
				</div>`)
		  
}


function loadData() {
	 
		$.post("../TeaQesServlet?status=find_page",{page:page},function(data){
	
			data = eval(`(${data})`); 
			
			 photo = data.photo;
			 
			let sum = data.sum;
			sum = Math.ceil(sum/4);
			 $("#page_count").text(sum);
			 
			 
			data = eval(`(${data.question_part})`);   //这是将字符串转成对象的语句。
			
              for(item of data){ 
            	  
                  render(item,photo)
               
              }      	
		      
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
	    	  render(item,star,photo)
	 }
	 //bind_star();
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
    $.post("../TeaQesServlet?status=find_page",{page:num},function(data){
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
                   $.post("../TeaQesServlet?status=find_page",{page:page},function(data){
                    let question = eval(`(${data})`).question_part;
                    doQes(question);
          })
               
        })
     
         $("#next").bind("click",function(){

               page += 1;
               console.log(page)
          $.post("../TeaQesServlet?status=find_page",{page:page},function(data){
        	  
        	       let question  = eval(`(${data})`).question_part; 
        	        question = eval(`(${question})`);
        	       console.log(question)
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


