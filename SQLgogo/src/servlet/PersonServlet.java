package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TeaUser;

import dao.UserDao;


public class PersonServlet extends HttpServlet {
	TeaUser teaUser=new TeaUser();
	UserDao userDao=new UserDao();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String action = req.getServletPath();
		
		
		if(action.equals("/Personalize")){
			System.out.println("进来了！");
		}else if(action.equals("/PwdChange")){        //更改密码
			String oldpwd = req.getParameter("oldpwd");//旧密码
			String newpwd = req.getParameter("newpwd");//新密码
			String confirm = req.getParameter("confirm");//确认新密码
			
			String username1=(String)req.getSession().getAttribute("username");
			int username = Integer.parseInt(username1);
			String pwd=(String)req.getSession().getAttribute("password");  //得到该客户当前的字符串
			
			//String MDoldpwd = md5.getMD5(oldpwd);
			try{
				if(oldpwd.equals(pwd)){ //输入的旧密码与原密码一致
					if(newpwd.equals(confirm)){//判断输入的两个新密码是否一致
						if(!(newpwd.equals(pwd))){//如果新密码与原密码不同，执行更新密码操作
							
							TeaUser teauserfind=userDao.findUser(username, pwd);
							teaUser.setPassword(newpwd);
							teaUser.setName(teauserfind.getName());
							teaUser.setCompany(teauserfind.getCompany());
							teaUser.setE_mail(teauserfind.getE_mail());
							teaUser.setMajor(teauserfind.getMajor());
							teaUser.setPhone_num(teauserfind.getPhone_num());
							teaUser.setRealname(teauserfind.getRealname());
							teaUser.setSex(teauserfind.getSex());
							
							userDao.update(teaUser);
							System.out.println("修改成功了");
							resp.getWriter().print("<script>alert('修改成功！正在为您跳转到主页')</script>");
							resp.sendRedirect("/SQLgogo/teacher/login.jsp");
							//exit(request, response);
						}else if(newpwd.equals(pwd)){
							resp.getWriter().print("<script>alert('密码没有改动')</script>");
						}
					}else{//抛出异常
						resp.getWriter().print("<script>alert('抱歉，密码输入不一致')</script>");
					}
				}else{//抛出异常
					resp.getWriter().print("<script>alert('旧密码输入错误')</script>");
					
				}
			}catch(Exception e){
				
				
		}
		}
		
		
	 }
		
	

}
