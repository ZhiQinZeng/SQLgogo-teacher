package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassInfo;
import bean.PageBean;
import bean.StuUser;
import dao.ClassDao;
import dao.StuDao;
import net.sf.json.JSONObject;

public class ClassManager extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			//设置请求以及响应的内容类型以及编码方式
			resp.setContentType("text/html;charset=UTF-8");
			
			
			String method = req.getParameter("method");
			
			//得到班级列表
			if("gettable".equals(method)){
				HttpSession session = req.getSession();
				/*if("".equals(session.getAttribute("userId"))){
					resp.getWriter().print("<script>alert('请先登录！')</script>");					
					resp.getWriter().print(
							"<script>window.open('login.jsp','_top')</script>");
				}*/
				int teaid = (int)session.getAttribute("userId");
				int pageNum = Integer.parseInt(req.getParameter("pageNum"));
				int pageSize = 8;
				
				classService sqs = new classService();
				PageBean pb = sqs.findAllUserWithPage(teaid,pageNum, pageSize);
				
				PrintWriter out = resp.getWriter();
				JSONObject json = JSONObject.fromObject(pb);     
				out.print(json.toString());
			}else if (method.endsWith("addClass")) {   //添加班级
			 
			 	String classname = req.getParameter("classname");	
			 	System.out.println(classname+"banji");
				String studentsnum =req.getParameter("studentsnum");
				HttpSession session = req.getSession();
				String teacher = (String)session.getAttribute("realname");
				int teaid = (int)session.getAttribute("userId");
				Date date= new Date();
				SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sd.format(date);
				
				
				//将string类型studentsnum转化成int类型
				 int studentsNum=Integer.parseInt(studentsnum);
				
				ClassInfo classinfo = new ClassInfo();
				ClassDao classdao = new ClassDao();
				
				//班级名称不能重复添加
				if((classdao.isExistClass(classname,teaid))){
					
					resp.getWriter().print("<script>alert('您已经创建过此班级,添加失败！')</script>");
					resp.getWriter().print("<script>history.back()</script>");
					return;
				}else{
					classinfo.setClassName(classname);
					classinfo.setTeacherName(teacher);
					classinfo.setStudentsNumber(studentsNum);
					classinfo.setAddtime(nowtime);
					classinfo.setTeaid(teaid);
					classdao.addClass(classinfo);
				}
			
				resp.sendRedirect("/SQLgogo/teacher/classAdmin.jsp");
			}else if(method.endsWith("updateClass")){
				//upid是靠session传值
				int upid = Integer.parseInt((String)req.getParameter("upid"));		
				String classname = req.getParameter("classname");
				String teacher = (String) req.getSession().getAttribute("realname");
				String studentsnum =req.getParameter("studentsnum");
				int studentsnum2 =Integer.parseInt(studentsnum);
				HttpSession session = req.getSession();
				int teaid = (int)session.getAttribute("userId");
				Date date= new Date();
				SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sd.format(date);
			
				//将string类型studentsnum转化成int类型
				int studentsNum=Integer.parseInt(studentsnum);
										 
				ClassInfo classinfo = new ClassInfo();
				ClassDao classdao = new ClassDao();
								
				classinfo.setClassName(classname);
				classinfo.setTeacherName(teacher);
				classinfo.setStudentsNumber(studentsNum);
				classinfo.setAddtime(nowtime);
				if((classdao.isExistAnotherClass(classname,teaid,studentsnum2,upid))){
					resp.getWriter().print("<script>alert('没有更改信息，更新失败！')</script>");
					resp.getWriter().print("<script>history.back()</script>");
					return;
				}else{
					classdao.updateClass(classinfo,upid);
				}
				resp.sendRedirect("/SQLgogo/teacher/classAdmin.jsp");
			}else if("deleteClass".equals(method)){
				int delid = Integer.parseInt(req.getParameter("delid"));
				ClassDao classdao = new ClassDao();
				StuDao stuDao = new StuDao();
				classdao.deleteClass(delid);   //删掉班级信息
				int res = stuDao.delStuByclassid(delid);
				
			}
			
			/*else if(method.endsWith("updateStudent")){
				//upid是靠session传值
				HttpSession session=req.getSession();
				Object upid=session.getAttribute("upid");
				
				String studentname = req.getParameter("studentname");
				String studentclass = req.getParameter("studentclass");
				String studentsnum =req.getParameter("studentsnum");

				StuDao studao = new StuDao();
				StuUser stuuser = new StuUser();
				
				stuuser.setRealname(studentname);
				stuuser.setUsername(studentsnum);
				stuuser.setClassname(studentclass);
				
				//学号相同的学生不能添加
				//StuUser su = studao.selById(studentsnum);
				
				JSONObject json = new JSONObject();
				json.put("code", 1);
				PrintWriter out =resp.getWriter();
				
				if(su == null){
					studao.addStudent(stuuser);
					out.print(json);
				}else{
					json.put("code", 2);
					out.print(json);
				}
				
			}	
			else if (method.equals("addStudent")) {				 				 
				String studentname = req.getParameter("studentname");
				String studentclass = req.getParameter("studentclass");
				String studentsnum =req.getParameter("studentsnum");				
			
				StuUser stuuser = new StuUser();
				StuDao studao = new StuDao();				
				
				stuuser.setUsername(studentsnum);
				stuuser.setRealname(studentname);
				stuuser.setClassname(studentclass);
				
				//学号相同的学生不能添加
				//StuUser su = studao.selById(studentsnum);
				
				JSONObject json = new JSONObject();
				json.put("code", 1);
				PrintWriter out =resp.getWriter();
				
				if(su == null){
					studao.addStudent(stuuser);
					out.print(json);
				}else{
					json.put("code", 2);
					out.print(json);
				}

			}*/
			
  }
}
