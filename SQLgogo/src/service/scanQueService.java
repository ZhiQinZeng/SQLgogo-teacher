package service;

import java.util.List;

import bean.PageBean;
import bean.Ques;
import dao.QueDao;

public class scanQueService {
	public PageBean<Ques> findAllUserWithPage(int pageNum,int pageSize){
		QueDao queDao = new QueDao();		
		List<Ques> list = queDao.findQues();
		
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(queDao.findOnePage(startIndex,pageSize));
		return pb;
	}
}
