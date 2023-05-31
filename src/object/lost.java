package object;

//数据库对象
public class lost {

	private String Lname;
	private String url;
	private String wraparound;
	private String price;
	private String detail;
	private String LPrice;
	private String type;
	private String date;
	// 无参构造方法初始化
	public lost() {

	}

	public lost(String Lname, String url, String wraparound, String price,String detail,String LPrice,String type,String date) {
		// TODO 自动生成的构造函数存根
		this.Lname = Lname;
		this.url = url;
		this.wraparound = wraparound;
		this.price = price;
		this.detail = detail;
		this.LPrice = LPrice;
		this.type = type;
		this.date = date;
	}

	public String getdate() {
		return date;
	}
	
	public String getLname() {
		return Lname;
	}

	public void setLname(String Lname) {
		this.Lname = Lname;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public String getwraparound() {
		return wraparound;
	}

	public void setwraparound(String wraparound) {
		this.wraparound = wraparound;
	}

	public String getprice() {
		return price;
	}
	
	public void setdate(String date) {
		this.date = date;
	}

	public void setprice(String price) {
		this.price = price;
	}
	public String getdetail() {
		return detail;
	}

	public void setdetail(String detail) {
		this.detail = detail;
	}
	public String getLPrice() {
		return LPrice;
	}

	public void setLPrice(String LPrice) {
		this.LPrice = LPrice;
	}
	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

}
