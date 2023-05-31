package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//连接数据库类
public class ServerConnect {

	// 连接数据库函数
	public static Connection ServerConnect() {
		// 数据库连接成功返回值
		Connection connect = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");

			System.out.println("成功加载Mysql驱动程序!");
		} catch (Exception e) {
			System.out.println("加载Mysql驱动程序时出错!");
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysource", "root", "root");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			System.out.println("成功连接Mysql服务器!");
		} catch (Exception e1) {
			System.out.print("获取数据错误!");
			e1.printStackTrace();

		}
		return connect;
	}

	// 数据库关闭函数
	public static void ServerClose(Connection connect) {

		if (connect != null) {

			try {
				connect.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

}
