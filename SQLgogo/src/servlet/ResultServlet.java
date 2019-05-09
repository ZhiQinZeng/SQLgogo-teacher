package servlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.PageBean;
import bean.Result;
import bean.StuUser;
import dao.DBDao;
import dao.PracticeDao;
import dao.ResultDao;
import dao.StuDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ResultAdminService;
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

	PracticeDao practiceDao = new PracticeDao();
	DBDao dbDao = new DBDao();
	public void init() throws ServletException {

		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		resp.setContentType("text/html;charset=UTF-8");
		String status = req.getParameter("status");
		if ("getsubjects".equals(status)) {  //测评得到一个库所有问题
			int praid = Integer.parseInt((String)req.getParameter("praid"));
			ResultAdminService ras = new ResultAdminService();
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			int pageSize = 8;
			PageBean pb = ras.findAllSubjectsWithPage(praid, pageNum, pageSize);
			
			JSONObject jsonObject = JSONObject.fromObject(pb);
			jsonObject.put("praid", praid);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonObject);
			
		} else if ("list".equals(status)) {  //测评得到所有库
			HttpSession session = req.getSession();
			int teaid = (int)session.getAttribute("userId");
			//System.out.println("userId"+teaid);
			ResultAdminService ras = new ResultAdminService();
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			int pageSize = 8;
			PageBean pb = ras.findAllUserWithPage(teaid,pageNum, pageSize);
			
			//JSONArray json = new JSONArray(list);
			JSONObject jsonObject = JSONObject.fromObject(pb);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonObject);
			
		}else if ("findResult".equals(status)) {
			int praid = Integer.parseInt((String)req.getParameter("praid"));
			int subid = Integer.parseInt((String)req.getParameter("subid"));
			ResultAdminService ras = new ResultAdminService();
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			int pageSize = 8;
			
			PageBean pb = ras.findAllResultssWithPage(praid, subid, pageNum, pageSize);
			List<StuUser> li = new ArrayList<StuUser>();
			
			
			JSONObject jsonObject = JSONObject.fromObject(pb);
			//jsonObject.put("praid", praid);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonObject);

		}else if ("notsubmit".equals(status)) {   //没有提交作业的
			int classid = Integer.parseInt((String)req.getParameter("classid"));
			int praid = Integer.parseInt((String)req.getParameter("praid"));
			int subid = Integer.parseInt((String)req.getParameter("subid"));
			ResultDao resDao = new ResultDao();
			List list = resDao.findnotsubmit(classid, praid, subid);
			
			
			JSONArray jsonObject = new JSONArray();
			jsonObject.add(list);
			//jsonObject.put("praid", praid);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonObject);

		}
	}

}
