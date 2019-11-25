public class Course {
	// Attributes
	String name;
	int numCredits;
	int[] gradingScale;

	// constructor
	public Course(String name, int numCredits, int[] gradingScale) {
		setName(name);
		setNumCredits(numCredits);
		setGradingScale(gradingScale);
	}

	// accessor name
	public String getName() {
		return name;
	}

	// accessor numCredits
	public int getNumCredits() {
		return numCredits;
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

	// mutator numCredits
	public Course setNumCredits(int numCredits) {
		this.numCredits = numCredits;
		return this;
	}

	// mutator gradingScale
	public Course setGradingScale(int[] gradingScale) {
		this.gradingScale = gradingScale;
		return this;
	}
	
}

