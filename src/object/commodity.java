package object;

//数据库对象
public class commodity {

	private String Cname;
	private String url;
	private String information;
	private String price;
	private String type;
	private String detail;
	// 无参构造方法初始化
	public commodity() {

	}

	public commodity(String Cname, String url, String information, 
			String price,String detail,String type) {
		// TODO 自动生成的构造函数存根
		this.Cname = Cname;
		this.url = url;
		this.information = information;
		this.price = price;
		this.type = type;
		this.detail = detail;
	}
	
	public String getdetail() {
		return detail;
	}

	public void setdetail(String detail) {
		this.detail = detail;
	}
	
	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}
	
	public String getCname() {
		return Cname;
	}

	public void setCname(String Cname) {
		this.Cname = Cname;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public String getinformation() {
		return information;
	}

	public void setinformation(String information) {
		this.information = information;
	}

	public String getprice() {
		return price;
	}
	
	public void setprice(String price) {
		this.price = price;
	}

}
