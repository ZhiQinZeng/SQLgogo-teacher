package bean;

public class Pra_sub {
   private int pra_id;
   public int getPra_id() {
	return pra_id;
}
public void setPra_id(int pra_id) {
	this.pra_id = pra_id;
}
public int getSub_id() {
	return sub_id;
}
public void setSub_id(int sub_id) {
	this.sub_id = sub_id;
}
public int getDeleted() {
	return deleted;
}
public void setDeleted(int deleted) {
	this.deleted = deleted;
}
   private int sub_id;
   private int deleted;
@Override
public String toString() {
	return "Pra_sub [pra_id=" + pra_id + ", sub_id=" + sub_id + ", deleted=" + deleted + "]";
}
   
}
