package dao;
/**
 * 管理员数据封装
 * @author CesareBorgia
 */

import model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends BaseDao{
    public Admin login(String name,String password){
    	String sql = "select * from s_admin where name = '" + name + "' and password = '" + password + "'";
    	ResultSet ret = query(sql);
    	try {
			if(ret.next()){
				Admin admin = new Admin();
				admin.setId(ret.getInt("id"));
				admin.setName(ret.getString("name"));
				admin.setPassword(ret.getString("password"));
				admin.setStatus(ret.getInt("status"));
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public boolean editPassword(Admin admin,String newPassword){
    	String sql = "update s_admin set password = '"+newPassword+"' where id = " + admin.getId();
    	return update(sql);
    }
}
