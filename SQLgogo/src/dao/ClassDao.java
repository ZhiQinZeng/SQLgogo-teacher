package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.ClassInfo;
import bean.Ques;
import bean.StuUser;
import bean.TeaUser;

import util.DBUtil;

public class ClassDao {
	
	public void addClass(ClassInfo classInfo) {
		
		//添加
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "insert into classinfo(className,teacherName,studentsNumber,addtime,teaid) values(?,?,?,?,?)";
		Object[] params = {classInfo.getClassName(),classInfo.getTeacherName(),classInfo.getStudentsNumber(),classInfo.getAddtime(),classInfo.getTeaid()};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
		//删除
	public void deleteClass(int id){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from classinfo where Id = ?";
		Object[] params = {id};
		
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
		//修改
	public void updateClass(ClassInfo classinfo,int upid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update classinfo set className=?,teacherName=?,studentsNumber=?,addtime=? where Id=?";
		Object[] params = {classinfo.getClassName(),classinfo.getTeacherName(),classinfo.getStudentsNumber(),classinfo.getAddtime(),upid};
		
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
		//查找所有班级
	public List<ClassInfo> findClass(int userid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		//根据
		String sql = "select * from classinfo  where  teaid=?";
		Object[] params = {userid};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	//根据班级名称和班级id查询所在其班级的所有学生
	public List<StuUser> findStuByClassid(int classid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select * from stuuser where  ClassId=?";
		Object[] params = {classid};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
	
	public List<ClassInfo> getClass(int start,int number){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from classinfo limit ?,?";
		Object[] pramas = {start,number};
		
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),pramas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
//	查询数据的总条数
	public int getCount() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "select count(id) from emp";
		
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			long count = (long)queryRunner.query(connection, sql,new ScalarHandler());
			return (int) count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return 0;
	}
	
	//添加班级时判断所添加的班级是否存在
	public boolean isExistClass(String classname,int teaid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "select className from classinfo where className=? and teaid=?";
		Object[] params = {classname,teaid};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try {
			
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),params);
			
			if((list.size()>0)){
				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return false;
		
	}
	
	//更新班级时判断所添加的班级是否存在
		public boolean isExistAnotherClass(String classname,int teaid,int stusnum,int upid){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
//			                                     start number
			String sql = "select * from classinfo where className=? and teaid=? and studentsNumber = ? and Id <> ?";
			Object[] params = {classname,teaid,stusnum,upid};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 * ScalarHandler: 处理单个值，比如记录的个数
			 */
			try {
				
				List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),params);
				
				if((list.size()>=1)){
					
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return false;
			
		}

	public List findOnePage(int userid, int startIndex, int pageSize) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select * from classinfo  where  teaid=? limit ?,?";
		Object[] params = {userid,startIndex,pageSize};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}

	public ClassInfo findQueById(int upid2) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select * from classinfo where id=?";
		Object[] params = {upid2};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			ClassInfo describe = (ClassInfo)queryRunner.query(connection, sql,new BeanHandler(ClassInfo.class),params);
			return describe;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	//学生管理找寻一个班
	public List<StuUser> findStudentsOnePage(int classid, int startIndex, int pageSize) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select * from stuuser where  ClassId=? limit ?,?";
		Object[] params = {classid,startIndex,pageSize};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public List<ClassInfo> findAllList() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from classinfo ";
		//Object[] params = {username,password};
		
		try {
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class));
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	//学生管理更新中得到所有的班级
	public List<ClassInfo> findAllClasses(int teaid) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select Id,className from classinfo where teaid=?";
		Object[] params = {teaid};
		
		try {
			List<ClassInfo> list = (List<ClassInfo>)queryRunner.query(connection, sql,new BeanListHandler(ClassInfo.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
}
