package servlet;

import java.io.IOException;

import java.util.List;

import javax.enterprise.inject.New;
//import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.alibaba.fastjson.JSONObject;

import bean.Practice;
import bean.Question;
import bean.TeaUser;
import bean.teaQuestion;
import dao.QuestionDao;
import dao.StuDao;
import dao.TeaQesDao;
import dao.UserDao;



@WebServlet("/TeaQesServlet")
public class TeaQesServlet extends HttpServlet {
	TeaQesDao  teaQesDao  = new TeaQesDao(); 
	
	UserDao userDao= new UserDao();
	StuDao stuDao = new StuDao();
    public TeaQesServlet() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html;charset=UTF-8");
		 HttpSession session=req.getSession();
		String status = req.getParameter("status");
		

		if ("list".equals(status)) {
			
//		
//				int userId  = (int) session.getAttribute("userId");
//				TeaUser teaUser =  userDao.SelectById(userId);
//				
//				String realname =teaUser.getRealname();
//				String photo = (String) session.getAttribute("userphoto");
//				
//				List<teaQuestion> list = teaQesDao.findQes(userId);
//				
//			JSONArray json = new JSONArray(list);
//			JSONObject object = new JSONObject();
//			object.put("photo",photo);
//			object.put("list",json.toString());
//            
//			resp.getWriter().print(object);
//			resp.getWriter().close();
		}else if ("add".equals(status)){
			String qes_name = req.getParameter("qes_name");
			String qes_protray = req.getParameter("qes_protray");
			
			 int userId  = (int) session.getAttribute("userId");
			 String realname = userDao.SelectById(userId).getRealname();
			teaQesDao.add(qes_name,qes_protray,userId,realname);
		}else if ("tea_delete".equals(status)){
			String teaqes_id = req.getParameter("teaqes_id");
	       
			teaQesDao.deleted(teaqes_id);
		}else if ("find_one".equals(status)){
			String qestion_id = req.getParameter("qestion_id");
			
			
			   Question question = teaQesDao.find_one(qestion_id);
			   String idString = String.valueOf(question.getStudentsNumber());
			   String user_name =  stuDao.selByRealId(idString).getRealname();
			   String profile =  "../../SQLgogo2/student/"+(String)stuDao.selByRealId(idString).getProfile();
			   
			   question.setStudentName(user_name);
			   question.setFilepath(profile);
			 JSONObject object = new JSONObject();
			 object.put("one",question);
			
			resp.getWriter().print(object);
			resp.getWriter().close();
		}else if ("find_one_tea".equals(status)){
			String qestion_id = req.getParameter("qestion_id");
			
			
			   teaQuestion teaquestion = teaQesDao.find_one_tea(qestion_id);
			   long idString = teaquestion.getTeaNumber();
			   
			   String profile =  (String)userDao.SelectById(idString).getProfile();
			   
			   teaquestion.setPhoto(profile);
			 JSONObject object = new JSONObject();
			 object.put("one",teaquestion);
			
			 resp.getWriter().print(object);
			 resp.getWriter().close();
		}else if ("find_page".equals(status)){
			int userId  = (int) session.getAttribute("userId");
			int page = Integer.parseInt(req.getParameter("page"));
					
			String photo = (String) session.getAttribute("userphoto");
			int sum = teaQesDao.countQes(userId);
			List<teaQuestion> list = teaQesDao.findQes(userId,page);
			
		
			
		JSONArray json = new JSONArray(list);
		JSONObject object = new JSONObject();
		object.put("photo",photo);
		object.put("question_part",json.toString());
		object.put("sum", sum);
		resp.getWriter().print(object);
		resp.getWriter().close();
	}
	}

}
