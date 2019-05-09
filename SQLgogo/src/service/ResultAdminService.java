package service;

import java.util.List;

import bean.ClassInfo;
import bean.PageBean;
import bean.Practice;
import bean.PracticeResult;
import bean.Ques;
import bean.Result;
import bean.StuUser;
import bean.Subject;
import dao.ClassDao;
import dao.PracticeDao;
import dao.QueDao;
import dao.ResultDao;
import dao.SubjectDao;

public class ResultAdminService {
	public PageBean<PracticeResult> findAllUserWithPage(int teaid,int pageNum,int pageSize){
		PracticeDao practiceDao = new PracticeDao();
		List<PracticeResult> list = practiceDao.findPracticeResult(teaid);
		//先拿到所有数据，得到总条数
		
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(practiceDao.findPracticeOnePage(teaid,startIndex,pageSize));
		return pb;
	}
	
	public PageBean<Subject> findAllSubjectsWithPage(int praid,int pageNum,int pageSize){
		SubjectDao subDao = new SubjectDao();
		List<Subject> list = subDao.findSubjectBypraid(praid);
		//先拿到所有数据，得到总条数
		
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(subDao.findSubjectResultOnePage(praid,startIndex,pageSize));
		return pb;
	}

	public PageBean findAllResultssWithPage(int praid, int subid, int pageNum, int pageSize) {
		ResultDao rd= new ResultDao();
		List<Result> list = rd.findResultByDou(praid, subid);
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(rd.findResultOnePage(praid,subid,startIndex,pageSize));
		
		return pb;
	}
}
