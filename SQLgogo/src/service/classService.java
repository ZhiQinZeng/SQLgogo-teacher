package service;

import java.util.List;

import bean.ClassInfo;
import bean.PageBean;
import bean.Ques;
import dao.ClassDao;
import dao.QueDao;

public class classService {
	public PageBean<ClassInfo> findAllUserWithPage(int teaid,int pageNum,int pageSize){
		
		ClassDao classDao=new ClassDao();
		ClassInfo classInfo=new ClassInfo();
		List<ClassInfo> list=classDao.findClass(teaid);
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(classDao.findOnePage(teaid,startIndex,pageSize));
		return pb;
	}
}
