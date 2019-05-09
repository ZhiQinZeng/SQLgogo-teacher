package dao;

import java.sql.Connection;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Practice;
import bean.PracticeResult;
import util.DBUtil;

public class PracticeDao {

	//zengzhiiqin
	
	public List<PracticeResult> findPracticeOnePage(int teaid,int startIndex,int pageSize) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from practice where classid in (select Id from classinfo where teaid=?) limit ?,?";
		Object[] params = {teaid,startIndex, pageSize};
		
		try {
			List<PracticeResult> list = (List<PracticeResult>)queryRunner.query(connection, sql,new BeanListHandler(PracticeResult.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public List<Practice> findPractice(int page) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from practice where deleted = 1 Limit ?,?";
		Object[] params = {(page-1)*7,7};
		
		try {
			List<Practice> list = (List<Practice>)queryRunner.query(connection, sql,new BeanListHandler(Practice.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	public int  countPractice(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT COUNT(PracticeId) FROM practice where deleted = 1";

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
	public Practice findOneById(String id) {    //根据id查询单一practice
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from practice where PracticeId = ? and deleted = 1";
		Object[] params = {id};
		
		try {
			Practice one = (Practice)queryRunner.query(connection, sql,new BeanHandler(Practice.class),params);
	
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

		String sql = "insert into practice (PracticeName,ClassName,PracticeGenre,Deadline,result_portion,process_portion,ques_id,dbname,classid) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {practice.getPracticeName(),practice.getClassName(),practice.getPracticeGenre(),new Timestamp(practice.getDeadline().getTime()),practice.getResult_portion(),practice.getProcess_portion(),practice.getQues_id(),practice.getDbname(),practice.getClassid()};//,practice.getPublishDay(),practice.getDeadline()
	     
		try { 
			queryRunner.update(connection,sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	public void modify(Practice practice) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "update practice set PracticeName = ?,ClassName = ?,PracticeGenre = ?,Deadline = ?,result_portion = ?,process_portion = ?,ques_id = ?,dbname = ?,classid = ? where PracticeId = ?";
		Object[] params = {practice.getPracticeName(),practice.getClassName(),practice.getPracticeGenre(),new Timestamp(practice.getDeadline().getTime()),practice.getResult_portion(),practice.getProcess_portion(),practice.getQues_id(),practice.getDbname(),practice.getClassid(),practice.getPracticeId()};//,practice.getPublishDay(),practice.getDeadline()
	     
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
	//zengzhiqn
	public List<PracticeResult> findPracticeResult(int teaid) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from practice where classid in (select Id from classinfo where teaid = ?)";
		Object[] params = {teaid};
		
		try {
			List<PracticeResult> list = (List<PracticeResult>)queryRunner.query(connection, sql,new BeanListHandler(PracticeResult.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}

}
