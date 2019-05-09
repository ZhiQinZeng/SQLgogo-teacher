package dao;

import java.sql.Connection;





import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Practice;
import bean.Question;

import util.DBUtil;

public class QuestionDao {


	public Question findQes(String studentName) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from questions where studentName = ? and deleted = 1";
		Object[] params = {studentName};
		
		try {
			Question qes = (Question)queryRunner.query(connection, sql,new BeanHandler(Question.class),params);
			return qes;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public List<Question> allQuestion() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from questions where deleted = 1";

		try {
			List<Question> list = (List<Question>)queryRunner.query(connection, sql,new BeanListHandler(Question.class));
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public int  count(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT COUNT(QuestionId) FROM questions";

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
	public int  countStar(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT COUNT(QuestionId) FROM questions where star = 1";

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
	public int  countAnswer(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT COUNT(QuestionId) FROM questions where questionDeal != '0'";

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
	public List<Question> findByPage(int page){
	   	 QueryRunner queryRunner = new QueryRunner();
	   	 Connection connection = DBUtil.getConnection();
	   	 String sql ="Select * FROM  questions where  deleted = 1 Limit ?,?";
	   	 Object[] params = {(page-1)*4,4};
	   	    try {
	   			List<Question> list = (List<Question>)queryRunner.query(connection, sql,new BeanListHandler(Question.class),params);
	   			return list;
	   		} catch (SQLException e) {
	   			e.printStackTrace();
	   		} finally{
	   			DBUtil.release(null, null, connection);
	   		}
	   		return null;
	    }
	
	public List<Question> findByDate(int page){
	   	 QueryRunner queryRunner = new QueryRunner();
	   	 Connection connection = DBUtil.getConnection();
	   	 String sql ="select * from (SELECT * FROM questions ORDER BY addtime asc) t where deleted = 1 Limit ?,?";
	   	 Object[] params = {(page-1)*4,4};
	   	    try {
	   			List<Question> list = (List<Question>)queryRunner.query(connection, sql,new BeanListHandler(Question.class),params);
	   			return list;
	   		} catch (SQLException e) {
	   			e.printStackTrace();
	   		} finally{
	   			DBUtil.release(null, null, connection);
	   		}
	   		return null;
	    }
	public List<Question> findByStar(int page){
	   	 QueryRunner queryRunner = new QueryRunner();
	   	 Connection connection = DBUtil.getConnection();
	   	 String sql ="SELECT * FROM questions where star = 1 Limit ?,?";
	   	 Object[] params = {(page-1)*4,4};
	   	    try {
	   			List<Question> list = (List<Question>)queryRunner.query(connection, sql,new BeanListHandler(Question.class),params);
	   			return list;
	   		} catch (SQLException e) {
	   			e.printStackTrace();
	   		} finally{
	   			DBUtil.release(null, null, connection);
	   		}
	   		return null;
	    }
	public List<Question> findByAnswer(int page){
	   	 QueryRunner queryRunner = new QueryRunner();
	   	 Connection connection = DBUtil.getConnection();
	   	 String sql ="SELECT * FROM questions where questionDeal != '0' Limit ?,?";
	   	 Object[] params = {(page-1)*4,4};
	   	    try {
	   			List<Question> list = (List<Question>)queryRunner.query(connection, sql,new BeanListHandler(Question.class),params);
	   			return list;
	   		} catch (SQLException e) {
	   			e.printStackTrace();
	   		} finally{
	   			DBUtil.release(null, null, connection);
	   		}
	   		return null;
	    }
	
	public void update(String questionId,String qesDeal) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		
		String sql = "update questions set questionDeal = ? where QuestionId = ?";
		Object[] params = {qesDeal,questionId};
		
		try { 
			queryRunner.update(connection, sql,params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
	public void delete(String qes_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update subjects set deleted = 0 where QuestionId = ?";
		Object[] params = {qes_id};           
		

		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
	
	public void delete_comment(String qes_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update  questions set questionDeal = 0 where QuestionId = ?";
		Object[] params = {qes_id};           
		

		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	public void star(String star,String id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "update questions set star = ? where QuestionId = ?";
		Object[] params = {star,id};
		
		try { 
			queryRunner.update(connection, sql,params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
}
