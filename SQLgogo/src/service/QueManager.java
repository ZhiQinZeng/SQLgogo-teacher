package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PageBean;
import bean.Ques;
import bean.StuUser;
import bean.Subject;
import dao.QueDao;
import dao.StuDao;
import dao.SubjectDao;
import net.sf.json.JSONObject;

public class QueManager extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				//设置请求以及响应的内容类型以及编码方式
				resp.setContentType("text/html;charset=UTF-8");
				
			//	System.out.println("coming");
				String method = req.getParameter("method");
				
				
				 if ("addQue".equals(method)) {
				 
					 String quename = req.getParameter("quename");
					 String quecontext =req.getParameter("quecontext");
						
					Date date= new Date();
					SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowtime = sd.format(date);
				
					//Ques ques = new Ques();
					  QueDao queDao = new QueDao();
					
					queDao.addQue(quename, quecontext, nowtime);
						
					PrintWriter out = resp.getWriter();
					JSONObject json = new JSONObject();
					json.put("res",1);
					out.print(json);
				}else if("updateQue".equals(method)){
					int upid = Integer.parseInt((String)req.getParameter("myid"));
					
					String quename = req.getParameter("quename");
					String quecontext =req.getParameter("quecontext");
				
					Date date= new Date();
					SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowtime = sd.format(date);
			
								
					  Ques ques = new Ques();
					  QueDao queDao = new QueDao();
					  ques.setQuename(quename);
					  ques.setQuecontext(quecontext);
					  ques.setThedate(nowtime);
												 
					  queDao.updateQue(ques,upid);
					  PrintWriter out = resp.getWriter();
					  JSONObject json = new JSONObject();
					  json.put("res",1);
					  out.print(json);
					
				}else if("deleteQue".equals(method)){
					int delid = Integer.parseInt((String)req.getParameter("delid"));
					QueDao queDao = new QueDao();
					queDao.deleteQue(delid);
				}
				else if("lookQue".equals(method)){
					int queid = Integer.parseInt((String)req.getParameter("lookid"));
					int pageNum = Integer.parseInt((String)req.getParameter("pageNum")); 
					SubjectDao subDao = new SubjectDao();
					subjectAdminService sas = new subjectAdminService();
					int pageSize = 8;
					PageBean pb =  sas.findAllUserWithPage(queid, pageNum, pageSize);
										
					PrintWriter out = resp.getWriter();
					JSONObject json = JSONObject.fromObject(pb);
					//JSONObject json = new JSONObject();
			
					 json.put("queid", queid);
					 out.print(json);
				}else if("deleteSubject".equals(method)){
					int delid = Integer.parseInt((String)req.getParameter("delid"));
					SubjectDao subDao = new SubjectDao();
					subDao.delBysubid(delid);
				}
				
		
	}

  }

