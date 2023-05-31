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


@WebServlet("/Userlogo")
public class Userlogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 
		
		/*
		 * 设置Ajax响应头 
		 * response.setHeader("Access-Control-Allow-Origin",'*');
		 */
		
		/*
		 * 初始化Session HttpSession session = request.getSession();
		 */
		
		//如果需要接收json类型的数据，则将其属性设置为json
		response.setContentType("application/json");
		
		//设置请求与响应的编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
       // List<FacebookModel> list = facebookService.getFacebookList();
       // JSONObject jsonObject = new JSONObject();
       // jsonObject.put("facebook",list);
        
        //通过servlet唯一方式，response输出流的方法响应
        PrintWriter out = response.getWriter();
		
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println("用户名："+username+" 密码："+password);
        response.getWriter().print(username);
		//设置响应体
		//response.sendRedirect(username);
        //响应方法
		/*
		 * out.println('xxx'); out.close(); out.flush();
		 */
        if(username.equals("admin") && password.equals("123")) {
        	
        	out.write("用户存在密码正确登录成功！");
           
        }  
        else {
            out.write("用户不存密码不正确在登录失败！");

        }
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		/*
		 * request.setCharacterEncoding("UTF-8");
		 * response.setCharacterEncoding("UTF-8"); 
		 * String fid =request.getParameter("id"); 
		 * int id = 0; 
		 * if (!"".equals(fid)) id =Integer.parseInt(fid); 
		 * int result = facebookService.deleteFacebook(id);
		 * 则ajax中的dataType属性就设置为text属性 
		 * response.getWriter().print(result);
		 */
		
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=utf-8");
		 
		 PrintWriter out = response.getWriter();
		 String user = request.getParameter("username");
	     String paw = request.getParameter("password");
	     String admin = null ;
	     Map hm =null ;
	     Gson gson = new Gson();
	     
	     UseServer accontDao = new UseServer();
	     //String sql = "select * from user where username='"+user+"' AND password='"+paw+"'" ; 
	     //使用占位符拼写SQL
	     String sql ="select * from user where username='{0}' AND password='{1}'";
	     sql=sql.replace("{0}", user);
	     sql=sql.replace("{1}", paw);
	     
	     
	     try {
	     //执行数据库语句，返回list集合 	 
	     List list=accontDao.DiySql(sql);
         if(list != null && !list.isEmpty()) {
         
           //out.println("list:" + list.toString());
           //不暴露集合内部实现的情况下遍历集合元素的方法,可用于迭代 ArrayList 和 HashSet 等集合	 
           Iterator it = list.iterator();   
           while(it.hasNext()) {   
               hm = (Map)it.next();   
               user= (String) hm.get("username");   
               //paw= (String) hm.get("password");
               admin= (String) hm.get("adminstrator");
           }  
           out.println("登录成功！欢迎用户"+user);
			
			  if(admin.equals("true")) {
				  int number = 0;
				  
			      String gson1 = gson.toJson(hm.get("username")); 
				  out.println(gson1);
				  out.println(number);	  
			  
			  } else {
				  int number = 1;
				 
				  String gson1 = gson.toJson(hm.get("username")); 
				  out.println(gson1);
				  out.println(number);
				  
			  }
			 
			
		}else {
		
	        out.write("登录失败！检查用户或者密码是否正确");
	           
		   }
         }catch (SQLException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			out.write("查询出错！");
		}
	
           out.flush();
           out.close();
 
		//doGet(request, response);
	}

}
