package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.ClassInfo;
import bean.Ques;
import bean.Question;
import util.DBUtil;

public class QueDao {
	public void addQue(String a, String b,String c) {
		
		//添加
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "insert into ques(quename,quecontext,thedate) values(?,?,?)";
		Object[] params = {a,b,c};
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
	
	//查找
	public List<Ques> findQues(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		
		String sql = "select id,quename,quecontext,thedate from ques";
		//Object[] params = {name,realname};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			
			List<Ques> list = (List<Ques>)queryRunner.query(connection, sql,new BeanListHandler(Ques.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	//删除
	public void deleteQue(int id){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from ques where id = ?";
		Object[] params = {id};
		
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
	//查找
		public Ques findQueById(int id){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
//			                                     start number
			
			String sql = "select quecontext,quename from ques where id=?";
			Object[] params = {id};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				
				Ques describe = (Ques)queryRunner.query(connection, sql,new BeanHandler(Ques.class),params);
				return describe;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		//通过名字查找
		public Ques findOneByName(String name) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			String sql = "select id,quecontext,thedate,quename from ques where quename = ? ";
			Object[] params = {name};
			System.out.println(name+"jinlaile!!!!!!!!!!!!!!!");
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
		
		//修改
		public void updateQue(Ques ques,Object upid){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "update ques set quename=?,quecontext=?,thedate=? where id=?";
			Object[] params = {ques.getQuename(),ques.getQuecontext(),ques.getThedate(),upid};
			
			try { 
				queryRunner.update(connection, sql,params);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
		}

		public List<Ques> findOnePage(int startIndex, int pageSize) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
//			                                     start number
			
			String sql = "select id,quename,quecontext,thedate from ques limit ?,?";
			Object[] params = {startIndex,pageSize};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				
				List<Ques> list = (List<Ques>)queryRunner.query(connection, sql,new BeanListHandler(Ques.class),params);
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		
}
