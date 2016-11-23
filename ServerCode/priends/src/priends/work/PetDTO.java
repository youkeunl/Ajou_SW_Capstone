package priends.work;

public class PetDTO {
	
	private String Pet_ID;
	private String name;
	private String sex;
	private String type;
	private String species;
	private int age;
	private String character;
	private String health;
	private String caution;
	private String Owner_PetSitterID;
	private String Owner_PetMomID;
	
	public String getPet_ID() {
		return Pet_ID;
	}
	public void setPet_ID(String pet_ID) {
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
	public String getOwner_PetSitterID() {
		return Owner_PetSitterID;
	}
	public void setOwner_PetSitterID(String owner_PetSitterID) {
		Owner_PetSitterID = owner_PetSitterID;
	}
	public String getOwner_PetMomID() {
		return Owner_PetMomID;
	}
	public void setOwner_PetMomID(String owner_PetMomID) {
		Owner_PetMomID = owner_PetMomID;
	}
}
