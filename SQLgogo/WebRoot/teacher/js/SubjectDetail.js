function loadData() {
	
	var thisURL = document.URL; 
	var getval =thisURL.split('?')[1]; 
	var id= getval.split("=")[1]; 
	
	
		$.post("../SubjectServlet?status=sub_detail",{sub_id: id},function(data){

		         var a = data.split(",")
		         var b =  new Array();
		         var c =  new Array();
		    for(i=0;i<a.length;i++){
		    	b[i] = a[i].split("="); 
		    	c[i] = b[i][1];
		    }
		      console.log(c); //c[0]是detail c[1]是answer c[2]是名字
	
		      var detail = c[0];
		      var answer = c[1];
		      var name = c[2]
		       $("#sub_protray p").text(detail);
		       $("#sub_answer p").text(answer);
		       $("#topic p").text(name);
		});

	}