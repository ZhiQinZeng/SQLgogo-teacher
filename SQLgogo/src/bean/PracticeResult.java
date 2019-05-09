package bean;

import java.util.Date;
public class PracticeResult {
      public int getPracticeId() {
		return PracticeId;
	}
	public void setPracticeId(int practiceId) {
		PracticeId = practiceId;
	}
	public String getPracticeName() {
		return PracticeName;
	}
	public void setPracticeName(String practiceName) {
		PracticeName = practiceName;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		this.ClassName = className;
	}
	public String getPracticeGenre() {
		return PracticeGenre;
	}
	public void setPracticeGenre(String practiceGenre) {
		PracticeGenre = practiceGenre;
	}
	
	
	 
	  public String getPublishDay() {
		return PublishDay;
	}
	public void setPublishDay(String publishDay) {
		PublishDay = publishDay;
	}
	public String getDeadline() {
		return Deadline;
	}
	public void setDeadline(String deadline) {
		Deadline = deadline;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public PracticeResult() {
		super();
	}
     
	  private int PracticeId;
      private String PracticeName;
      private String ClassName;
      private String PracticeGenre;
      private String PublishDay;
      private String Deadline;
      private String showDate ;
      private int classid;
     public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	private String dbname;
	private int ques_id; 
      public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}
	public int getResult_portion() {
		return result_portion;
	}
	public void setResult_portion(int result_portion) {
		this.result_portion = result_portion;
	}
	public int getProcess_portion() {
		return process_portion;
	}
	public void setProcess_portion(int process_portion) {
		this.process_portion = process_portion;
	}

	private int result_portion;
     
	@Override
	public String toString() {
		return "Practice [PracticeId=" + PracticeId + ", PracticeName=" + PracticeName + ", ClassName=" + ClassName
				+ ", PracticeGenre=" + PracticeGenre + ", PublishDay=" + PublishDay + ", Deadline=" + Deadline
				+ ", showDate=" + showDate + ", result_portion=" + result_portion + ", process_portion="
				+ process_portion + "]";
	}

	private int process_portion;
      
}
