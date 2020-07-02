package com.xmwl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Basedao {
	static{
		//加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//连接
	public static Connection getconn()
	{
		//创建一个连接对象
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/xmwl?useSSL=false&serverTimezone=UTC","root","ps8023");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//增删改
	public static int exectuIUD(String sql,Object[] params){
		int count=0;
		Connection conn=Basedao.getconn();
		//准备sql
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			//绑定参数
			for(int i=0;i<params.length;i++)
			{
				ps.setObject(i+1, params[i]);
			}
			//执行
			count =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeall(null,ps,conn);
		}
		return count;
	}
	//退出
	public static void closeall(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
