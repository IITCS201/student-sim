public class Location {
	// Attributes
	String name;
	int[] pos;
	int staminaModifier;
	int schoolModifier;
	int socialModifier;
	int moneyModifier;

	// constructor
	public Location(String name, int[] pos, int staminaModifier, int schoolModifier, int socialModifier, int moneyModifier) {
		setName(name);
		setPos(pos);
		setStaminaModifier(staminaModifier);
		setSchoolModifier(schoolModifier);
		setSocialModifier(socialModifier);
		setMoneyModifier(moneyModifier);
	}

	// accessor name
	public String getName() {
		return name;
	}

	// accessor pos
	public int[] getPos() {
		return pos;
	}

	// accessor staminaModifier
	public int getStaminaModifier() {
		return staminaModifier;
	}

	// accessor schoolModifier
	public int getSchoolModifier() {
		return schoolModifier;
	}

	// accessor socialModifier
	public int getSocialModifier() {
		return socialModifier;
	}

	// accessor moneyModifier
	public int getMoneyModifier() {
		return moneyModifier;
	}

	// mutator name
	public Location setName(String name) {
		this.name = name;
		return this;
	}

	// mutator pos
	public Location setPos(int[] pos) {
		this.pos = pos;
		return this;
	}

	// mutator staminaModifer
	public Location setStaminaModifier(int staminaModifier) {
		this.staminaModifier = staminaModifier;
		return this;
	}

	// mutator schoolModifier
	public Location setSchoolModifier(int schoolModifier) {
		this.schoolModifier = schoolModifier;
		return this;
	}

	// mutator socialModifier
	public Location setSocialModifier(int socialModifier) {
		this.socialModifier = socialModifier;
		return this;
	}

	// mutator moneyModifier
	public Location setMoneyModifier(int moneyModifier) {
		this.moneyModifier = moneyModifier;
		return this;
	}

	// sleep method
	
	// study method
	
	// socialize method
	
	// work method
	
	public static void main(String[] args) {
	}
}

