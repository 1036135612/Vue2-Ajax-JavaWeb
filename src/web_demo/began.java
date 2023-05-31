package web_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mysql.UseServer;
import mysql.UserService;
import object.lost;

@WebServlet("/began")
public class began extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService re = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=utf-8");
		 
		 PrintWriter out = response.getWriter();
		 String Lname = request.getParameter("Lname");
	     Map hm =null ;
	     Gson gson = new Gson();	 
	    
	     UseServer accontDao = new UseServer();
	     //String sql = "select * from user where username='"+user+"' AND password='"+paw+"'" ; 
	     //使用占位符拼写SQL
	     String sql ="select * from lots where Lname='{0}' ";
	     sql=sql.replace("{0}", Lname);
	    
	     try {
		     List list=accontDao.DiySql(sql);
	         if(list != null && !list.isEmpty()) {
	         
	           //out.println("list:" + list.toString());
	           //转换成Map对象	 
	           Iterator it = list.iterator();   
	           while(it.hasNext()) {   
	        	   //指针指向下一条记录
	               hm = (Map)it.next();    
	      
	           }  
	                
				      String gson1 = gson.toJson(hm); 				     
					  out.println(gson1);
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
			   }
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("查询出错！");
			}
		
	           out.flush();
	           out.close();
				
        //集合与对象的遍历演示
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		List<Accont> u = re.findAll();
//
//		String username = request.getParameter("username");
//		Accont use = re.findByYhm(username);
//
//		out.println("<html><head><meta charset=\"utf-8\"></head>");
//		for (Accont entity : u) {
//			out.println(entity.getUserName());
//			out.println(entity.getInformation());
//			out.println(entity.getIphone() + "<br>");
//		}
//		out.println("你要查询的消息：");
//		out.println(use.getUserName());
//		out.println(use.getInformation());
//		out.println(use.getIphone());
//		out.println("</html>");
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		List<lost> u = re.findAll();
		String gson1 = gson.toJson(u);

		out.write(gson1);
		// out.println(gson1);
		out.flush();
		out.close();

		// doGet(request, response);
	}

}
