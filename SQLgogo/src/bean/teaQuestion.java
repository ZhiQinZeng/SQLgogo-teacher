package bean;

import java.util.Date;

public class teaQuestion {
   public int getTeaQuestionId() {
		return teaQuestionId;
	}
	public void setTeaQuestionId(int teaQuestionId) {
		this.teaQuestionId = teaQuestionId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public int getTeaNumber() {
		return teaNumber;
	}
	public void setTeaNumber(int teaNumber) {
		this.teaNumber = teaNumber;
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
	public String getQuestionDeal() {
		return questionDeal;
	}
	public void setQuestionDeal(String questionDeal) {
		this.questionDeal = questionDeal;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getConcern() {
		return concern;
	}
	public void setConcern(int concern) {
		this.concern = concern;
	}
private int teaQuestionId;
    private String className;
    private String teaName;
    private int teaNumber;
    private Date addtime;
    private String questionProtray;
    private String questionDeal;
    private int star;
    private int concern;
    private String photo;
    public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getQesName() {
		return qesName;
	}
	public void setQesName(String qesName) {
		this.qesName = qesName;
	}
	private String qesName;

}
