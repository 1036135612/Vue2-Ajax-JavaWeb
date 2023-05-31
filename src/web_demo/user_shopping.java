package web_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mysql.UseServer;

@WebServlet("/user_shopping")
public class user_shopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    Gson gson = new Gson();
	    
	    String flag = request.getParameter("flag");
	    String user = request.getParameter("user");
	    
	  //查询商品信息
	    String sql ="SELECT * FROM  `shopping cart` \r\n"
	    		+ "INNER JOIN commodity\r\n"
	    		+ "ON `shopping cart`.ugoods=commodity.Cname\r\n"
	    		+ "WHERE `shopping cart`.`name`='{0}'";
	    sql=sql.replace("{0}", user);
	    
	    try {
		     List list=accontDao.DiySql(sql);
	         if(list != null && !list.isEmpty() && flag.equals("")) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					  System.out.println(gson1);
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
			   }
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("查询出错！");
			}
	    if(!flag.equals("") &&!flag.equals("undefind")) {
	    	
	    	String sqll = "DELETE FROM `shopping cart` WHERE name = '{0}' && ugoods = '{1}'";
	    	sqll=sqll.replace("{0}", user);
	    	sqll=sqll.replace("{1}", flag);
	    	 try {
				int reqll=accontDao.UpdateSql(sqll);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    	
	    	System.out.println(flag);
	    	System.out.println(flag);
	    	
	    }
	    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    Gson gson = new Gson();
	    
	    String flag = request.getParameter("flag");
	    String user = request.getParameter("user") ;
	    
	  //查询商品信息
	    String sql ="SELECT * FROM  `shopping cart` \r\n"
	    		+ "INNER JOIN lots\r\n"
	    		+ "ON `shopping cart`.ugoods=lots.Lname\r\n"
	    		+ "WHERE `shopping cart`.`name`='{0}'";
	    sql=sql.replace("{0}", user);
	    
	    try {
		     List list=accontDao.DiySql(sql);
	         if(list != null && !list.isEmpty() && flag.equals("")) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					  System.out.println(gson1);
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
			   }
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("查询出错！");
			}
	    if(!flag.equals("") && !flag.equals("undefined")) {
		    
	    	String sqll = "DELETE FROM `shopping cart` WHERE name = '{0}' && ugoods = '{1}'";
	    	sqll=sqll.replace("{0}", user);
	    	sqll=sqll.replace("{1}", flag);
	    	 try {
				int reqll=accontDao.UpdateSql(sqll);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    	
	    	System.out.println(flag);
	    	
	    }
	}

}
