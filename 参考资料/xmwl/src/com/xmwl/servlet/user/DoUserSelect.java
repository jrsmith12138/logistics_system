package com.xmwl.servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmwl.entity.XMWL_USER;
import com.xmwl.service.XMWL_USERDao;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cpage=1;//当前页
		int count=5;//每页条数
		//获取用户指定页面
		String cp=request.getParameter("cp");
		
		//接受用户搜索关键字
		String keyword=request.getParameter("keywords");
		
		if(cp!=null)
		{
			cpage=Integer.parseInt(cp);
		}
		int arr[]=XMWL_USERDao.totalPage(count);
		//获取所有用户列表
		ArrayList<XMWL_USER> list=XMWL_USERDao.selectAll(cpage,count,keyword);
		
		//放回请求对象域中
		request.setAttribute("userlist", list);
		request.setAttribute("tsum", arr[0]);
		request.setAttribute("tpage", arr[1]);
		request.setAttribute("cpage", cpage);
		//转发
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);;

	}

}
