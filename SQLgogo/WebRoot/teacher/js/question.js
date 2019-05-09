
var thisURL = document.URL; 

var getval =thisURL.split('?')[1]; 
var user = getval.split("=")[0];
var type  = getval.split("=")[0];
var qestion_id= getval.split("=")[1]; 

var s = 1; //判断评论框收齐或者展开
var a = new Array();  //放评论的数组
var k = 0;  //记录评论数

function loadData() {
	if(type == "teaQes"){
		$.post("../TeaQesServlet?status=find_one_tea",{qestion_id:qestion_id},function(data){
			 data = eval(`(${data})`).one; 
			console.log(data)
			$("#tea_deleted").css("display","none")
			
			 $(".user_name").text(data.studentName);
			let unixTimestamp = new Date( data.addtime );
			let addTime = unixTimestamp.toLocaleString();
			   $(".qes_time").text(addTime);
			   $("#mentions").text(data.concern);
			  $("#qes_protray").text(data.questionProtray);
			  $("#qes_photo").attr("src",data.photo);
			  $(".qes_name").text(data.qesName);
			  if(data.questionDeal!="0"){
				  $("#eval").append(`<div id="comment_demo0" style="display:flex;flex-direction:row;"></div>`);
				  k++;
			  }
		});
	}else{
		$.post("../TeaQesServlet?status=find_one",{qestion_id:qestion_id},function(data){
			 data = eval(`(${data})`).one; 
			console.log(data)
			$("#tea_deleted").css("display","none")
			
			 $(".user_name").text(data.studentName);
			let unixTimestamp = new Date( data.addtime );
			let addTime = unixTimestamp.toLocaleString();
			   $(".qes_time").text(addTime);
			   $("#mentions").text(data.concern);
			  $("#qes_protray").text(data.questionProtray);
			  $("#qes_photo").attr("src",data.filepath);
			  $(".qes_name").text(data.title);
			  if(data.questionDeal!="0"){
				  $("#eval").append(`<div id="comment_demo0" style="display:flex;flex-direction:row;">
							<div class="panel panel-info" style="background-color: #bce8f1;width:510px;margin-left:200px;display:flex;background-color: #bce8f1;align-items: center;height: 32px;">
							<p style="margin-bottom: 0px;margin-left: 20px; color:#676666">${data.questionDeal}</p></div>
							<button type="button" id="delete0"  onclick="deleted(0);"class="btn btn-danger btn-xs" 
								style="display:flex; position:relative;height: 28px;margin-top: 2px;margin-left: 15px;">删除</button></div>`);
				  k++;
			  }
		});

		
	}
	
}
function proto_deleted(){

layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
	$("#pro_comment").css("display","none");
   /* $.post("../QesDealServlet?status=delete",{qes_id:qesId},function(){
    	 console.log("全部删除啊！！！！！！！！！！！")
		});*/
	
  layer.close(index);
  layer.msg('删除成功',{ offset: '350px' })
  //history.go(0);
});  



}
function deleted(id){
var comment = "comment_demo"+id;

layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
	$("#"+comment).remove();
	
     $.post("../QesDealServlet?status=delete",{qes_id:qestion_id},function(data){
     if(data=="true") layer.msg('删除成功',{ offset: '350px' })
		});
    
  layer.close(index);
  
  k--;
});  

}

function tea_deleted(){
var id = tea_qesId;



layer.confirm('确定要删除吗？', { offset: '280px' },function(index){
	
     $.post("../TeaQesServlet?status=tea_delete",{teaqes_id:id},function(){
    	 
		});
  layer.close(index);
  layer.msg('删除成功',{ offset: '350px' })
  setTimeout(function(){ window.history.go(-1); }, 1500);
});  

}


$(document).ready(function() {
var E = window.wangEditor
var editor1 = new E('#div1', '#div2') // 两个参数也可以传入 elem 对象，class 选择器


$("#comment").click(function() {
	if (s % 2 == 1) {
		$("#comment").html("收起");
		$("#submit").css("display", "");
		$("#submit").css("opacity", 0.1);
		$("#submit").animate(
			{
				left : '90px',
				opacity : '1'
			}, "slow");
		s++;
	} else {
		$("#comment").html("回复");
		$("#submit").animate(
			{
				left : "0px",
				opacity : '0',
			}, "slow", function() {
				$("#submit").css("display", "none");
			});

		s++;
	}
});

$("#submit").click(function() {

	var comment2 = editor1.txt.text()
	var ok = {
		comment : comment2,
		index : a.length
	}

	$.post("../QesDealServlet?status=comment", {qesDeal: comment2,qes_id:qestion_id},function(data){
	     if(data=="true")
		   layer.msg('提交成功',{ offset: '350px' })
		});  
	a.push(ok);
	var item = a[a.length - 1];

	
	var id = 'comment_demo' + item.index;
	if (k == 0) {
		$('#eval').css("opacity", "0");
		$("#eval").append(`<div id="comment_demo0" style="display:flex;flex-direction:row;">
		<div class="panel panel-info" style="background-color: #bce8f1;width:510px;margin-left:200px;display:flex;background-color: #bce8f1;align-items: center;height: 32px;">
		<p style="margin-bottom: 0px;margin-left: 20px; color:#676666">${item.comment}</p></div>
		<button type="button" id="delete0"  onclick="deleted(0);"class="btn btn-danger btn-xs" 
			style="display:flex; position:relative;height: 28px;margin-top: 2px;margin-left: 15px;">删除</button></div>`);

		$("#eval").animate(
			{
				opacity : '1'
			}, 1000, function() {
				$("#delete0").css("display", "");
				$("#delete0").css("opacity", '0');
				$("#delete0").animate({
					opacity : '1'
				}, 1000).slideDown("slow");
			});
		k++;
	} else {
		var container = document.createElement('div')
		var id = "comment_demo" + item.index;
		container.id = id;
		$("#eval").append(container)
		$("#" + id).css("display", 'relative');
		$('#' + id).css("opacity", "0");
		$("#" + id).append(`<div class="comment_demo" style="display:flex;flex-direction:row;">
		<div class="panel panel-info" style="background-color: #bce8f1;width:510px;margin-left:200px;background-color: #bce8f1;display: flex;align-items: center;height: 32px;">
		<p style="margin-bottom: 0px;margin-left: 20px;color:#676666">${item.comment}</p></div>
		
		<button id="delete${item.index}"type="button"  onclick="deleted(${item.index});" class="btn btn-danger btn-xs"  style="height: 28px;margin-top: 2px;margin-left: 15px; display:none">删除</button>
		</div>`);
		$("#" + id).animate(
			{
				display : 'relative',
				opacity : '1'
			}, 1000, function() {
				$("#delete" + item.index).css("display", "");
				$("#delete" + item.index).css("opacity", '0');
				$("#delete" + item.index).animate({
					opacity : '1'
				}, 1000).slideDown("slow");
			});
	}
});
editor1.customConfig.menus = [
	'head',
	'bold',
	'italic',
	'underline',
	'emoticon', // 表情
	'code' , // 插入代码
	'undo' // 撤销
]

editor1.create()

});

