public class Course {
	// Attributes
	String name;
	String fullName;
	int numCredits;
	int offerYear;
	int offerSemester;
	int[] gradingScale;
	double runningAvg;
	int numMeetings;
	char grade;
	boolean passed;

	// constructor
	public Course(String name, String fullName, int numCredits, 
			int offerYear, int offerSemester, int[] gradingScale) {
		setName(name);
		setFullName(fullName);
		setNumCredits(numCredits);
		setOfferYear(offerYear);
		setOfferSemester(offerSemester);
		setGradingScale(gradingScale);
		runningAvg = 0;
		numMeetings = 0;
		grade = '\000';
		passed = false;
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

	// accessor runningAvg;
	public double getRunningAvg() {
		return runningAvg;
	}

	// accessor grade
	public char getGrade() {
		return grade;
	}

	// accessor passed
	public boolean getPassed() {
		return passed;
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

	// mutator runningAvg
	public Course setRunningAvg(double runningAvg) {
		this.runningAvg = runningAvg;
		return this;
	}

	// mutator grade
	public Course setGrade(char grade) {
		this.grade = grade;
		return this;
	}

	// mutator passed
	public Course setPassed(boolean passed) {
		this.passed = passed;
		return this;
	}

	// reset method
	public void reset() {
		setRunningAvg(0);
		setGrade('\000');
		numMeetings = 0;
	}

	// calcRunningAvg method
	public void calcRunningAvg(int school) {
		if (numMeetings == 0) {
			setRunningAvg(school);
		}
		else {
			double prevSum = getRunningAvg() * numMeetings;
			setRunningAvg((prevSum + school) / (numMeetings + 1));
		}
		assignGrade();
		numMeetings++;
	}

	// assignGrade method
	public void assignGrade() {
		int A = gradingScale[0];
		int B = gradingScale[1];
		int C = gradingScale[2];
		int D = gradingScale[3];
		int E = gradingScale[4];
		
		if (getRunningAvg() >= A) {
			setGrade('A');
			setPassed(true);
		}
		else if (getRunningAvg() >= B) {
			setGrade('B');
			setPassed(true);
		}
		else if (getRunningAvg() >= C) {
			setGrade('C');
			setPassed(true);
		}
		else if (getRunningAvg() >= D) {
			setGrade('D');
			setPassed(false);
		}
		else if (getRunningAvg() >= E) {
			setGrade('E');
			setPassed(false);
		}
		else {
			setGrade('F');
			setPassed(false);
		}
	}

	// toString method
	@Override
	public String toString() {
		String str = getName() + " - " + getFullName() 
			+ " (Credits: " + getNumCredits() + ")";
		
		if (getGrade() != '\000') {
			str += " (Previous grade: " + getGrade() + ")";
		}

		return str;
	}
}

