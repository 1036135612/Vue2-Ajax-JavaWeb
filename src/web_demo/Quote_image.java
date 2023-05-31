package web_demo;

import java.io.IOException;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;

import mysql.UseServer;
import object.Userlots;
import object.commodity;
@WebServlet("/Quote_image")
@MultipartConfig
public class Quote_image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UseServer accontDao = new UseServer();
		Map hm =null ;
		Gson gson = new Gson();	
		
		//查询商品
	    String sql ="SELECT *FROM commodity ";
	    
	    try {
		     List list=accontDao.DiySql(sql);
	         if(list != null && !list.isEmpty()) {
	                
				      String gson1 = gson.toJson(list); 				     
					  out.println(gson1);
					 
				
			}else {
			
		        out.write("没有获取到相关数据");
		           
			   }
	         }catch (SQLException | ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				out.write("查询出错！");
			}
		   
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  //接收js上传文件
				Part filePart = request.getPart("file");        
			    String fileName = filePart.getSubmittedFileName();
			    System.out.println(filePart);
		
			    filePart.write("E:\\java文件\\VueJava\\webapp\\png\\"+fileName);
		
	  }
	public static void main(String[] args) {
		
		Quote_image nn =new Quote_image();
		nn.TimerShift();
		
	}
	public void TimerShift() {
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            
            	System.out.println("定时器执行完成");
            	timer.cancel();
            }
        },3000);			
	}


}