package service;

import java.util.List;

import bean.ClassInfo;
import bean.PageBean;
import bean.Ques;
import bean.StuUser;
import bean.Subject;
import dao.ClassDao;
import dao.QueDao;
import dao.SubjectDao;

public class subjectAdminService {
	public PageBean<Subject> findAllUserWithPage(int queid,int pageNum,int pageSize){
		
		SubjectDao subDao = new SubjectDao();
		List<Subject> list = subDao.findSubjectByQueid(queid);
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(subDao.findSubjectsOnePage(queid,startIndex,pageSize));
		return pb;
	}
}
