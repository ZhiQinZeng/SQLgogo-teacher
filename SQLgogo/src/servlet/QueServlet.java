package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PageBean;
import bean.Ques;
import dao.ClassDao;
import dao.QueDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.scanQueService;
import sun.rmi.server.Dispatcher;

public class QueServlet extends HttpServlet {
	String addid;
	String upid;
	String delid;
	String method;
	String lookid;
	String searchName;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//设置请求以及响应的内容类型以及编码方式
		response.setContentType("text/html;charset=UTF-8");
		
		 addid = request.getParameter("addid");
		 upid = request.getParameter("upid");
		 delid = request.getParameter("delid");
		 lookid = request.getParameter("lookid");
		 method = request.getParameter("method");
		 searchName  = request.getParameter("searchName");
		 
		 if(!(searchName==null)){
			 String idString = request.getParameter("ques_id");
			 int id = Integer.parseInt(idString);
			 
			 QueDao queDao = new QueDao();
			 Ques okQues =  queDao.findQueById(id);
			 PrintWriter out = response.getWriter();
				
			 out.print(okQues.getQuename());
		 }
		 
		 
		if(!(method==null)){
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int pageSize = 8;
			scanQueService sqs = new scanQueService();
			PageBean pb = sqs.findAllUserWithPage(pageNum, pageSize);
			List<Ques> li = pb.getList();
			
			PrintWriter out = response.getWriter();
			JSONObject json = JSONObject.fromObject(pb);     
			out.print(json.toString());
		}else if(!(addid==null)){
			add(request,response);
		}else if(!(upid==null)){
			update(request,response);
		}else if(!(delid==null)){
			delete(request,response);
		}else if(!(lookid==null)){
			look(request,response);
		}
		
		
	}
	
	private void look(HttpServletRequest request, HttpServletResponse response) {
		QueDao queDao = new QueDao();
		int parupid = Integer.parseInt(lookid);
		Ques describe = queDao.findQueById(parupid);
		
		HttpSession session=request.getSession();
		session.setAttribute("queupid", upid);
		
		request.setAttribute("quename", describe.getQuename());
	    request.setAttribute("quecontext", describe.getQuecontext());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher/lookQue.jsp");
		
		try {
			try {
				dispatcher.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("/SQLgogo/teacher/addQue.jsp");
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	/*	QueDao queDao = new QueDao();
		int parupid = Integer.parseInt(upid);
		Ques describe = queDao.findQueById(parupid);
		
		HttpSession session=request.getSession();
		session.setAttribute("queupid", upid);
		
		request.setAttribute("quename", describe.getQuename());
	    request.setAttribute("quecontext", describe.getQuecontext());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher/updateQue.jsp");
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		/*QueDao queDao = new QueDao();
		//queDao.deleteQue(delid);
		
		response.sendRedirect("/SQLgogo/teacher/scanQue.jsp");*/
	}

}
