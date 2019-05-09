package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Question;
import dao.QuestionDao;


public class QesDealServlet extends HttpServlet {
	QuestionDao questionDao=null;
	public void init() throws ServletException {
			questionDao=new QuestionDao();
		    super.init();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html;charset=UTF-8");
		String status = req.getParameter("status");
		String qesDeal = req.getParameter("qesDeal");
		String qes_id_com = req.getParameter("qes_id");
		
		
		if ("comment".equals(status)) {
		
			questionDao.update(qes_id_com, qesDeal);  
			resp.getWriter().write("true");
		}else if ("delete".equals(status)) {
			
			 String qes_id =  req.getParameter("qes_id");
			
			 questionDao.delete_comment(qes_id);
			 resp.getWriter().write("true");
		}else if ("star".equals(status)) {
			
			 String star =  req.getParameter("star");
			 String qes_id =  req.getParameter("qes_id");
			questionDao.star(star,qes_id);
		}
		
	}

}
