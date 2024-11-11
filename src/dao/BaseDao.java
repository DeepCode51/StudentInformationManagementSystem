package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
//封装应用程序基本操作对数据库的基本操作
public class BaseDao {
	private DbUtil dbUtil = new DbUtil();
	
	//关闭数据库连接，关闭资源
	public void closeCon(){
		dbUtil.closeCon();
	}
    //基础查询:多条件查询
	public ResultSet query(String sql){
		try {
			PreparedStatement pstmt = dbUtil.getConnection().prepareStatement(sql);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	//改变数据库内容操作
	public boolean update(String sql)
	{
		try {
			return dbUtil.getConnection().prepareStatement(sql).executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Connection getConnection(){
		return dbUtil.getConnection();
	}
}
