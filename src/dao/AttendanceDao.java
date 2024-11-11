package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import model.Page;
import util.StringUtil;

/**
 * 考勤信息数据库操作
 *
 */
public class AttendanceDao extends BaseDao {
	/**
	 * 添加考勤信息
	 * @param attendance
	 * @return
	 */
	public boolean addAttendance(Attendance attendance){
		String sql = "insert into s_attendance values(null,"+attendance.getCourseId()+","+attendance.getStudentId()+",'"+attendance.getType()+"','"+attendance.getDate()+"')";
		return update(sql);
	}
	
	/**
	 * 判断当前是否已签到
	 * @param studentId
	 * @param courseId
	 * @param type
	 * @return
	 */
	public boolean isAttendanced(int studentId,int courseId,String type,String date){
		boolean ret = false;
		String sql = "select * from s_attendance where student_id = " + studentId + " and course_id = " + courseId + " and type = '" + type + "' and date = '" + date + "'";
		ResultSet query = query(sql);
		try {
			if(query.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取指定的考勤信息列表
	 * @param attendace
	 * @param page
	 * @return
	 */
	public List<Attendance> getSelectedCourseList(Attendance attendace,Page page){
		List<Attendance> ret = new ArrayList<Attendance>();
		String sql = "select * from s_attendance ";
		if(attendace.getStudentId() != 0){
			sql += " and student_id = " + attendace.getStudentId();
		}
		if(attendace.getCourseId() != 0){
			sql += " and course_id = " + attendace.getCourseId();
		}
		if(!StringUtil.isEmpty(attendace.getType())){
			sql += " and type = '" + attendace.getType() + "'";
		}
		if(!StringUtil.isEmpty(attendace.getDate())){
			sql += " and date = '" + attendace.getDate() + "'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		sql = sql.replaceFirst("and", "where");
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Attendance a = new Attendance();
				a.setId(resultSet.getInt("id"));
				a.setCourseId(resultSet.getInt("course_id"));
				a.setStudentId(resultSet.getInt("student_id"));
				a.setType(resultSet.getString("type"));
				a.setDate(resultSet.getString("date"));
				ret.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取符合条件记录总数
	 * @param attendance
	 * @return
	 */
	public int getAttendanceListTotal(Attendance attendance){
		int total = 0;
		String sql = "select count(*)as total from s_attendance ";
		if(attendance.getStudentId() != 0){
			sql += " and student_id = " + attendance.getStudentId();
		}
		if(attendance.getCourseId() != 0){
			sql += " and course_id = " + attendance.getCourseId();
		}
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deleteAttendance(int id){
		String sql = "delete from s_attendance where id = " + id;
		return update(sql);
	}
}
