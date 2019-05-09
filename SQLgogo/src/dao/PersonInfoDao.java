package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DBUtil;

import bean.Ques;
import bean.TeaUser;

public class PersonInfoDao {
	//存储图片路径
	public void uploadimage(String filename,BigInteger username) {

		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "update teauser set profile=? where name=?";
		Object[] params = {filename,username};
		try { 
			queryRunner.update(connection, sql,params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	//根据个人用户名查找出路径
	public List<TeaUser> findImagePath(BigInteger username) {

		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "select profile from teauser where name=?";
		Object[] params = {username};
		try { 
			List<TeaUser> list = (List<TeaUser>)queryRunner.query(connection, sql,new BeanListHandler(TeaUser.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	//存储老师个人信息
	public void saveType(TeaUser tea,String username){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "update teauser set e_mail=?,phone_num=?,sex=?,major=?,company=? where name=?";
		Object[] params = {tea.getE_mail(),tea.getPhone_num(),tea.getSex(),tea.getMajor(),tea.getCompany(),username};
		try { 
			queryRunner.update(connection, sql,params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
}
