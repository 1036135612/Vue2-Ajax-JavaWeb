package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import object.lost;

//执行数据库SQL语句类
public class UseServer {

	public List<lost> findAllData() {

		List<lost> ret = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isExist = false;

		try {
			// 启动数据库
			conn = ServerConnect.ServerConnect();
			if (conn == null) {
				System.out.println("数据库未连接");
			} else {
				// 连接成功执行语句，准备语句
				String sql = "SELECT *FROM lots WHERE Lname NOT IN (SELECT Ugoods FROM `shopping cart`)";
				// 准备执行语句
				pstmt = conn.prepareStatement(sql);
				// 执行SQL语句
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					if (!isExist) {
						ret = new ArrayList<lost>();
						isExist = true;
					}
					// 数据库对象
					lost entity = new lost();
					// 数据库属性
					String Lname = rs.getString("Lname");
					String wraparound = rs.getString("wraparound");
					String price = rs.getString("price");
					String url = rs.getString("url");
					// 数据放对象里
					entity.setLname(Lname);
					entity.setwraparound(wraparound);
					entity.setprice(price);
					entity.seturl(url);
					// 放到数据列表
					ret.add(entity);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				// 关闭数据库
				ServerConnect.ServerClose(conn);
			}
		}
		return ret;
	}

	public lost findDataByYnm(String Yname) {

		lost ret = null;
		Connection conn = null;// 数据库连接对象
		PreparedStatement pstmt = null;// 语句执行对象
		boolean isExist = false;

		try {

			conn = ServerConnect.ServerConnect();
			if (null == conn) {
				System.out.println("数据库未连接");
			} else {
				// 连接成功执行语句，准备语句
				String sql = "select * from xj where name = ?";
				// 准备执行语句
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Yname);
				// 执行SQL语句
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					if (!isExist) {
						ret = new lost();
						isExist = true;

					}

					String Lname = rs.getString("Lname");
					String wraparound = rs.getString("wraparound");
					String price = rs.getString("price");

					ret.setLname(Lname);
					ret.setwraparound(wraparound);
					ret.setprice(price);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (null != conn) {
				ServerConnect.ServerClose(conn);
			}
		}
		return ret;

	}

	//SQL自定义查询方法
	public List DiySql(String sql)throws SQLException, ClassNotFoundException {

		List list = new ArrayList();
		ResultSet rets = null;
		Connection conn = null;// 数据库连接对象
		PreparedStatement pstmt = null;// 语句执行对象

		try {

			conn = ServerConnect.ServerConnect();
			if (null == conn) {
				System.out.println("数据库未连接");
			} else {

				// 准备执行语句
				pstmt = conn.prepareStatement(sql);
				// 执行SQL语句
				rets = pstmt.executeQuery();

				
				//Map rowData = new HashMap();
				
				list = convertList(rets);
				
		        conn.close();
		        
				//System.out.println("list:" + list.toString());
				// for (Object li : list) {
			    //       System.out.println("list:" + li);
			    //   }//list遍历的两种方式
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (null != conn) {
				ServerConnect.ServerClose(conn);
			}
		}
		return list;
	}
	
	//SQL自定义上传方法
	public int UpdateSql(String sql)throws SQLException, ClassNotFoundException {

		int rets = 0 ;
		Connection conn = null;// 数据库连接对象
		PreparedStatement pstmt = null;// 语句执行对象

		try {

			conn = ServerConnect.ServerConnect();
			if (null == conn) {
				System.out.println("数据库未连接");
			} else {

				// 准备执行语句
				pstmt = conn.prepareStatement(sql);
				// 执行SQL语句
				rets = pstmt.executeUpdate();
				
		        conn.close();		        
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (null != conn) {
				ServerConnect.ServerClose(conn);
			}
		}
		return rets;
	}
	
	//ResultSet对象转换为list集合
	private static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

}
