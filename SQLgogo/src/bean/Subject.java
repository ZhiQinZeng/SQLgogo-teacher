package bean;

import java.io.Serializable;

public class Subject  implements Serializable{
    
     public int getSubjectId() {
		return SubjectId;
	}
	public void setSubjectId(int subjectId) {
		SubjectId = subjectId;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	public int getScores() {
		return Scores;
	}
	public void setScores(int scores) {
		Scores = scores;
	}
	public int getExamScores() {
		return ExamScores;
	}
	public void setExamScores(int examScores) {
		ExamScores = examScores;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	public String getMethod() {
		return Method;
	}
	public void setMethod(String method) {
		Method = method;
	}
	private int SubjectId;
	@Override
	public String toString() {
		return "subject_detail="
				+ subject_detail +",subject_answer=" + subject_answer + ",subject_name="+SubjectName+"";
	}
	private  String SubjectName;
     private int Scores;
     private int ExamScores;
     private int Total;
     private String Method;
     private String subject_detail;
     private String subject_answer;
     private int deleted;
     private int ques_id;
     private String key_choice;
     private int set_score;
     private int setpro_score;
     private int setres_score;
   
	public int getSet_score() {
		return set_score;
	}
	public void setSet_score(int set_score) {
		this.set_score = set_score;
	}
	public int getSetpro_score() {
		return setpro_score;
	}
	public void setSetpro_score(int setpro_score) {
		this.setpro_score = setpro_score;
	}
	public int getSetres_score() {
		return setres_score;
	}
	public void setSetres_score(int setres_score) {
		this.setres_score = setres_score;
	}
	public String getKey_choice() {
		return key_choice;
	}
	public void setKey_choice(String key_choice) {
		this.key_choice = key_choice;
	}
	public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getSubject_detail() {
		return subject_detail;
	}
	public void setSubject_detail(String subject_detail) {
		this.subject_detail = subject_detail;
	}
	public String getSubject_answer() {
		return subject_answer;
	}
	public void setSubject_answer(String subjet_answer) {
		this.subject_answer = subjet_answer;
	}
	
    

	
}
