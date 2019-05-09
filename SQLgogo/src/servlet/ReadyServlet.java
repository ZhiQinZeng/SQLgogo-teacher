package servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBDao;
import net.sf.json.JSONObject;

public class ReadyServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//设置请求以及响应的内容类型以及编码方式
		response.setContentType("text/html;charset=UTF-8");
		 
		
		String sqlget = request.getParameter("mytextarea");
		String dataname = request.getParameter("dataname");		
		HttpSession session = request.getSession();
		String teauser = String.valueOf(session.getAttribute("username"));
		
		DBDao dbdao = new DBDao();
		
		
		String dbresult = "database use or create is successful!";   //数据库结果
		String result ="defeat!";  //总的结果
		String tableresult = "table create fail!";  //建表结果
		
		if((dbdao.isExist(dataname, teauser))){    //如果此数据库存在就直接使用它建表，
			//建表语句
			String[] sqlget2 = sqlget.split(";");
	    	String  tableresult2 ;
	    	tableresult = "table create successful!";
	    	for(int i=0;i<sqlget2.length;i++){
	    		tableresult2 = dbdao.createtable(dataname, sqlget2[i]);	    		
	    		if(("tableyes").equals(tableresult2)){   //每次都成功
	    			continue;
	    		}else{
	    			tableresult = tableresult2+"此报错语句后面的sql语句不会被执行！";
	    			result = "defeat!";
	    			break;
	    		}
	    		
	    	}
			//建表结束
		}else{    //不存在创建此数据库，并将数据库数据保存
			dbresult = dbdao.createdb(dataname);
			if(dbresult == "dbyes"){
				dbresult = "database use or create is successful!";
				dbdao.insertdbname(dataname, teauser);
				//建表语句
				String[] sqlget2 = sqlget.split("(;\r\n)|(;\n)");
		    	String  tableresult2 ;
		    	tableresult = "table create successful!";
		    	result = "successfully!";
		    	for(int i=0;i<sqlget2.length;i++){
		    		tableresult2 = dbdao.createtable(dataname, sqlget2[i]);
		    		if(("tableyes").equals(tableresult2)){   //每次都成功
		    			continue;
		    		}else{
		    			tableresult = tableresult2+"此报错语句后面的sql语句不会被执行！";
		    			result = "defeat!";
		    			break;
		    		}
		    		
		    	}
				//建表结束
		    	
			}else{	    	//如果库创建不成功则表也不成功
		    	tableresult = "database result is defeat so that table create failed!";	
		    	result = "defeat!";
		    }
		}
		
	    	
	    JSONObject json = new JSONObject();
		json.put("dbresult", dbresult);
		json.put("tableresult", tableresult);
		json.put("result",result);
		PrintWriter out =response.getWriter();
		out.print(json);
	    
	}
}