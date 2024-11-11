package model;
//请假表实体类
public final class Leave {
	
	public static int LEAVE_STATUS_WAIT = 0;//等待
	public static int LEAVE_STATUS_AGREE = 1;//同意
	public static int LEAVE_STATUS_DISAGREE = -1;//不同意
	private int id;
	private int studentId;
	private String info;//请假理由
	private int status = LEAVE_STATUS_WAIT;//请假状态
	private String remark;//批复
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
