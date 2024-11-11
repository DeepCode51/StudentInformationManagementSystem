package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Page;
import model.Score;

//成绩表数据库基础操作
public class ScoreDao extends BaseDao {

	//成绩录入
	public boolean addScore(Score score ){
		String sql = "insert into s_score values(null,"+score.getStudentId()+","+score.getCourseId()+","+score.getScore()+",'"+score.getRemark()+"')";
	    return update(sql);
	}
	//删除成绩
	public boolean deleteScore(int id){
		String sql ="delete from s_score where id = "+id;
		return update(sql);
	}
	//修改成绩
	public boolean editScore(Score score){
		String sql = "update s_score set student_id = " + score.getStudentId();
		sql += ",course_id = " + score.getCourseId();
		sql += ",score = " + score.getScore();
		sql += ",remark = '" + score.getRemark() + "'";
		sql += " where id = " + score.getId();
		return update(sql);
	}
	//判断成绩是否录入
	public boolean isAdd(int studentId,int courseId){
		String sql = "select * from s_score where student_id = "+studentId+" and course_id = "+courseId;
		ResultSet query = query(sql);
		try {
			if(query.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//分页获取成绩列表
	public List<Score> getScoreList(Score score,Page page){
		List<Score> ret = new ArrayList<Score>();
		String sql = "select * from s_score ";
		if(score.getStudentId() != 0){
			sql += " and student_id = " + score.getStudentId();
		}
		if(score.getCourseId() != 0){
			sql += " and course_id = " + score.getCourseId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		sql = sql.replaceFirst("and", "where");
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Score s = new Score();
				s.setId(resultSet.getInt("id"));
				s.setCourseId(resultSet.getInt("course_id"));
				s.setStudentId(resultSet.getInt("student_id"));
				s.setScore(resultSet.getDouble("score"));
				s.setRemark(resultSet.getString("remark"));
				ret.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	//获取符合某一条件的所有成绩列表
	public List<Map<String, Object>> getScoreList(Score score){
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		String sql = "select s_score.*,s_student.name as studentName,s_course.name as courseName from s_score,s_student,s_course where s_score.student_id=s_student.id and s_score.course_id=s_course.id ";
		if(score.getStudentId()!=0){
			sql += "and student_id = "+score.getStudentId();
		}
		if(score.getCourseId()!=0){
			sql += "and course_id = "+score.getCourseId();
		}
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Map<String, Object> s = new HashMap<String, Object>();
				s.put("id",resultSet.getInt("id"));
				s.put("courseId",resultSet.getInt("course_id"));
				s.put("studentId",resultSet.getInt("student_id"));
				s.put("score",resultSet.getDouble("score"));
				s.put("remark",resultSet.getString("remark"));
				s.put("studentName", resultSet.getString("studentName"));
				s.put("courseName", resultSet.getString("courseName"));
				ret.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	//获取成绩总记录数
	public int getScoreListTotal(Score score) {
		// TODO Auto-generated method stub
		int total = 0;
		String sql = "select count(*) as total from s_score ";
		if(score.getStudentId() != 0){
			sql += " and student_id = " + score.getStudentId();
		}
		if(score.getCourseId() != 0){
			sql += " and course_id = " + score.getCourseId();
		}
		sql = sql.replaceFirst("and", "where");
		ResultSet resultSet = query(sql);
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
	//计算学生平均成绩
	public Map<String, Object> getAvgStats(Score score){
		Map<String,Object> ret = new HashMap<String, Object>();
		String sql = "select max(s_score.score) as max_score,avg(s_score.score) as avg_score,min(s_score.score) as min_score,s_course.name as courseName from s_score,s_course where s_score.course_id=s_course.id and s_score.course_id = " + score.getCourseId();
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				ret.put("max_score",resultSet.getDouble("max_score"));
				ret.put("avg_score",resultSet.getDouble("avg_score"));
				ret.put("min_score",resultSet.getDouble("min_score"));
				ret.put("courseName", resultSet.getString("courseName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
