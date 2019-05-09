package servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import bean.JsonReader;
import bean.Practice;
import bean.Question;
import dao.QuestionDao;
import dao.StuDao;

import net.sf.json.JSONObject;


public class QuestionServlet extends HttpServlet {
	QuestionDao questionDao=null;
	StuDao stuDao = new StuDao();
	public void init() throws ServletException {
			questionDao=new QuestionDao();
		    super.init();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html;charset=UTF-8");

		String status = req.getParameter("status");
		
		//String studentName = req.getParameter("studentName");
		
		
		if ("list".equals(status)) {
			List<Question> list = questionDao.allQuestion();
			
			//JSONArray json = new JSONArray(list);  
			//resp.getWriter().write(json.toString());  暂时关掉全部查询
			//resp.getWriter().close();
		}else if("find".equals(status)){
			
			
			    int page = Integer.parseInt(req.getParameter("page"));
			    List<Question> question_part = questionDao.findByPage(page);
			    for(int  i=0;i<question_part.size();i++)    {
					 String idString = String.valueOf(question_part.get(i).getStudentsNumber());	
				      String name =  stuDao.selByRealId(idString).getRealname();
				      String profile =  (String)stuDao.selByRealId(idString).getProfile();
					
				      question_part.get(i).setStudentName(name);
				      question_part.get(i).setFilepath(profile);
				   }   
			    int sum = questionDao.count();
			    JSONObject obj = new JSONObject();

	    		obj.put("question_part", JSONArray.toJSON(question_part));
	    		obj.put("sum", sum);
				resp.getWriter().print(obj);
				resp.getWriter().close();
		}else if("new".equals(status)){
			
			    int page = Integer.parseInt(req.getParameter("page"));
			    List<Question> question_part = questionDao.findByDate(page);
			    for(int  i=0;i<question_part.size();i++)    {
					 String idString = String.valueOf(question_part.get(i).getStudentsNumber());	
				      String name =  stuDao.selByRealId(idString).getRealname();
				      String profile =  (String)stuDao.selByRealId(idString).getProfile();
					
				      question_part.get(i).setStudentName(name);
				      question_part.get(i).setFilepath(profile);
				   } 
			    int sum = questionDao.count();
			    JSONObject obj = new JSONObject();

	    		obj.put("question_part", JSONArray.toJSON(question_part));
	    		obj.put("sum", sum);
				resp.getWriter().print(obj);
				resp.getWriter().close();
		}else if("star".equals(status)){
			
		    int page = Integer.parseInt(req.getParameter("page"));
		    List<Question> question_part = questionDao.findByStar(page);
		    for(int  i=0;i<question_part.size();i++)    {
				 String idString = String.valueOf(question_part.get(i).getStudentsNumber());	
			      String name =  stuDao.selByRealId(idString).getRealname();
			      String profile =  (String)stuDao.selByRealId(idString).getProfile();
				
			      question_part.get(i).setStudentName(name);
			      question_part.get(i).setFilepath(profile);
			   } 
		    int sum = questionDao.countStar();
		    JSONObject obj = new JSONObject();

    		obj.put("question_part", JSONArray.toJSON(question_part));
    		obj.put("sum", sum);
			resp.getWriter().print(obj);
			resp.getWriter().close();
	}else if("answer".equals(status)){
		
	    int page = Integer.parseInt(req.getParameter("page"));
	    List<Question> question_part = questionDao.findByAnswer(page);
	    for(int  i=0;i<question_part.size();i++)    {
			 String idString = String.valueOf(question_part.get(i).getStudentsNumber());	
		      String name =  stuDao.selByRealId(idString).getRealname();
		      String profile =  (String)stuDao.selByRealId(idString).getProfile();
			
		      question_part.get(i).setStudentName(name);
		      question_part.get(i).setFilepath(profile);
		   } 
	    int sum = questionDao.countAnswer();
	    JSONObject obj = new JSONObject();

		obj.put("question_part", JSONArray.toJSON(question_part));
		obj.put("sum", sum);
		resp.getWriter().print(obj);
		resp.getWriter().close();
}
	}
	
	

}
