package service;

import java.util.List;

import bean.ClassInfo;
import bean.PageBean;
import bean.Ques;
import bean.StuUser;
import dao.ClassDao;
import dao.QueDao;

public class StudentsAdminService {
	public PageBean<StuUser> findAllUserWithPage(int classid,int pageNum,int pageSize){
		
		ClassDao classDao=new ClassDao();
		ClassInfo classInfo=new ClassInfo();
		//先拿到所有数据，得到总条数
		List<StuUser> list=classDao.findStuByClassid(classid);
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(classDao.findStudentsOnePage(classid,startIndex,pageSize));
		return pb;
	}
}
