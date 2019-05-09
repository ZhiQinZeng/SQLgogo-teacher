package bean;

public class TeaUser {
	private int id;
	private long name;
	private String password;
	private String realname;
	private String e_mail;
	private String phone_num;
	private String sex;
	private String major;
	private String company;
	private Object profile;
	
	
	
	public TeaUser(int id, long name, String password, String realname, String e_mail, String phone_number, String sex,
			String major, String company,String profile) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.realname = realname;
		this.e_mail = e_mail;
		this.phone_num = phone_num;
		this.sex = sex;
		this.major = major;
		this.company = company;
		this.profile = profile;
	}
	

	public Object getProfile() {
		return profile;
	}


	public void setProfile(Object profile) {
		this.profile = profile;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getE_mail() {
		return e_mail;
	}


	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}


	public String getPhone_num() {
		return phone_num;
	}


	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public TeaUser(){
		
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getName() {
		return name;
	}


	public void setName(long name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
