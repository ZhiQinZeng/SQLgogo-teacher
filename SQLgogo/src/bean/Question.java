package bean;

import java.util.Date;

public class Question {
	private int QuestionId;
	public int getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(int questionId) {
		QuestionId = questionId;
	}
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentsNumber() {
		return studentsNumber;
	}
	public void setStudentsNumber(int studentsNumber) {
		this.studentsNumber = studentsNumber;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getQuestionProtray() {
		return questionProtray;
	}
	public void setQuestionProtray(String questionProtray) {
		this.questionProtray = questionProtray;
	}
	private String studentName;
	private int studentsNumber;
	private Date addtime;
	private String questionProtray;
	private String className;
	private int star;
	private int concern;
	private String questionDeal;
	private String filepath;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getQuestionDeal() {
		return questionDeal;
	}
	public void setQuestionDeal(String questionDeal) {
		this.questionDeal = questionDeal;
	}
	public int getConcern() {
		return concern;
	}
	public void setConcern(int concern) {
		this.concern = concern;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int concern) {
		this.star = concern;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
