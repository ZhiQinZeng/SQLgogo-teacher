package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.DataSources;
/*
 * 	JDBC工具类，负责注册驱动，获取连接，释放资源
 * 
 *  注册驱动需要使用  com.mysql.jdbc.Driver MySQL驱动包中的Driver类，直接写在代码中，会使程序和
 *  MySQL的驱动耦合，以后如果更改数据库，也必须要更改代码，程序的扩展性不强
 *  包括连接数据库时的账号密码，这些内容都可能会更改，因此最好将这些信息存储到一个配置文件中，通过在程序中
 *  读取配置文件来注册驱动，获取连接
 * 
 *  
 */
public class DBUtil {
	
	private static final String JDBC_DRIVER = "driverClass";
	private static final String JDBC_URL = "jdbcUrl";
	
	private static DataSource ds;
	
	/** 
     * 初始化连接池代码块 
     */
	static {
		initDBSource();
	}
	
	/** 
     * 初始化c3p0连接池 
     */
	private static final void initDBSource() {
		Properties c3p0Pro = new Properties();
		try {
			 //加载配置文件  
			c3p0Pro.load(DBUtil.class.getResourceAsStream("../c3p0.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String drverClass = c3p0Pro.getProperty(JDBC_DRIVER);
		if(drverClass != null) {
			try {
				//加载驱动类  
				Class.forName(drverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		Properties jdbcpropes = new Properties();
		Properties c3propes = new Properties();
		for (Object key : c3p0Pro.keySet()) {
			String skey = (String)key;
			if(skey.startsWith("c3p0.")) {
				c3propes.put(skey, c3p0Pro.getProperty(skey));
			} else {
				jdbcpropes.put(skey, c3p0Pro.getProperty(skey));
			}
		}
		
		try {
			//建立连接池
			DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL),jdbcpropes);
			ds = DataSources.pooledDataSource(unPooled,c3propes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
     * 获取数据库连接对象 
     * @return 数据连接对象 
     * @throws SQLException  
     */ 
	public static synchronized Connection getConnection()  {	
		try {
			final Connection conn = ds.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//释放资源
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		//资源在finnaly中释放
		if(resultSet != null){
			try {
				resultSet.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	/*private static Properties properties;
	//注册驱动
	static{
		//静态代码块当类被加载的时候会执行，只要DBUtil类被第一次用到，DBUtil类就会被加载到内存，
		//静态代码块就会执行，驱动会被注册
		try {
			//通过类加载器读取src目录中的 properties文件
			ClassLoader cl = DBUtil.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("db.properties");
			
			//Properties有一个对应的类，可以用于读取文件信息
			properties = new Properties();
			//通过输入流加载properties文件
			properties.load(is);
			
			//通过key获取到properties文件中对应的值
			String driver = properties.getProperty("driver");
			//注册驱动
			Class.forName(driver);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection(){
		
		//读取配置文件中的账号，密码等信息，用于连接数据库
		String url = properties.getProperty("url");
		String user = properties.getProperty("username");
		String password = properties.getProperty("password");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//释放资源
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		//资源在finnaly中释放
		if(resultSet != null){
			try {
				resultSet.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}*/
}
