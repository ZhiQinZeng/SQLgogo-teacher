package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import bean.ClassInfo;
import bean.DBInfo;
import bean.PageBean;
import bean.Practice;

import dao.ClassDao;
import dao.DBDao;
import net.sf.json.JSONObject;
import service.DBManagerService;


/**
 * Servlet implementation class DbServlet
 */
@WebServlet("/DbServlet") 
public class DbServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       DBDao dbDao = new DBDao();
       ClassDao classDao =new ClassDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				//设置请求以及响应的内容类型以及编码方式
				response.setContentType("text/html;charset=UTF-8");
				String status = request.getParameter("status");
				
				HttpSession session = request.getSession();
				String teauser = String.valueOf(session.getAttribute("username"));
				
				if("list_db".equals(status)){
					List<DBInfo> list =  dbDao.findDb();
					
					JSONArray json = new JSONArray(list);
					
					response.getWriter().write(json.toString());
					response.getWriter().close();
				}else if("list_class".equals(status)){
					List<ClassInfo> list_class = classDao.findAllList();
					
                     JSONArray json = new JSONArray(list_class);
					
					response.getWriter().write(json.toString());
					response.getWriter().close();
				}else if("getdbbyteauser".equals(status)){
					int pageNum = Integer.parseInt(request.getParameter("pageNum"));
					int pageSize = 8;
					DBManagerService dbservice = new DBManagerService();
					PageBean<DBInfo> pb = dbservice.findAllUserWithPage(pageNum, pageSize, teauser);
					
					JSONObject jsonObject = JSONObject.fromObject(pb);
					PrintWriter pw = response.getWriter();
					pw.print(jsonObject);
				}else if("seldbtables".equals(status)){
					String dbname = request.getParameter("dbname");
					List<String> li = dbDao.findTables(dbname);
				    
					JSONArray jsonArray = new JSONArray(li.toString());
					PrintWriter pw = response.getWriter();
					pw.print(jsonArray);
				}else if("deldbtable".equals(status)){
					String dbname = request.getParameter("dbname");
					int dbinfo_res = dbDao.deldbByTea(dbname,teauser);   //删除dbinfo中的一条库信息
					
					//删除后台库中的一个库
					int db_res = dbDao.deletedb(dbname);
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("dbinfo_res", dbinfo_res);
					jsonObject.put("db_res", db_res);
					PrintWriter pw = response.getWriter();
					pw.print(jsonObject);
					//request.getRequestDispatcher("./dbmanager.jsp");
					
				}
					
		
		
	}

}
