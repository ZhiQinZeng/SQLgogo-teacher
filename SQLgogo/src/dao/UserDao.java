package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bean.TeaUser;
import util.DBUtil;

public class UserDao {
//	根据用户名和密码查询用户信息
	public TeaUser findUser(long username,String password) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teauser where name = ? and password = ?";
		Object[] params = {username,password};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			TeaUser user = (TeaUser)queryRunner.query(connection, sql,new BeanHandler(TeaUser.class),params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public void update(TeaUser teaUser) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "update teauser set name = ?,password = ?,realname = ?,e_mail = ?,phone_num=?,sex=?,major=?,company=? ";
		Object[] params = {teaUser.getName(),teaUser.getPassword(),teaUser.getRealname(),teaUser.getE_mail(),teaUser.getPhone_num(),teaUser.getSex(),teaUser.getMajor(),teaUser.getCompany()};
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
	
	public TeaUser SelectByName(String username) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teauser where name = ? ";
		Object[] params = {username};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			TeaUser user = (TeaUser)queryRunner.query(connection, sql,new BeanHandler(TeaUser.class),params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public TeaUser SelectByNameLong(long username) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teauser where name = ? ";
		Object[] params = {username};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			TeaUser user = (TeaUser)queryRunner.query(connection, sql,new BeanHandler(TeaUser.class),params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public TeaUser SelectById(long idString) {  
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from teauser where id = ?";
		Object[] params = {idString};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			TeaUser user = (TeaUser)queryRunner.query(connection, sql,new BeanHandler(TeaUser.class),params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	public int addTeacher(long name,String password,String realname) {  
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "insert into teauser(name,password,realname,profile) values (?,?,?,?)";
		Object[] params = {name,password,realname,"icon/tea_icon/timg.jpg"};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			//TeaUser user = (TeaUser)queryRunner.query(connection, sql,new BeanHandler(TeaUser.class),params);
			int returnval = queryRunner.update(connection, sql,params);
			return returnval;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return 0;
	}
}
