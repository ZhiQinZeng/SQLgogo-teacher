package servlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;

import bean.DBInfo;
import bean.Practice;
import bean.Ques;
import bean.TeaUser;

import dao.DBDao;

import dao.PracticeDao;
import dao.QueDao;
import dao.UserDao;

public class PracticeServlet extends HttpServlet {

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
		if ("add".equals(status)) {

			String prac_name = req.getParameter("prac_name");
			int result_portion = Integer.parseInt(req.getParameter("result_portion"));
			int process_portion = Integer.parseInt(req.getParameter("process_portion"));
			String prac_genre = req.getParameter("prac_genre");
			String prac_class = req.getParameter("prac_class");
			String prac_deadline = req.getParameter("prac_deadline");
			String db_name = req.getParameter("db_choice");
			int class_id = Integer.parseInt(req.getParameter("class_id"));
		
			
			DBInfo db = dbDao.findOneByName(db_name);

		
			System.out.println(prac_deadline);
 
			
			Practice p = new Practice();
			p.setClassName(prac_class);
			p.setPracticeName(prac_name);
			p.setPracticeGenre(prac_genre);
            p.setResult_portion(result_portion);
            p.setProcess_portion(process_portion);
            p.setQues_id(db.getId());
            p.setDbname(db.getDbname());
            p.setClassid(class_id);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				p.setDeadline(sf.parse(prac_deadline));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			practiceDao.add(p);
		} else if ("list".equals(status)) {
			int page = Integer.parseInt(req.getParameter("page"));
			
			List<Practice> list = practiceDao.findPractice(page);			
			int sum = practiceDao.countPractice();
			JSONArray json = new JSONArray(list);
			JSONObject object = new JSONObject();
			object.put("sum", sum);
			object.put("practice", json.toString());
			resp.getWriter().print(object);
			resp.getWriter().close();
			
		}else if ("findOne".equals(status)) {
			String id = req.getParameter("pra_id");
			Practice one = practiceDao.findOneById(id);		
			JSONObject obj = new JSONObject();
			obj.put("one", one);
			resp.getWriter().print(obj);

		}else if ("delete".equals(status)) {
			
			 String prac_id =  req.getParameter("prac_id");
			
			practiceDao.delete(prac_id);
		}else if ("modify".equals(status)) {

			String prac_name = req.getParameter("prac_name");
			int result_portion = Integer.parseInt(req.getParameter("result_portion"));
			int process_portion = Integer.parseInt(req.getParameter("process_portion"));
			String prac_genre = req.getParameter("prac_genre");
			String prac_class = req.getParameter("prac_class");
			String flag = req.getParameter("flag");
			
			
			String db_name = req.getParameter("db_choice");
			int class_id = Integer.parseInt(req.getParameter("class_id"));
			int prac_id = Integer.parseInt(req.getParameter("prac_id"));
			
			Ques db = dbDao.findOneByDbName(db_name);
			System.out.println(db.getId());
			
			//long date2 = new Date(prac_deadline);
			
 
			Practice p = new Practice();
			if(flag.equals("unchange")){  //未改动
				
				long prac_deadline = Long.parseLong(req.getParameter("submit_time"));  //传过来的是毫秒数
				Timestamp t = new Timestamp(prac_deadline);
				p.setDeadline(t);
				
			}else{  //改动过，老方法
				String prac_deadline2 = req.getParameter("submit_time");  //传过来的是字符串
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					p.setDeadline(sf.parse(prac_deadline2));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			p.setClassName(prac_class);
			p.setPracticeName(prac_name);
			p.setPracticeGenre(prac_genre);
            p.setResult_portion(result_portion);
            p.setProcess_portion(process_portion);
            p.setQues_id(db.getId());
            p.setDbname(db.getQuename());
            p.setClassid(class_id);
            p.setPracticeId(prac_id);
           
		

			practiceDao.modify(p);
		}
	}

}
