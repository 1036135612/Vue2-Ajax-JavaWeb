package web_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mysql.UseServer;

@WebServlet("/Open_three")
public class Open_three extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
		
		String name = request.getParameter("name");
		String fname = request.getParameter("fname");
		
		String sql = "DELETE FROM `shopping cart` \r\n"
				+ "WHERE name='{0}'  AND ugoods='{1}'";
		sql=sql.replace("{0}", name);
	    sql=sql.replace("{1}", fname);
		
		
		try {
			
			int	req = accontDao.UpdateSql(sql);
			 if(req != 0 && !(req <0)) {  
				 
				 out.println("您对该用户的操作成功！！");
			 }else {
				
				 out.println("操作失败！！重新尝试或者联系开发者！");
			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
		Gson gson = new Gson();	
		
		String sql = "SELECT*FROM commodity\r\n"
				+ "INNER JOIN `shopping cart`\r\n"
				+ "ON commodity.Cname=`shopping cart`.ugoods";
		
		String sqll = "SELECT*FROM lots\r\n"
				+ "INNER JOIN `shopping cart`\r\n"
				+ "ON lots.Lname=`shopping cart`.ugoods";
		
		try {
			
			List list1 = accontDao.DiySql(sql);
			List list2 = accontDao.DiySql(sqll);
			List list3 = new ArrayList<>();
			//合并两个list列表
			list3.addAll(list1);
			list3.addAll(list2);
			String gson1 = gson.toJson(list1); 
			String gson2 = gson.toJson(list2); 
			String gson3 = gson.toJson(list3); 
			System.out.println(gson1);
			System.out.println(gson2);
			out.println(gson3);
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
