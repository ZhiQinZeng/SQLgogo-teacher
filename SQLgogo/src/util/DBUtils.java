package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


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
public class DBUtils {
	
	private static Properties properties;
	//注册驱动
	static{
		//静态代码块当类被加载的时候会执行，只要DBUtil类被第一次用到，DBUtil类就会被加载到内存，
		//静态代码块就会执行，驱动会被注册
		try {
			//通过类加载器读取src目录中的 properties文件
			ClassLoader cl = DBUtils.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("db2.properties");
			
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
	public static Connection getConnection(String databasename){
		
		//读取配置文件中的账号，密码等信息，用于连接数据库
		String url = properties.getProperty("url")+databasename+"?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
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
	
	}
}
