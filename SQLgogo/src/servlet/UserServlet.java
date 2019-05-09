package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import bean.TeaUser;

import dao.UserDao;
import util.Utils;

public class UserServlet extends HttpServlet {
	

	UserDao userDao=null;
	public void init() throws ServletException {

			userDao=new UserDao();
		super.init();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//设置请求以及响应的内容类型以及编码方式
		resp.setContentType("text/html;charset=UTF-8");
		
		
		String action = req.getServletPath();
		try {
			if (action.equals("/Login")) {

				long username = Long.parseLong(req.getParameter("username"));
				String password = req.getParameter("password");
	
				TeaUser teauser=userDao.findUser(username, password);
							
				if (teauser!=null) {
					// 成功
					String realname=teauser.getRealname();	
					String userphoto=(String) teauser.getProfile();	
					int userId= teauser.getId();	
					HttpSession session=req.getSession();
					session.setMaxInactiveInterval(24*3600);//以秒为单位
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					session.setAttribute("realname",realname);	
					session.setAttribute("userphoto",userphoto);
					session.setAttribute("userId",userId);
					resp.sendRedirect("/SQLgogo/teacher/main.jsp");
				} else {
					resp.getWriter().print("none");
				}
			 }else if(action.equals("/check")){
				    
				 HttpSession session=req.getSession();
				 if(session.getAttribute("username")!=null){
					long username = 	(long) session.getAttribute("username");
					 String userphoto =  (String) session.getAttribute("userphoto");
					 String realname=	(String) session.getAttribute("realname");
					 int userId  = (int) session.getAttribute("userId");
						  JSONObject obj = new JSONObject();

						  	obj.put("username", username);
							obj.put("realname", realname);
							obj.put("userphoto", userphoto);
							obj.put("userId", userId);
							resp.getWriter().print(obj);
				 }else {
					 resp.getWriter().print("null");
				 }
		 
			 }else if(action.equals("/log_out")){ 
				 HttpSession session=req.getSession();
				 session.invalidate();
				 resp.sendRedirect("/SQLgogo/teacher/login.jsp");
			 }
		}catch (Exception e) {
					e.printStackTrace(System.out);
					resp.getWriter().print("<script>alert('内部错误')</script>");
					
					resp.getWriter().print(
							"<script>history.back()</script>");
		}
	}
	
	//一种密码加密算法，先保留
	private String md5(String message) {
		//System.out.println("Utils.md5(message)是："+Utils.md5(message));
		return Utils.md5(message);

	}
	
	
}
