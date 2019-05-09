package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StuUser;
import bean.TeaUser;
import dao.PersonInfoDao;



public class PersonInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				//设置请求以及响应的内容类型以及编码方式
				response.setContentType("text/html;charset=UTF-8");
				
				String phone_number = request.getParameter("phone_number");
				String e_mail = request.getParameter("e_mail");
				String sex = request.getParameter("sex");
				String major = request.getParameter("major");
				String company = request.getParameter("company");
				
				HttpSession session=request.getSession(); 
			    String username = (String)session.getAttribute("username");
			    
			    TeaUser stu = new TeaUser();
			    stu.setMajor(major);
			    stu.setPhone_num(phone_number);
			    stu.setSex(sex);
			    stu.setCompany(company);
			    stu.setE_mail(e_mail);
			    
			    PersonInfoDao perdao = new PersonInfoDao();
			    perdao.saveType(stu,username);
			    
			    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		response.sendRedirect("/SQLgogo/teacher/perInfo.jsp");
	}

}
