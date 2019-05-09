package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import bean.Practice;
import bean.Subject;
import dao.PracticeDao;
import dao.SubjectDao;

/**
 * Servlet implementation class Sub_Qes
 */
@WebServlet("/Sub_Qes")
public class Sub_Qes extends HttpServlet {

	SubjectDao subjectDao = new SubjectDao();

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
			
			String prac_genre = req.getParameter("prac_genre");
			String prac_class = req.getParameter("prac_class");
			String prac_deadline = req.getParameter("prac_deadline");
			System.out.println(prac_deadline);
 
			// System.out.println(prac_class+prac_name+prac_genre);
			Practice p = new Practice();
			p.setClassName(prac_class);
			p.setPracticeName(prac_name);
			p.setPracticeGenre(prac_genre);

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				p.setDeadline(sf.parse(prac_deadline));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			
		} else if ("list".equals(status)) {
			String Sid = req.getParameter("Sid");
			List<Subject> list = subjectDao.find_qes_Subject(Sid);
			
			JSONArray json = new JSONArray(list);
			
			resp.getWriter().write(json.toString());
			resp.getWriter().close();
		}else if ("delete".equals(status)) {
			
			 String prac_id =  req.getParameter("prac_id");
			
			
		}
	}

}
