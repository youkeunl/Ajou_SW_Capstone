package work.sitter;

public class SitterDTO {
	
	private String name;
	private String id;
	private String pass;
	private String address;
	private int price_day;
	private int price_night;
	private int age;
	
	
	
	public int getPrice_day() {
		return price_day;
	}
	public void setPrice_day(int price_day) {
		this.price_day = price_day;
	}
	public int getPrice_night() {
		return price_night;
	}
	public void setPrice_night(int price_night) {
		this.price_night = price_night;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

	

}
