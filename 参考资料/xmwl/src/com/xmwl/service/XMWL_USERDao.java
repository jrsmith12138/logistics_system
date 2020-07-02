package com.xmwl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xmwl.dao.Basedao;
import com.xmwl.entity.XMWL_USER;

public class XMWL_USERDao {
	//加入数据库
	public static int insert(XMWL_USER u){
		String sql="insert into XMWL_USER values(?,?,?,?,DATE_FORMAT(?,'%Y-%m-%d'),?,?,?,?,?)";
		Object[] params={
				u.getUSER_ID(),
				u.getUSER_PASSWORD(),
				u.getUSER_NAME(),
				u.getUSER_SEX(),
				u.getUSER_BIRTHDAY(),
				u.getUSER_EMAIL(),
				u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(),
				u.getUSER_STATUS(),
				u.getUSER_IDENITY_CODE()				
		};
		return Basedao.exectuIUD(sql, params);
	}
	
	//获取总记录数和总页数
	public static int[] totalPage(int count){
		int arr[]={0,1};//0总记录。1页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		ResultSet rs=null;
				
		try {
			String sql="select count(*) from XMWL_USER";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				arr[0]=rs.getInt(1);
				if(arr[0]%count==0){
					arr[1]=arr[0]/count;
				}
				else {
					arr[1]=arr[0]/count+1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	
	
	//查询所有
	public static ArrayList<XMWL_USER> selectAll(int cpage,int count,String keyword){
		ArrayList<XMWL_USER> list=new ArrayList<XMWL_USER>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		
		try {
			String sql="";
			if(keyword!=null)
			{
				sql="select * from `XMWL_USER` where USER_NAME like ? limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
				ps.setInt(2, (cpage-1)*count);
				ps.setInt(3,count);
			}else
			{
				sql="select * from `XMWL_USER` limit ?,?";
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, (cpage-1)*count);
				ps.setInt(2,count);
			}
			 			
			rs=ps.executeQuery();
			while(rs.next()){
				XMWL_USER u=new XMWL_USER(rs.getString("USER_ID"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_NAME"),
						rs.getString("USER_SEX"),
						rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS"),
						rs.getString("USER_IDENITY_CODE")
						);
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeall(rs, ps, conn);
		}
		return list;
				//
	}

}
