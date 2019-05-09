package service;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;



import bean.StuUser;
import dao.StuDao;


public class SaveDate2DB {
	public static boolean iserror = false;  //判断记录是否重复
    public int save(String path,int classid,String className) throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        StuUser student = null;
        List<StuUser> list = xlsMain.readXls(path);
        StuDao studao = new StuDao();
        int returnvalue = 1;
        for(int i=0;i<list.size();i++){
        	System.out.println(list.get(i).getRealname());
        }
        for (int i = 0; i < list.size(); i++) {
            student = list.get(i);
            List<StuUser> li = studao.selectOne(student);
            if (li == null || li.isEmpty()) {
                studao.addStudent(student,classid,className); 
                returnvalue = 2;    //插入成功，无相同学号
            } else {
            	iserror = true;
            	System.out.println(li.get(0).getUsername()+"此学号学生已经存在，此条记录被抛弃!");
            	returnvalue = 3;  //插入成功，有相同学号
            }
        }
        return returnvalue;
    }
}



