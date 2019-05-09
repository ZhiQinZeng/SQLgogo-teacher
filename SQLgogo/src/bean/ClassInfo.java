package bean;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class ClassInfo {
	private int Id;
	private String className;
	private int teaid;
	private String teacherName;
	private Integer studentsNumber;
	private String addtime;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getStudentsNumber() {
		return studentsNumber;
	}
	public void setStudentsNumber(Integer studentsNumber) {
		this.studentsNumber = studentsNumber;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public int getTeaid() {
		return teaid;
	}
	public void setTeaid(int teaid) {
		this.teaid = teaid;
	}
	
	
	
}
