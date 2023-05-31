package web_demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import mysql.UseServer;
import object.lost;

@WebServlet("/UserQuote")
@MultipartConfig
public class UserQuote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
		Gson gson = new Gson();
		
	    String flag = request.getParameter("flag");
	    String rname = request.getParameter("rname");
	    String rurl = request.getParameter("rurl");
	    
	    
	  if(flag.equals("LL")) { 
	  //查询拍品评论
	    String sql ="SELECT * FROM  remarks \r\n"
	    		+ "INNER JOIN lots\r\n"
	    		+ "ON remarks.rname = lots.Lname &&remarks.rurl=lots.url\r\n"
	    		+ "WHERE remarks.rname='{0}' && remarks.rurl='{1}'";
	    sql=sql.replace("{0}", rname);
	    sql=sql.replace("{1}", rurl);
	    
			try {
				List list = accontDao.DiySql(sql);
				if(list != null && !list.isEmpty() && flag.equals("LL")) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					  System.out.println(gson1);
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
			   }
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	  } else if (flag.equals("LC")) {
		  System.out.println(rname);
		  String remarks = request.getParameter("remarks");
		  System.out.println(remarks);
		//添加拍品/商品评论
		    String sq1 ="INSERT INTO remarks (rname,rurl,remarks) VALUES ('{0}','{1}','{2}')";
		    sq1=sq1.replace("{0}", rname);
		    sq1=sq1.replace("{1}", rurl);
		    sq1=sq1.replace("{2}", remarks);
		    try {
				int reqll=accontDao.UpdateSql(sq1);
				if(reqll>0) {
					out.println("评论已添加！！");
				}
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}else if (flag.equals("CC")) {
		
		//查询商品评论
	    String sql ="SELECT * FROM  remarks \r\n"
	    		+ "INNER JOIN commodity\r\n"
	    		+ "ON remarks.rname = commodity.Cname &&remarks.rurl=commodity.url\r\n"
	    		+ "WHERE remarks.rname='{0}' && remarks.rurl='{1}'";
	    sql=sql.replace("{0}", rname);
	    sql=sql.replace("{1}", rurl);
	    System.out.println(rname);
	    System.out.println(rurl);
			try {
				List list = accontDao.DiySql(sql);
				if(list != null && !list.isEmpty() && flag.equals("CC")) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					  System.out.println(gson1);
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
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

	public int dateShift(String data ) {

		// 秒的时间戳
		long time = System.currentTimeMillis() / 1000;
	    // 取天余数，得今天过了多少秒,注意时差
		long h = (time + 8 * 60 * 60) % (24 * 60 * 60);
		long da = Long.parseLong(data);
		int dat = (int) (da-h);
		
		// 小时换算
		int h1 = (int) dat / 3600;
		// 分钟换算
		int m1 = (dat / 60) % 60;
		// 秒针换算
		int s1 = dat - (h1 * 60 * 60) - (m1 * 60);

		System.out.println("截至距离时间"+h1+"时"+m1+"分"+s1+"秒");
	    return dat ;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
      
		//接收json字符串 
		 PrintWriter out = response.getWriter();
		 BufferedReader reader = request.getReader();
	     String json = reader.readLine();
	     reader.close();
	   
	     Gson str = new Gson();
	     //json转换成Java对象,kay和对象属性保持一致
	     lost lost = str.fromJson(json, lost.class);	
	     
	     UserQuote daQuote = new UserQuote();
	     int dat =dateShift(lost.getdate());
	    
	     UseServer accontDao = new UseServer();
	   //查询拍品是否存在
		    String sq1 ="SELECT*FROM lots WHERE Lname='{0}'";
		    sq1=sq1.replace("{0}", lost.getLname());
		 
			try {
				List req1 = accontDao.DiySql(sq1);
				 if(req1 != null && !req1.isEmpty()) {
			    	 
					 out.println("拍卖品存在，添加不成功");
					 System.out.println("拍卖品存在，添加不成功！！");
			    	 
			     }else {
			    	 
						//加入拍卖
					    String sq4 = "INSERT INTO lots (Lname,wraparound,Price,detail,LPrice,url,type,date) values ('{0}','{1}',{2},'{3}',{4},'{5}','{6}',{7});";		 
					    sq4=sq4.replace("{0}",lost.getLname() );
						sq4=sq4.replace("{1}", lost.getwraparound());
						sq4=sq4.replace("{2}",lost.getprice() );
						sq4=sq4.replace("{3}", lost.getdetail());
						sq4=sq4.replace("{4}",lost.getLPrice() );
						sq4=sq4.replace("{5}", lost.geturl());
						sq4=sq4.replace("{6}",lost.gettype() );
						sq4=sq4.replace("{7}",lost.getdate() );
						int req3 = accontDao.UpdateSql(sq4);
						
						out.println("拍卖品不存在，添加成功！！");
						
			     Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {	
							try {
								//查询拍品出价最高者 
							    String sq2 ="SELECT*FROM temporarily WHERE Ugoods='{0}'AND bid=( SELECT MAX(bid)FROM temporarily WHERE Ugoods='{0}')";
							    sq2=sq2.replace("{0}", lost.getLname());
							    
								List req2 = accontDao.DiySql(sq2);
								if(req2 != null && !req2.isEmpty()) {
							    	 
									System.out.println("查询拍品出价最高者");
									Iterator it = req2.iterator();
									Map hm =null ;
							           while(it.hasNext()) {   
							        	   //指针指向下一条记录
							               hm = (Map)it.next();  
							              
							           }
							        //读取Map中的用户名
							         Object value = hm.get("Uname");
									 System.out.println("Map根据kay取值方法"+value);
									 System.out.println("Map存在的值"+hm);
									 
									 //查询是否重复
									    String sq5 ="SELECT*FROM `shopping cart` WHERE Uname='{0}'AND Ugoods='{1}'";
									    sq5=sq5.replace("{0}", (CharSequence) hm.get("Uname"));
									    sq5=sq5.replace("{1}", lost.getLname());
									    List req5 = accontDao.DiySql(sq5);
									    
									 if(req5 != null && !req5.isEmpty()) {
											
										 System.out.println("竞拍结束，写入出错！！");
										
									 }else {
										//加入出价最高者购物车    
										    String sq3 = "insert into `shopping cart` (name,ugoods) values ('{0}','{1}');";		 
										    sq3=sq3.replace("{0}", (CharSequence) hm.get("Uname"));
											sq3=sq3.replace("{1}", lost.getLname());
											System.out.println(sq3);		
											int req3 = accontDao.UpdateSql(sq3);
											System.out.println("竞拍结束，成功写入！！");
											 
									}
									
							    	 
							     }
						
							} catch (ClassNotFoundException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							
							System.out.println("竞拍结束！！");
							timer.cancel();
						}
					}, dat*1000);
				
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
