package web_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mysql.UseServer;
import object.Userlots;
import object.commodity;
import object.lost;

@WebServlet("/quote")
public class quote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
		Map hm =null ;
	    Gson gson = new Gson();	 
		 
	    String LPrice = request.getParameter("Price") ;
	    String Lname = request.getParameter("Lname") ;
	    String name = request.getParameter("user") ;
	    System.out.println(name);
	    if( !name.equals("undefined") && name!="" && name!=null ) {
	    //修改竞拍价
	    String sq1 ="UPDATE lots SET LPrice ={0}  WHERE Lname = '{1}'";
	    sq1=sq1.replace("{0}", LPrice);
	    sq1=sq1.replace("{1}", Lname);
        //查询用户是否参与过
	    String sq2 ="SELECT*FROM temporarily WHERE Uname='{0}'AND Ugoods='{1}'";
	    sq2=sq2.replace("{0}", name);
	    sq2=sq2.replace("{1}", Lname);
	    //竞价暂存
	    String sq3 ="INSERT INTO temporarily (Uname,Ugoods,bid) VALUES ('{0}','{1}',{2})";
	    sq3=sq3.replace("{0}", name);
	    sq3=sq3.replace("{1}", Lname);
	    sq3=sq3.replace("{2}", LPrice);
	    //更新用户竞价
	    String sq4 ="UPDATE temporarily SET bid = {0}  WHERE Uname = '{1}' AND Ugoods='{2}'";
	    sq4=sq4.replace("{0}", LPrice);
	    sq4=sq4.replace("{1}", name);
	    sq4=sq4.replace("{2}", Lname);
	     //修改当前的竞拍价
	     try {
	    	 
		     int req=accontDao.UpdateSql(sq1);
	         if(req != 0 && !(req <0)) {      
 
					 out.println("您的报价是￥"+LPrice+"参与竞价请时刻留意哦~！！");
					 List req2=accontDao.DiySql(sq2);
					 
					 if(req2 != null && !req2.isEmpty()) {
						 
						 int req4=accontDao.UpdateSql(sq4);
								 
							
						}else {
						
							 int req3=accontDao.UpdateSql(sq3);
							 					        
						   }
					 	    
										 				
			}else {
			
		        out.write("数据出错，没有成功参与竞拍！！");
		           
			   }
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("系统出错！");
			}
	    }else {
	    	out.write("您还未登录！！登录后才能参与！！");
		}
	     out.flush();
         out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
	    Gson gson = new Gson();	
		Userlots customer = new Userlots();
		
		//接收json字符串 
		 BufferedReader reader = request.getReader();
	     String json = reader.readLine();
	     reader.close();
		
	     //json转换成Java对象,kay和对象属性保持一致
	     commodity comm = gson.fromJson(json, commodity.class);	
         
         //保存商品
 	    String sql ="INSERT INTO commodity (Cname,information,Price,url,type,detail) \r\n"
 	    		+ "VALUES ('{0}','{1}',{2},'{3}','{4}','{5}')";
 	     sql=sql.replace("{0}", comm.getCname());
 	     sql=sql.replace("{1}", comm.getinformation());
 	     sql=sql.replace("{2}", comm.getprice());
 	     sql=sql.replace("{3}", comm.geturl());
 	     sql=sql.replace("{4}", comm.gettype());
 	     sql=sql.replace("{5}", comm.getdetail());
 	     
 	  //查询商品是否添加过
 	    String sq2 ="SELECT*FROM commodity WHERE Cname='{0}' OR information='{1}' or  url='{2}'";
 	    sq2=sq2.replace("{0}", comm.getCname());
 	    sq2=sq2.replace("{1}", comm.getinformation());
 	    sq2=sq2.replace("{1}", comm.geturl());
 	    
		try {
			 List req2=accontDao.DiySql(sq2);
		 if(req2 != null && !req2.isEmpty()) {
			
			 out.println("商品存在，请更改信息再次添加！！");
			 
			}else {
				
				int req = accontDao.UpdateSql(sql);
				 if(req != 0 && !(req <0)) {     
			        	
			         out.println("商品添加成功！！");
			        	
			        }else {
			        	out.println("商品添加失败！！请重新尝试或者联系管理员");
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
