package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;


import bean.Subject;
import dao.SubjectDao;

public class SubjectServlet extends HttpServlet {

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
		
		if("look".equals(status)){
			
			
			String prac_Id = req.getParameter("prac_id");
			
			List<Subject> list = subjectDao.findSubjectByPraId(prac_Id);
			
			JSONArray json = new JSONArray(list);
			System.out.println(json);
			resp.getWriter().write(json.toString());
			resp.getWriter().close();
			
			
		}else if ("delete".equals(status)) {
			
			 String sub_id =  req.getParameter("sub_id");
			 String pra_id =  req.getParameter("pra_id");
			  subjectDao.delete(sub_id,pra_id);
		}else if ("sub_detail".equals(status)) {
			String sub_id = req.getParameter("sub_id");
			Subject subject = subjectDao.findOneSubject(sub_id);		
			
			resp.getWriter().write(subject.toString());
			resp.getWriter().close();
		}else if ("add".equals(status)) {
			String sub_name = req.getParameter("sub_name");
			String sub_protray = req.getParameter("sub_protray");
			String sub_answer = req.getParameter("sub_answer");
			String prac_id = req.getParameter("prac_id");
			String keys = req.getParameter("keys");
			int total = Integer.parseInt(req.getParameter("total"));
			subjectDao.add(sub_name, sub_protray, sub_answer, prac_id,keys,total);

			
		}else if ("add_choiced".equals(status)) {
		
			String qes_id = req.getParameter("qes_id");
			String pra_id = req.getParameter("pra_id");
			String[] sub_id = qes_id.split(",");
			System.out.println(pra_id);
			
			for(int i = 0;i<sub_id.length;i++){
				
				if(subjectDao.check_id(pra_id, sub_id[i])){
					
					
					System.out.println(sub_id[i]);
					subjectDao.add_pra_sub(pra_id, sub_id[i]);
					
					resp.getWriter().write("true");
					resp.getWriter().close();
				}else{
					
					boolean a = subjectDao.check_id(pra_id, sub_id[i]);

					resp.getWriter().write("false");
					resp.getWriter().close();
				}
				   
			}
			
		}else if ("all_delete".equals(status)) {
			
			String prac_id = req.getParameter("prac_id");
			
			subjectDao.all_delete(prac_id);
		}else if ("updateBysubid".equals(status)) {
			int subid = Integer.parseInt((String)req.getParameter("subid"));
			String sub_name = req.getParameter("sub_name");
			String sub_protray = req.getParameter("sub_protray");
			String sub_answer = req.getParameter("sub_answer");
			String prac_id = req.getParameter("prac_id");
			String keys = req.getParameter("keys");
			int total = Integer.parseInt(req.getParameter("total"));
			subjectDao.updateBysubid(subid, sub_name, sub_protray, sub_answer, prac_id, keys, total);
		}else if("addSubject".equals(status)){
			int subid = Integer.parseInt((String)req.getParameter("subid"));
			String sub_name = req.getParameter("sub_name");
			String sub_protray = req.getParameter("sub_protray");
			String sub_answer = req.getParameter("sub_answer");
			String prac_id = req.getParameter("prac_id");
			String keys = req.getParameter("keys");
			int total = Integer.parseInt(req.getParameter("total"));
			subjectDao.addSubject(subid,sub_name, sub_protray, sub_answer, prac_id,keys,total);

		}
	}

}
