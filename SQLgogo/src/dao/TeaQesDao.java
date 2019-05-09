package dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Question;
import bean.teaQuestion;
import util.DBUtil;

public class TeaQesDao {



	public List<teaQuestion> findQes(int userId,int page) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teaquestions where teaNumber = ? and deleted = 1  Limit ?,?";
		Object[] params = {userId,(page-1)*4,4};
		
		try {
			
			List<teaQuestion> list = (List<teaQuestion>)queryRunner.query(connection, sql,new BeanListHandler(teaQuestion.class),params);
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public List<teaQuestion> allQuestion() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teaquestions where deleted = 1";
		
		
		try {
			List<teaQuestion> list = (List<teaQuestion>)queryRunner.query(connection, sql,new BeanListHandler(teaQuestion.class));
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public int  countQes(int userId){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT COUNT(teaQuestionId) FROM teaquestions where teaNumber = ?";
		Object[] params = {userId};
		try {
			long count = (long)queryRunner.query(connection, sql,new ScalarHandler(),params);
			return (int) count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return 0;
	}
	public void add(String name,String protray,int id,String realName) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "insert into teaquestions (qesName,questionProtray,teaNumber,teaName) values(?,?,?,?)";
		Object[] params = {name,protray,id,realName};
	     
		try { 
			queryRunner.update(connection,sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
	public void deleted(String teaqes_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		System.out.println("jinlaile       "+teaqes_id);
		String sql = "update teaquestions set deleted = 0 where teaQuestionId = ?";
		Object[] params = {teaqes_id};           
		

		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	public Question find_one(String id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
	
		String sql = "select * from questions  where QuestionId = ? and deleted = 1";
		Object[] params = {id};           
		

		try { 
			Question one = (Question)queryRunner.query(connection, sql,new BeanHandler(Question.class),params);
			return one;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public teaQuestion find_one_tea(String id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
	
		String sql = "select * from teaquestions  where teaQuestionId = ? and deleted = 1";
		Object[] params = {id};           
		

		try { 
			teaQuestion one = (teaQuestion)queryRunner.query(connection, sql,new BeanHandler(teaQuestion.class),params);
			return one;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
}
