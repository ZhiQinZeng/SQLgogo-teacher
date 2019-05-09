package dao;

import java.sql.Connection;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;



import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Pra_sub;
import bean.Practice;
import bean.Subject;
import bean.TeaUser;

import util.DBUtil;
import util.DBUtils;

public class SubjectDao {


	public List<Subject> findSubject(String id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from subjects where PracticeId = ? and deleted = 1";
		Object[] params = {id};
		
		try {
			List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
	public List<Subject> findSubjectByPraId(String id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * from subjects t1 join (select pra_Id,sub_id,deleted as del from pra_sub where  pra_Id = ? and deleted = 1) t2 on t1.SubjectId = t2.sub_id WHERE t1.deleted = 1";
		Object[] params = {id};
		
		try {
			List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);
	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
	public Subject findOneSubject(String id) {  //用subjectID来查题目
		
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from subjects where SubjectId = ? and deleted = 1";
		Object[] params = {id};
		
		try {
			Subject subject = (Subject)queryRunner.query(connection, sql,new BeanHandler(Subject.class),params);

			return subject;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	
	
    public List<Subject> find_qes_Subject(String id) {  //用题库id来查询每个题库里相应的题目
		
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from subjects where ques_id = ? and deleted = 1";
		Object[] params = {id};
		
		try {
			List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		return null;
	}
	/*public void update(TeaUser teaUser) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
//		                                     start number
		String sql = "update teauser set name = ?,password = ?,realname = ?,e_mail = ?,phone_num=?,sex=?,major=?,company=? ";
		Object[] params = {teaUser.getName(),teaUser.getPassword(),teaUser.getRealname(),teaUser.getE_mail(),teaUser.getPhone_num(),teaUser.getSex(),teaUser.getMajor(),teaUser.getCompany()};
		
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
	*/
	public void add(String name,String protray,String answer,String id,String keys,int total) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "insert into subjects (SubjectName,subject_detail,subject_answer,key_choice,Total) values(?,?,?,?,?)";
		Object[] params = {name,protray,answer,keys,total};
		
		
		String sql2 = "insert into pra_sub (pra_Id,sub_id) values(?,?)";
		

		try { 
			queryRunner.update(connection,sql,params);
			
			int sub_id = selectByPotray(protray);
			Object[] params2 = {id,sub_id};
			
			queryRunner.update(connection,sql2,params2);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	
	
	
	public void add_pra_sub(String pra_id,String sub_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "insert into pra_sub(pra_Id,sub_id) values(?,?)";
		Object[] params = {pra_id,sub_id};
		

		try { 
			queryRunner.update(connection,sql,params);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	public boolean check_id(String pra_id,String sub_id){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();

		String sql = "select * from pra_sub where pra_Id = ? and sub_id = ?";
		Object[] params = {pra_id,sub_id};

		try { 
			
			Pra_sub pra_sub = (Pra_sub)queryRunner.query(connection, sql,new BeanHandler(Pra_sub.class),params);
			try {
				System.out.println(pra_sub.getSub_id());
			} catch (Exception e) {
				return true;
			}
			
//			if(flag.equals("")){
//				return true;
//			}else{
//				return false;
//			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		} finally{
			DBUtil.release(null, null, connection);
		}
		
	}
	public int selectByPotray(String protray) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "select SubjectId from subjects where subject_detail = ? and deleted = 1";
		Object[] params = {protray};     
		try { 
			   
				int id = (int)queryRunner.query(connection, sql,new ScalarHandler(),params);
				return  id;
		} catch (SQLException e) {
			int str= -1;
			e.printStackTrace();
			return str;
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
	
	
	
	public void delete(String sub_id,String pra_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from pra_sub where sub_id = ? and pra_Id = ? ";
		
		Object[] params = {sub_id,pra_id};           
		//String sql = "delete from practice where PracticeId = ?";
		//Object[] params = {prac_id};

		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}

	public void all_delete(String prac_id) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = DBUtil.getConnection();
		
		String sql = "delete from pra_sub where  pra_Id = ?";
		             
		Object[] params = {prac_id};           
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, null, connection);
		}
	}
	
	
	//找到对应表中所有数据
			public List<Object[]> findDatas(String dbname,String tabname) throws ClassNotFoundException, SQLException{
				QueryRunner queryRunner = new QueryRunner();
				Connection connection = DBUtils.getConnection(dbname);
//						                                     start number
				
				 String sql = "select * from "+tabname;
				//Object[] params = {dbname};
				/*
				 * BeanListHandler:将结果集封装为list集合
				 * BeanHandler：将结果集中的第一条记录封装为对象
				 */
				try {
					//方法返回值 每行是一个对象数组，存储到List
					List<Object[]> list = (List<Object[]>)queryRunner.query(connection, sql,new ArrayListHandler());
					return list;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					DBUtil.release(null, null, connection);
				}
				return null;
			}
			
					
		//找到对应表中所有字段类型字段和字段注释
		public List<Object[]> findTypes(String dbname,String tabname) throws ClassNotFoundException, SQLException{
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtils.getConnection(dbname);
//						                                     start number
			
			 String sql = "select COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT from information_schema.COLUMNS where table_name = ? and table_schema = ?";
			Object[] params = {tabname,dbname};
			/*
			 * BeanListHandler:将结果集封装为list集合
			 * BeanHandler：将结果集中的第一条记录封装为对象
			 */
			try {
				//方法返回值 每行是一个对象数组，存储到List
				List<Object[]> list = (List<Object[]>)queryRunner.query(connection, sql,new ArrayListHandler(),params);
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		//曾智琴
		public List<Subject> findSubjectByQueid(int id) {  
			
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			String sql = "select * from subjects where ques_id = ? ";
			Object[] params = {id};
			
			try {
				List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);

				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}

		//zengzhii
		public List findSubjectsOnePage(int queid, int startIndex, int pageSize) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			String sql = "select * from subjects where ques_id = ? limit ?,? ";
			Object[] params = {queid,startIndex,pageSize};
			
			try {
				List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);

				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}
		
		public void delBysubid(int sub_id) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			
			String sql = "delete from subjects where SubjectId = ? ";
			
			Object[] params = {sub_id};           
			
			try { 
				queryRunner.update(connection, sql,params);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
		}
		//zengzhiqin
		public void updateBysubid(int upid,String name,String protray,String answer,String id,String keys,int total) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();

			String sql = "update subjects set SubjectName =?,subject_detail=?,subject_answer=?,key_choice=?,Total=? where SubjectId=?";
			Object[] params = {name,protray,answer,keys,total,upid};
			

			try { 
				queryRunner.update(connection,sql,params);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			
		}
		
		public void addSubject(int subid,String name,String protray,String answer,String id,String keys,int total) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();

			String sql = "insert into subjects (SubjectName,subject_detail,subject_answer,key_choice,Total,ques_id) values(?,?,?,?,?,?)";
			Object[] params = {name,protray,answer,keys,total,subid};
		
			try { 
				queryRunner.update(connection,sql,params);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			
		}


		public void addSubjectUpload(int subid,String name,String protray,String answer,String total,String keys) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();

			String sql = "insert into subjects (SubjectName,subject_detail,subject_answer,set_score,key_choice,ques_id) values(?,?,?,?,?,?)";
			Object[] params = {name,protray,answer,total,keys,subid};
		
			try { 
				queryRunner.update(connection,sql,params);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			
			
		}

		//zengzhiiqin  得到练习里面的题目
		public List<Subject> findSubjectBypraid(int praid) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			String sql = "select * from subjects where SubjectId in (select sub_id from pra_sub where pra_Id = ?)";
			Object[] params = {praid};
			
			try {
				List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);

				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}


		public List findSubjectResultOnePage(int praid, int startIndex, int pageSize) {
			QueryRunner queryRunner = new QueryRunner();
			Connection connection = DBUtil.getConnection();
			String sql = "select * from subjects where SubjectId in (select sub_id from pra_sub where pra_Id = ?) limit ?,?";
			Object[] params = {praid,startIndex,pageSize};
			
			try {
				List<Subject> list = (List<Subject>)queryRunner.query(connection, sql,new BeanListHandler(Subject.class),params);

				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtil.release(null, null, connection);
			}
			return null;
		}


		
		
}
