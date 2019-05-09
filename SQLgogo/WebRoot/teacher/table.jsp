<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSON"%>
<%@page import="dao.SubjectDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
    <title>统计表</title>
    <link rel="stylesheet" href="css/bootstrap4alpha.css"/>
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="css/buttons.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="css/shCore.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <style type="text/css" class="init">
        .right{
            font-size: 25px;
        }
        #light{
            width: 527px;
            float: left;
        }
        #Blight{
            background-color: #c8e5bc;
            float: right;
            
        }
        #example > thead > tr > th {
            text-align: center;
        }
/*        #example_info{ display:none;}*/
    </style>
    <script type="text/javascript" language="javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script>
    <script type="text/javascript" language="javascript" src="js/dataTables.bootstrap4.js"></script>
    <script type="text/javascript" language="javascript" src="js/dataTables.buttons.js"></script>
    <script type="text/javascript" language="javascript" src="js/buttons.bootstrap4.js"></script>
    <script type="text/javascript" src="js/jszip.js"></script>
    <script type="text/javascript" language="javascript" src="js/buttons.html5.js"></script>
    
    <script type="text/javascript" src="layui-v2.4.3/layui/layui.all.js"></script>
    <script src="layui-v2.4.3/layui/layui.js" charset="utf-8"></script>

</head>
<body class="dt-example dt-example-bootstrap4">
	<% 
 	 String tab = (String)request.getParameter("tabname");
  	String db = (String)request.getParameter("dbname");
  	SubjectDao subjectDao = new SubjectDao();
    List<Object[]> datali = subjectDao.findDatas(db, tab);
    List<Object[]> typeli = subjectDao.findTypes(db, tab);
    
  	%>

<div class="container">
    <section>
        <div class="panel panel-default">
            <div class="panel-body">
            <!--<input type="checkbox" value= "0" onclick="columnClick(this);"/>
            <input type="checkbox" value= "1" onclick="columnClick(this);"/>
            <input type="checkbox" value= "2" onclick="columnClick(this);"/>
            <input type="checkbox" value= "3" onclick="columnClick(this);"/>
            <input type="checkbox" value= "4" onclick="columnClick(this);"/>
            <input type="text" id="id1" value="" />-->
	<table name="type" class="layui-table">
   <font size="4"><%=tab %>表字段属性：</font>
    <tr>
    	<td>字段名</td>
    	<td>字段类型</td>
    	<td>字段注释</td>
    <tr>
   <%  //集合的遍历
		for(Object[] objs:typeli){  %>
		   <tr>
	<% 	//遍历对象数组
			 for(Object obj:objs){ %>
				
					<td><%=obj %></td> 									
		
	<%		}  %>
			</tr>
			
		<% 		}
	%>
   </table>
    <font size="4">表格数据：</font>
                <table id="example" class="table table-bordered table-striped table-hover">
                    <thead>
                            
                     <tr id="table_tr"></tr> 
                    </thead>
					<tbody>
					
	
  	 <%     	 	
   //字段名称集合的遍历
		List<Object> arr = new ArrayList();
           			 
           			for(Object[] objs:typeli){           
           				 arr.add(objs[0]);
           	 		} 
		%> 
  	
					
	 <%  //表格数据集合的遍历
		for(Object[] objs:datali){ %>    
		   <tr>
	 <% 	//遍历对象数组
			 for(Object obj:objs){ %> 				    
					<td><%=obj %></td> 											
	<%		}  %>
			</tr>			
		<% 		}
	%>    
	
				
				
				</tbody>
                </table>
            </div>
        </div>
    </section>
</div>

</body>

<script type="text/javascript" language="javascript" class="init">
    	var dataTable;
        $(document).ready(function() {
           	
                	var str = "<%=arr%>";
                	str = str.replace("[","");
                	str = str.replace("]","");
                	//str = str.substr(1);
                	
                	var arr2 = new Array();
                	arr2 = str.split(",");
                	var arr3 = [];
                	for(var a=0;a<arr2.length;a++){
                		arr3[a] = {"data":arr2[a]+""};
                	}
                	
                	var columName = arr3;
					
					console.log(columName.length);
			        for(var i =0;i<columName.length; i++){
			        	$("#example thead tr").append("<th>"+columName[i].data+"</th>");
			        }
			        var table = $('#example').DataTable( {
		                ordering: false,//是否启用排序
		                searching: true,//搜索
		                language: {
		                    lengthMenu: '显示 <select style="height: 35px;width: 100px">'+
		                    '<option value="5">5</option>'+
		                    '<option value="10">10</option>'+
		                    '<option value="20">20</option>'+
		                    '<option value="30">30</option>'+
		                    '<option value="40">40</option>'+
		                    '<option value="50">50</option>'+
		                    '<option value="-1">不限</option>'+
		                    '</select>',
		                    search: '<span class="label label-success">搜索：</span>',//右上角的搜索文本，可以写html标签
		
		                    paginate: {//分页的样式内容。
		                        previous: "上一页",
		                        next: "下一页",
		                        first: "第一页",
		                        last: "最后"
		                    },
		                    zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
		                    //下面三者构成了总体的左下角的内容。
		                    info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
		                    infoEmpty: "0条记录",//筛选为空时左下角的显示。
		                    infoFiltered: ""//筛选之后的左下角筛选提示，
		                },
		                paging:true,
		                pagingType: "full_numbers",//分页样式的类型
		                data: dataTable,
		                lengthChange: true,
		                columns:columName,
//						dom: 'Bfltip',
		                'dom': '<"left"f>r<"right"<"#light"l><"Blight"B>>tip',
//		                'excel', 'pdf', 'print','colvis','csv','copy'
		                buttons: [ 'excel'],
		                createdRow : function ( row, data, index ) {
		                    $('td', row).css('font-weight',"bold").css("text-align","center");
		                },
		
		          } );
//			    },
//         	}); 
	//	var date = getUrlParamById('date');
      //  var pay_time = JSON.stringify(date);
        //alert(pay_time);
        } );
    </script>
</html>