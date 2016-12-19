package priends.work;

public class PetDTO {
	
	private String name;
	private int Pet_ID;
	private String sex;
	private String type;
	private String species;
	private int age;
	private String character;
	private String health;
	private String caution;
	private String Owner_PetMomID;
	private String Match_PetSitterID;
	private String Start_Date;
	private String End_Date;
	private int state;
	private String image;
	
	public int getState() {
		return state;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMatch_PetSitterID() {
		return Match_PetSitterID;
	}
	public void setMatch_PetSitterID(String match_PetSitterID) {
		Match_PetSitterID = match_PetSitterID;
	}
	public String getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(String start_Date) {
		Start_Date = start_Date;
	}
	public String getEnd_Date() {
		return End_Date;
	}
	public void setEnd_Date(String end_Date) {
		End_Date = end_Date;
	}
	public int getPet_ID() {
		return Pet_ID;
	}
	public void setPet_ID(int pet_ID) {
		Pet_ID = pet_ID;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
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
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getCaution() {
		return caution;
	}
	public void setCaution(String caution) {
		this.caution = caution;
	}
	public String getOwner_PetMomID() {
		return Owner_PetMomID;
	}
	public void setOwner_PetMomID(String owner_PetMomID) {
		Owner_PetMomID = owner_PetMomID;
	}
}
