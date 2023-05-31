package web_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/Req_commodity")
public class Req_commodity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    Gson gson = new Gson();
	    
	    String Cname = request.getParameter("Cname") ;
	    String user = request.getParameter("user") ;
	    
	  //查询商品信息
	    String sql ="SELECT *FROM commodity WHERE Cname='{0}'";
	    sql=sql.replace("{0}", Cname);
	    
	    try {
		     List list=accontDao.DiySql(sql);
	         if(list != null && !list.isEmpty() && user.equals("undefined")) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					  System.out.println(gson1);
				
			}
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("查询出错！");
			}
		   
	    if(!user.equals("undefined") && user!="") {
	    	//查询用户是否拥有
		    String sq1 ="SELECT *FROM `shopping cart` WHERE `name`='{0}' && `ugoods`='{0}'";
		    sq1=sq1.replace("{0}", user);
		    sq1=sq1.replace("{1}", Cname);
		    try {
		    List req=accontDao.DiySql(sq1);
	    	if(req != null && !req.isEmpty()) {
	    		
	    		out.println("您已拥有！！请勿重复购买");
	    	}else {
	    		//给用户购物车添加商品
			    String sq2 ="INSERT INTO `shopping cart` (`name`,Ugoods) VALUES ('{0}','{1}')";
			    sq2=sq2.replace("{0}", user);
			    sq2=sq2.replace("{1}", Cname);
			    
			   
				int req1 = accontDao.UpdateSql(sq2);
				if(req1 != 0 && !(req1 <0)) {
					out.println("商品已经添加到您的购物车！！");
				}
	    	  }
			} catch (ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
			}
	    		
		}
	  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    
	    String Name = request.getParameter("Name") ;
	    String Paw = request.getParameter("Paw") ;
	    
	    //查询用户是否存在
	    String sql ="SELECT *FROM `user` WHERE username='{0}' && `password`='{1}'";
	    sql=sql.replace("{0}", Name);
	    sql=sql.replace("{1}", Paw);
	    
	  //添加用户账户
	    String sq2 ="INSERT INTO `user` (username,`password`,adminstrator) VALUES ('{0}','{1}','false')";
	    sq2=sq2.replace("{0}", Name);
	    sq2=sq2.replace("{1}", Paw);
	    
		try {
			List list = accontDao.DiySql(sql);
			  if(list != null && !list.isEmpty()) {
				  
				  out.println("用户存在！！，请更换账户与密码");
				 
		        }else {
					
		        int req = accontDao.UpdateSql(sq2);
				  if(req != 0 && !(req <0)) {
					  
					out.println("您的账户添加成功！！");
					}else {
					
					out.println("账户添加出现错误！！请重试或联系管理员");
					}
				}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
      
		
	}
	public void server() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");

			System.out.println("成功加载Mysql驱动程序!");
		} catch (Exception e) {
			System.out.println("加载Mysql驱动程序时出错!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysource", "root", "root");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			System.out.println("成功连接Mysql服务器!");
		} catch (Exception e1) {
			System.out.print("获取数据错误!");
			e1.printStackTrace();

		}

	}

}
