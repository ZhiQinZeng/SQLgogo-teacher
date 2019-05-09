package bean;

public class Ques {
	public int id;
	public String quename;
	public String quecontext;
	public String belongto;
	public String thedate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuename() {
		return quename;
	}
	public void setQuename(String quename) {
		this.quename = quename;
	}
	public String getQuecontext() {
		return quecontext;
	}
	public void setQuecontext(String quecontext) {
		this.quecontext = quecontext;
	}
	public String getBelongto() {
		return belongto;
	}
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}
	public String getThedate() {
		return thedate;
	}
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	@Override
	public String toString() {
		return "Ques [id=" + id + ", quename=" + quename + ", quecontext=" + quecontext + ", belongto=" + belongto
				+ ", thedate=" + thedate + "]";
	}
	
	
}
