package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Result;
import bean.StuUser;
import util.DBUtil;

public class ResultDao {
	//根据练习id和习题id找到学生回答情况
	public List<Result> findResultByDou(int praid,int subid){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		//根据
		String sql = "select * from result  where  pra_id=? and sub_id=?";
		Object[] params = {praid,subid};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {			
			List<Result> list = (List<Result>)queryRunner.query(connection, sql,new BeanListHandler(Result.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	//根据练习id和习题id找到学生回答情况
		public List findResultOnePage(int praid,int subid,int pageNum, int pageSize){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			//根据
			String sql = "select result.pro_score,result.res_score,result.score,result.code,result.updatetime,stuuser.realname,stuuser.username,stuuser.ClassId from result  join stuuser on  result.stu_id=stuuser.id where pra_id=? and sub_id=? order by result.updatetime asc limit ?,? ";
			Object[] params1 = {praid,subid,pageNum,pageSize};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {			
				List list = (List)queryRunner.query(connection, sql,new ArrayListHandler(),params1);
					
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		//查找未提交名单
		public List findnotsubmit(int classid,int praid,int subid){
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			//根据
			String sql = "select realname from stuuser where  ClassId =? and id not in(select stu_id from result where pra_id=? and sub_id=?)";
			
			Object[] params1 = {classid,praid,subid};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {			
				List list = (List)queryRunner.query(connection, sql,new ArrayListHandler(),params1);
					
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		
}
