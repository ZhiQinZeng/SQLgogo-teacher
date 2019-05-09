package dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.mysql.jdbc.Statement;

import bean.DBInfo;
import bean.Practice;
import bean.Ques;
import util.DBUtil;
import util.DBUtils;

public class DBDao {
	//创建库
	public String createdb(String dbname){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql1 = "create database "+dbname;
		
		
		try { 
			queryRunner.update(connection, sql1);
			
		} catch (SQLException se) {			
			   StringWriter sw = new StringWriter();  
			   PrintWriter pw = new PrintWriter(sw);  
			   se.printStackTrace(pw);  
			   String msg=sw.toString();
		      String [] str = msg.split("\n");
		      String str1 = str[0].substring(0,str[0].indexOf(":"));
		      //System.out.println(str1[0]);
		      String str2 = str[0].substring(str[0].indexOf(":")+1);
		      return str2;
		} finally{
			DBUtil.release(null, null, connection);
		}
		return "dbyes";
	}
	//使用库
	public String usedb(String dbname){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql1 = "use "+dbname+";";
		
		
		try { 
			queryRunner.update(connection, sql1);
			
		} catch (SQLException se) {			
			   StringWriter sw = new StringWriter();  
			   PrintWriter pw = new PrintWriter(sw);  
			   se.printStackTrace(pw);  
			   String msg=sw.toString();
		      String [] str = msg.split("\n");
		      String str1 = str[0].substring(0,str[0].indexOf(":"));
		      //System.out.println(str1[0]);
		      String str2 = str[0].substring(str[0].indexOf(":")+1);
		      return str2;
		} finally{
			DBUtil.release(null, null, connection);
		}
		return "usedbyes";
	}
	//创建表
	public String createtable(String dbname,String tablesql){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtils.getConnection(dbname);
		String sql = tablesql+";";
		
		try { 
			queryRunner.update(connection,sql);
			
		} catch (SQLException se) {			
			//Handle errors for JDBC
			   //se.printStackTrace();
			   StringWriter sw = new StringWriter();  
			   PrintWriter pw = new PrintWriter(sw);  
			   se.printStackTrace(pw);  
			   String msg=sw.toString();
			   String [] str = msg.split("\n");
			   String str1 = str[0].substring(0,str[0].indexOf(":"));
			      //System.out.println(str1[0]);
			   String str2 = str[0].substring(str[0].indexOf(":")+1);
			     // System.out.println(str1);
		      return str2;
		} finally{
			DBUtil.release(null, null, connection);
		}
		return "tableyes";
	}
	
	
	//往管理数据库的表中添加数据
	public void insertdbname(String dbname,String username){
		//添加
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//				                                     start number
		String sql = "insert into dbinfo(dbname,tea_user) values(?,?)";
		Object[] params = {dbname,username};
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
	
	public boolean isExist(String dbname,String teauser){
		//添加
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//				                                     start number
		String sql = "select * from dbinfo where dbname=? and tea_user=?";
		Object[] params = {dbname,teauser};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
			try { 
				List<DBInfo> rs = (List<DBInfo>)queryRunner.query(connection, sql,new BeanListHandler(DBInfo.class),params);
				
				if(rs.size() > 0){
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return false;
	}
	
	
	
	
	
	
	
	


	public List<DBInfo> findDb() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from dbinfo ";
		//Object[] params = {username,password};
		
		try {
			List<DBInfo> list = (List<DBInfo>)queryRunner.query(connection, sql,new BeanListHandler(DBInfo.class));
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public DBInfo findOneByName(String name) {    //根据id查询单一practice
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from dbinfo where dbname = ?";
		Object[] params = {name};
		
		try {
			DBInfo one = (DBInfo)queryRunner.query(connection, sql,new BeanHandler(DBInfo.class),params);
	
			return one;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public Ques findOneByDbName(String name) {    //根据name查询单一practice
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from ques where quename = ?";
		Object[] params = {name};
		
		try {
			Ques one = (Ques)queryRunner.query(connection, sql,new BeanHandler(Ques.class),params);
	
			return one;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public void add(Practice practice) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "insert into practice (PracticeName,ClassName,PracticeGenre,Deadline,result_portion,process_portion,ques_id) values(?,?,?,?,?,?,?)";
		Object[] params = {practice.getPracticeName(),practice.getClassName(),practice.getPracticeGenre(),new Timestamp(practice.getDeadline().getTime()),practice.getResult_portion(),practice.getProcess_portion(),practice.getQues_id()};//,practice.getPublishDay(),practice.getDeadline()
	     
		try { 
			queryRunner.update(connection,sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
	public void delete(String prac_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update practice set deleted = 0 where PracticeId = ?";
		Object[] params = {prac_id};           
		
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	//管理库功能  找到对应表数据
	public List<DBInfo> findDBBytea(String teauser) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from dbinfo where tea_user= ?";
		Object[] params = {teauser};           
		
		try { 
			List<DBInfo> list = (List<DBInfo>)queryRunner.query(connection, sql,new BeanListHandler(DBInfo.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public List<DBInfo> findOnePage(String teauser,int startIndex, int pageSize) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from dbinfo where tea_user= ? limit ?,?";
		Object[] params = {teauser,startIndex,pageSize};           
		
		try { 
			List<DBInfo> list = (List<DBInfo>)queryRunner.query(connection, sql,new BeanListHandler(DBInfo.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	//找到对应库下面所有表
	public List<String> findTables(String dbname){
		
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		 String sql = "select table_name from information_schema.tables "+
				 "where table_schema=?"+
				 " and table_type='base table'; ";
		Object[] params = {dbname};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<String> list = (List<String>)queryRunner.query(connection, sql,new ColumnListHandler(),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	//删除dbinfo中的一条库信息
	public int deldbByTea(String dbname, String teauser) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete  from dbinfo where tea_user= ? and dbname=?";
		Object[] params = {teauser,dbname};
		
		try {
			int res = queryRunner.update(connection, sql, params);
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int deletedb(String dbname) {
		Connection connection = DBUtils.getConnection(dbname);
		
		String sql = "drop database "+dbname;
		try {
			Statement stmt = (Statement) connection.createStatement();
			stmt.executeUpdate(sql);
			//System.out.println("delete successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
