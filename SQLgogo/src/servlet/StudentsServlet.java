package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.StudentsAdminService;
import bean.ClassInfo;
import bean.PageBean;
import bean.StuUser;
import bean.TeaUser;
import dao.ClassDao;
import dao.StuDao;
import dao.UserDao;

public class StudentsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//设置请求以及响应的内容类型以及编码方式
		resp.setContentType("text/html;charset=UTF-8");
		
		String method = req.getParameter("method");
		
		if("getstudents".equals(method)){
			int classid=Integer.parseInt((String)req.getParameter("classid"));
			ClassDao classDao = new ClassDao();
			List<StuUser> seloptli=classDao.findStuByClassid(classid);
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			int pageSize = 8;
			StudentsAdminService sas = new StudentsAdminService();
			PageBean pb = sas.findAllUserWithPage(classid, pageNum, pageSize);
					
			PrintWriter out =resp.getWriter();
			
			JSONObject jsonArray = JSONObject.fromObject(pb);
			String result = jsonArray.toString();
			
			out.write(result);
		}else if("getclasses".equals(method)){
			HttpSession session = req.getSession();
			int teaid = (int)session.getAttribute("userId");
			ClassDao classDao = new ClassDao();
			List<ClassInfo> list = classDao.findAllClasses(teaid);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("res", list);
			PrintWriter out =resp.getWriter();
			out.print(jsonobj);
		}else if("updateStu".equals(method)){   //学号不能有重复
			int stuid = Integer.parseInt((String)req.getParameter("stuid"));
			String studentname = req.getParameter("studentname");
			int selval = Integer.parseInt((String)req.getParameter("selval"));
			String seltext = req.getParameter("seltext");
			String studentsnum =req.getParameter("studentsnum");
			
			StuDao studao = new StuDao();
			if(studao.isExistStu(studentsnum, stuid)){   //如果学号存在
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "2");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}else{  //不存在则进行更新
				StuUser stuuser = new StuUser();
				
				stuuser.setRealname(studentname);
				stuuser.setUsername(studentsnum);
				stuuser.setClassname(seltext);
				stuuser.setClassId(selval);
				
				studao.updatestudent(stuuser, stuid);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "1");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}
			
		}else if("deleteStu".equals(method)){
			StuDao studao = new StuDao();
			int id = Integer.parseInt((String)req.getParameter("delid"));
			studao.deleteStudent(id);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("res", "1");
			PrintWriter out =resp.getWriter();
			out.print(jsonobj);
		}else if("addStu".equals(method)){
			//int stuid = Integer.parseInt((String)req.getParameter("stuid"));
			String studentname = req.getParameter("studentname");
			int selval = Integer.parseInt((String)req.getParameter("selval"));
			String seltext = req.getParameter("seltext");
			String studentsnum =req.getParameter("studentsnum");
			
			StuDao studao = new StuDao();
			if(studao.isExistStu(studentsnum)){   //如果学号存在
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "2");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}else{  //不存在则进行添加
				StuUser stuuser = new StuUser();
				
				stuuser.setRealname(studentname);
				stuuser.setUsername(studentsnum);
				stuuser.setClassname(seltext);
				stuuser.setClassId(selval);
				stuuser.setPassword(studentsnum);
				studao.addstudent(stuuser);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "1");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}
		}else if("lookStu".equals(method)){
			int stuid = Integer.parseInt((String)req.getParameter("stuid"));
			StuDao studao = new StuDao();
			StuUser stu = studao.selByID(stuid);
			
			JSONObject jsonobj =  JSONObject.fromObject(stu);
			PrintWriter out =resp.getWriter();
			out.print(jsonobj);
		}else if("addteacher".equals(method)){
			String teausername = req.getParameter("teausername");
			long teausername2 = Long.parseLong(teausername);
			String teapassword = req.getParameter("teapassword");
			String teaname = req.getParameter("teaname");
			UserDao userDao = new UserDao();
			TeaUser teaUser = userDao.SelectByNameLong(teausername2);
			if(teaUser != null){
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "2");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}else{
				userDao.addTeacher(teausername2, teapassword, teaname);
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("res", "1");
				PrintWriter out =resp.getWriter();
				out.print(jsonobj);
			}
		}
	}
}