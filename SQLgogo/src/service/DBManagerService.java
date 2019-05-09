package service;

import java.util.List;

import bean.DBInfo;
import bean.PageBean;
import dao.DBDao;


public class DBManagerService {
	public PageBean<DBInfo> findAllUserWithPage(int pageNum,int pageSize,String teauser){
		DBDao dbDao = new DBDao();		
		List<DBInfo> list = dbDao.findDBBytea(teauser);
		int totalRecord = list.size();
		PageBean pb = new PageBean(pageNum,pageSize,totalRecord);
		int startIndex = pb.getStartIndex();
		//有startIndex和pageSize就可以拿到每页的数据
		pb.setList(dbDao.findOnePage(teauser,startIndex,pageSize));
		return pb;
	}
}
