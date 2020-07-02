package com.xmwl.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmwl.entity.XMWL_USER;
import com.xmwl.service.XMWL_USERDao;

/**
 * Servlet implementation class DoUserAdd
 */
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("userName");
		String pwd=request.getParameter("passWord");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String year=request.getParameter("birthday");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		//创建用户实体
		XMWL_USER u=new XMWL_USER(username,pwd,name,sex,year,email,mobile,address,1,null);
		//加入用户表中
		int count=XMWL_USERDao.insert(u);
		//成功或失败重定向
		if(count>0){
			response.sendRedirect("admin_douserselect");	
		}
		else
		{
			PrintWriter out=response.getWriter();
			out.write("<script>");
			out.write("alert('用户添加失败')");
			out.write("location.href='manage/admin_useradd.jsp'");
			out.write("</script>");
		}
	}

}
