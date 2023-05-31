package web_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Time")
public class Time extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取毫秒时间戳，但效率低
		Date d = new Date();

		// out.println("<meta charset=\"utf-8\">");
		// 没有格式的毫秒数时间戳
		out.println(d.getTime());
		out.flush();
		out.close();

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// 获取毫秒时间戳
		long time = System.currentTimeMillis();
		int t = (int) (time / 1000);// 秒的时间戳
		// int t = (int) (time);// 毫秒的时间戳

		out.print(t);
		out.flush();
		out.close();

		doGet(request, response);
	}

}
