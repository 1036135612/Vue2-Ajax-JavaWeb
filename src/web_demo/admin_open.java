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

@WebServlet("/admin_open")
public class admin_open extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    Gson gson = new Gson();
	    
	    String flag = request.getParameter("flag");
	    String Cname = request.getParameter("Cname");
	    
	  //查询商品信息
	    String sql ="SELECT * FROM  commodity ";
	    
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
	    	
	    	String sqll = "DELETE FROM commodity WHERE Cname = '{0}' && url = '{1}'";
	    	sqll=sqll.replace("{0}", Cname);
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
	    String Lname = request.getParameter("Lname") ;
	    
	  //查询拍品信息
	    String sql ="SELECT *\r\n"
	    		+ "FROM lots\r\n"
	    		+ "WHERE Lname NOT IN (SELECT Ugoods FROM `shopping cart`)";
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
		    
	    	String sqll = "DELETE FROM lots WHERE Lname = '{0}' && url = '{1}'";
	    	sqll=sqll.replace("{0}", Lname);
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
