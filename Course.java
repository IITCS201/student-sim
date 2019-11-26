public class Course {
	// Attributes
	String name;
	String fullName;
	int numCredits;
	int offerYear;
	int offerSemester;
	int[] gradingScale;

	// constructor
	public Course(String name, String fullName, int numCredits, int offerYear, int offerSemester, int[] gradingScale) {
		setName(name);
		setFullName(fullName);
		setNumCredits(numCredits);
		setOfferYear(offerYear);
		setOfferSemester(offerSemester);
		setGradingScale(gradingScale);
	}

	// accessor name
	public String getName() {
		return name;
	}

	// accessor fullName
	public String getFullName() {
		return fullName;
	}

	// accessor numCredits
	public int getNumCredits() {
		return numCredits;
	}

	// accessor offerYear
	public int getOfferYear() {
		return offerYear;
	}

	// accessor offerSemester
	public int getOfferSemester() {
		return offerSemester;
	}

	// accessor gradingScale
	public int[] getGradingScale() {
		return gradingScale;
	}

	// mutator name
	public Course setName(String name) {
		this.name = name;
		return this;
	}

	// mutator fullName
	public Course setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	// mutator numCredits
	public Course setNumCredits(int numCredits) {
		this.numCredits = numCredits;
		return this;
	}

	// mutator offerYear
	public Course setOfferYear(int offerYear) {
		this.offerYear = offerYear;
		return this;
	}

	// mutator offerSemester
	public Course setOfferSemester(int offerSemester) {
		this.offerSemester = offerSemester;
		return this;
	}

	// mutator gradingScale
	public Course setGradingScale(int[] gradingScale) {
		this.gradingScale = gradingScale;
		return this;
	}

	// toString method
	@Override
	public String toString() {
		String str = "{ " + getName() + " " + getFullName() 
			+ " Credits: " + getNumCredits() + " }";

		return str;
	}
}

