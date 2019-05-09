package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import bean.StuUser;
import util.DBUtil;

public class StuDao {
	//根据id查找
	public StuUser selByID(int id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from stuuser where id = ?";
		Object[] params = {id};
		
		try { 
			StuUser stu = (StuUser)queryRunner.query(connection, sql,new BeanHandler(StuUser.class),params);
			return stu;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
		
	public void addStudent(StuUser stuuser,int classid,String className) {
		
		//添加
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "insert into stuuser(ClassId,username,realname,classname,password) values(?,?,?,?,?)";
		Object[] params = {classid,stuuser.getUsername(),stuuser.getRealname(),className,stuuser.getUsername()};
		
		 /* BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数*/
		 
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
		//删除学生，根据学生的id进行删除
	public void deleteStudent(int id){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from stuuser where id = ?";
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
	public void updatestudent(StuUser stuuser,int id){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update stuuser set ClassId=?,realname=?,classname=?,username=? where id=?";
		Object[] params = {stuuser.getClassId(),stuuser.getRealname(),stuuser.getClassname(),stuuser.getUsername(),id};
		
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
	//添加学生
		public void addstudent(StuUser stuuser){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "insert into stuuser  (ClassId,password,realname,classname,username) values(?,?,?,?,?) ";
			Object[] params = {stuuser.getClassId(),stuuser.getPassword(),stuuser.getRealname(),stuuser.getClassname(),stuuser.getUsername()};
			
			try { 
				queryRunner.update(connection, sql,params);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
		}
	
	//判断导入excel学生数据的时候，学号有没有重复
	public List<StuUser> selectOne(StuUser stuuser) throws SQLException{
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from stuuser where username = ?";
		Object[] params = {stuuser.getUsername()};
		
		try { 
			List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),params);
			if(!(list == null)){
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
}
		//查找所有班级
	public List<StuUser> findClass(String name,String realname){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select stuuser.Id,className,teacherName,studentsNumber,addtime from StuUser join teauser on stuuser.teacherName=teauser.realname where  name=? and teacherName=?";
		Object[] params = {name,realname};
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
	
	
	public List<StuUser> getClass(int start,int number){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from stuuser limit ?,?";
		Object[] pramas = {start,number};
		
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),pramas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	//根据学号查询是否有另外的此学号学生,跟新使用
	public boolean isExistStu(String stuusername,int stuid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from stuuser where username=? and id <> ?";
		Object[] pramas = {stuusername,stuid};
		
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),pramas);
			if(list.size() >= 1){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return false;
	}
	
	//根据学号查询是否有另外的此学号学生,添加使用
		public boolean isExistStu(String stuusername){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "select * from stuuser where username=?";
			Object[] pramas = {stuusername};
			
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				List<StuUser> list = (List<StuUser>)queryRunner.query(connection, sql,new BeanListHandler(StuUser.class),pramas);
				if(list.size() >= 1){
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return false;
		}
		public StuUser selById(String upid){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "select * from stuuser where username=?";
			Object[] pramas = {upid};
			
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				StuUser stu = (StuUser)queryRunner.query(connection, sql,new BeanHandler(StuUser.class),pramas);
				return stu;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		public StuUser selByRealId(String upid){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "select * from stuuser where id = ?";
			Object[] pramas = {upid};
			
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				StuUser stu = (StuUser)queryRunner.query(connection, sql,new BeanHandler(StuUser.class),pramas);
				return stu;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		
	
	
	//根据学生的班级id进行删除
	public int delStuByclassid(int classid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from stuuser where Classid = ?";
		Object[] params = {classid};
		
		try { 
			int res = queryRunner.update(connection, sql,params);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return 0;
	}
}
