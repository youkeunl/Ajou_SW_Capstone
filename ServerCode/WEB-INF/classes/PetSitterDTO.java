package priends.work;

public class PetSitterDTO {
	
	private String PetSitter_ID;
	private String PetSitter_Pwd;
	private String name;
	private String sex;
	private String job;
	private int career;
	private String certification;
	private int age;
	private String character;
	private String address;
	private double point_x;
	private double point_y;
	private int price_day;
	private int price_night;
	private int distance;
	private int priority;
	private double recommend;
	private String image;
	private String home1;
	private String home2;
	private String home3;
	private String [] calender = new String [32];
	
	public String[] getCalender() {
		return calender;
	}
	public void setCalender(String[] calender) {
		this.calender = calender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHome1() {
		return home1;
	}
	public void setHome1(String home1) {
		this.home1 = home1;
	}
	public String getHome2() {
		return home2;
	}
	public void setHome2(String home2) {
		this.home2 = home2;
	}
	public String getHome3() {
		return home3;
	}
	public void setHome3(String home3) {
		this.home3 = home3;
	}
	public double getRecommend() {
		return recommend;
	}
	public void setRecommend(double recommend) {
		this.recommend = recommend;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getPetSitter_ID() {
		return PetSitter_ID;
	}
	public void setPetSitter_ID(String petSitter_ID) {
		PetSitter_ID = petSitter_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getCareer() {
		return career;
	}
	public void setCareer(int career) {
		this.career = career;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPoint_x() {
		return point_x;
	}
	public void setPoint_x(double point_x) {
		this.point_x = point_x;
	}
	public double getPoint_y() {
		return point_y;
	}
	public void setPoint_y(double point_y) {
		this.point_y = point_y;
	}
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
	public String getPetSitter_Pwd() {
		return PetSitter_Pwd;
	}
	public void setPetSitter_Pwd(String petSitter_Pwd) {
		PetSitter_Pwd = petSitter_Pwd;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}