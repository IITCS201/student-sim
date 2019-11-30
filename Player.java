import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Player {
	// Attributes
	Major major;

	ArrayList<Course> coursesAvailable;
	ArrayList<Course> coursesEnrolled;
	ArrayList<Course> coursesTaken;

	Location currentLocation;

	int creditsTaken;
	int creditsLeft;

	int stamina;
	int school;
	int social;
	int money;

	int[] pos;

	int[] schedule;

	boolean gameOver;

	// constructor
	public Player() {
		major = null;

		coursesAvailable = new ArrayList<Course>();
		coursesEnrolled = new ArrayList<Course>(2);
		coursesTaken = new ArrayList<Course>();

		currentLocation = null;

		creditsTaken = 0;
		creditsLeft = 0;

		stamina = 75;
		school = 75;
		social = 75;
		money = 50;

		pos = new int[2];

		schedule = new int[] {0, 1, 0, 1, 0};

		gameOver = false;
	}

	// accessor coursesAvailable
	public ArrayList<Course> getCoursesAvailable() {
		return coursesAvailable;
	}

	// accessor coursesEnrolled
	public ArrayList<Course> getCoursesEnrolled() {
		return coursesEnrolled;
	}

	// accessor coursesTaken
	public ArrayList<Course> getCoursesTaken() {
		return coursesTaken;
	}

	// accessor creditsTaken
	public int getCreditsTaken() {
		return creditsTaken;
	}

	// accessor creditsLeft
	public int getCreditsLeft() {
		return creditsLeft;
	}

	// accessor currentLocation
	public Location getCurrentLocation() {
		return currentLocation;
	}

	// accessor stamina
	public int getStamina() {
		return stamina;
	}

	// accessor school
	public int getSchool() {
		return school;
	}

	// accessor social
	public int getSocial() {
		return social;
	}

	// accessor money
	public int getMoney() {
		return money;
	}

	// accessor pos
	public int[] getPos() {
		return pos;
	}

	// accessor schedule
	public int[] getSchedule() {
		return schedule;
	}

	// accessor gameOver
	public boolean getGameOver() {
		return gameOver;
	}

	// mutator major
	public Player setMajor(Major major) {
		this.major = major;
		return this;
	}

	// mutator coursesAvailable
	public Player setCoursesAvailable(Course[] coursesArray) {
		ArrayList<Course> coursesAvailable = 
			new ArrayList<Course>(Arrays.asList(coursesArray));
		this.coursesAvailable = coursesAvailable;
		return this;
	}

	// mutator coursesEnrolled
	

	// mutator coursesTaken


	// mutator creditsTaken
	public Player setCreditsTaken(int creditsTaken) {
		this.creditsTaken = creditsTaken;
		return this;
	}

	// mutator creditsLeft
	public Player setCreditsLeft(int creditsLeft) {
		this.creditsLeft = creditsLeft;
		return this;
	}

	// mutator currentLocation
	public Player setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
		this.pos = currentLocation.getPos();
		return this;
	}

	// mutator stamina
	public Player setStamina(int stamina) {
		this.stamina = stamina;
		return this;
	}

	// mutator school
	public Player setSchool(int school) {
		this.school = school;
		return this;
	}

	// mutator social
	public Player setSocial(int social) {
		this.social = social;
		return this;
	}

	// mutator money
	public Player setMoney(int money) {
		this.money = money;
		return this;
	}

	// mutator pos
	public Player setPos(int[] pos) {
		this.pos = pos;
		return this;
	}

	// mutator gameOver
	public Player setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		return this;
	}

	// updateStamina method
	public void updateStamina(int stamina) {
		setStamina(getStamina() + stamina);
	}

	// updateSchool method
	public void updateSchool(int school) {
		setSchool(getSchool() + school);
	}

	// updateSocial method
	public void updateSocial(int social) {
		setSocial(getSocial() + social);
	}

	// updateMoney method
	public void updateMoney(int money) {
		setMoney(getMoney() + money);
	}

	// move method
	public void move(Location destination) {
		if ((getMoney() < destination.getMoneyModifier())) {
			System.out.println("Not enough money");
			System.out.println();
			return;
		}

		// calculate distance and stamina cost
		int xDist = Math.abs(destination.getPos()[0] - getPos()[0]);
		int yDist = Math.abs(destination.getPos()[1] - getPos()[1]);
		//          Math.round returns long, so typecast as int
		int dist = (int) Math.round(Math.sqrt(xDist * xDist + yDist * yDist));
		int staminaCost = (int) Math.round((dist / 2) * (dist / 2));
		updateStamina(-staminaCost);

		// get money cost
		int moneyCost = destination.getMoneyModifier();
		updateMoney(-moneyCost);

		// set currentLocation
		setCurrentLocation(destination);

		System.out.println("You went to " + getCurrentLocation().getName());
		if (moneyCost != 0) {
			System.out.println("-" + staminaCost + " Stamina, "
					+ "-" + moneyCost + " Money");
		}
		else {
			System.out.println("-" + staminaCost + " Stamina");
		}
		System.out.println();
	}

	// sleep method
	public void sleep() {
		int staminaModifier = getCurrentLocation().getStaminaModifier();
		int otherModifier = (int) staminaModifier/2;

		if (100 - getStamina() < staminaModifier) {
			staminaModifier = 100 - getStamina();
		}
		updateStamina(staminaModifier);
		updateSchool(-otherModifier);
		updateSocial(-otherModifier);

		System.out.println("You slept.");
		System.out.println("+" + staminaModifier + " Stamina, "
				+ "-" + otherModifier + " School, " 
				+ "-" + otherModifier + " Social");
	}
	
	// study method
	public void study() {
		int schoolModifier = getCurrentLocation().getSchoolModifier();
		int otherModifier = (int) schoolModifier/2;

		updateStamina(-otherModifier);
		if (100 - getSchool() < schoolModifier) {
			schoolModifier = 100 - getSchool();
		}
		updateSchool(schoolModifier);
		updateSocial(-otherModifier);

		System.out.println("You studied.");
		System.out.println("-" + otherModifier + " Stamina, "
				+ "+" + schoolModifier + " School, " 
				+ "-" + otherModifier + " Social");
	}

	// socialize method
	public void socialize() {
		int socialModifier = getCurrentLocation().getSocialModifier();
		int otherModifier = (int) socialModifier/2;

		updateStamina(-otherModifier);
		updateSchool(-otherModifier);
		if (100 - getSocial() < socialModifier) {
			socialModifier = 100 - getSocial();
		}
		updateSocial(socialModifier);

		System.out.println("You socialized.");
		System.out.println("-" + otherModifier + " Stamina, "
				+ "-" + otherModifier + " School, " 
				+ "+" + socialModifier + " Social");
	}

	// goToWork method
	public void goToWork() {
		// gives money, takes stamina, school, and social
		int staminaModifier = 15;
		int schoolModifier = 7;
		int socialModifier = 7;
		int moneyModifier = 100;
		
		updateStamina(-staminaModifier);
		updateSchool(-schoolModifier);
		updateSocial(-socialModifier);
		updateMoney(moneyModifier);

		System.out.println("You worked.");
		System.out.println("-" + staminaModifier + " Stamina, " 
				+ "-" + schoolModifier + " School, " 
				+ "-" + socialModifier + " Social, " 
				+ "+" + moneyModifier + " Money");
	}

	// goToClass method
	public void goToClass(Course course, boolean attended) {
		if (attended) {
			course.calcRunningAvg(getSchool());
			System.out.println("You went to " + course.getName() + ".");
		}
		else {
			course.calcRunningAvg(40);
			System.out.println("You skipped " + course.getName() + ".");
		}
		System.out.println("Your current grade is " + course.getGrade());
	}

	// enroll method
	public void enroll(Course course) {
		// remove from coursesAvailable
		int courseIndex = getCoursesAvailable().indexOf(course);
		getCoursesAvailable().remove(courseIndex);

		// add to coursesEnrolled
		getCoursesEnrolled().add(course);
		course.reset();;
	}

	// getGrades method
	public void getGrades() {
		for (int i = 0; i < getCoursesEnrolled().size(); i++) {
			Course c = getCoursesEnrolled().get(i);
			c.assignGrade();

			String str = c.getName() + ": " + c.getGrade();

			if (! c.getPassed()) {
				str += " - You will not get credit";
			}

			System.out.println(str);
		}

		System.out.println();
	}

	// endOfSemester method
	public void endOfSemester() {
		for (int i = 0; i < getCoursesEnrolled().size(); i++) {
			Course c = getCoursesEnrolled().get(i);
			// if course was passed
			if (c.getPassed() == true) {
				// update creditsTaken and creditsLeft
				setCreditsTaken(getCreditsTaken() + c.getNumCredits());
				setCreditsLeft(getCreditsLeft() - c.getNumCredits());
			}
			else {
				// put course back into coursesAvailable and sort
				getCoursesAvailable().add(c);
				// TODO sort
			}

			// regardless of grade, add to coursesTaken
			if (getCoursesTaken().contains(c)) {
				int replaceIndex = getCoursesTaken().indexOf(c);
				getCoursesTaken().set(replaceIndex, c);
			}
			else {	
				getCoursesTaken().add(c);
			}
		}

		// clear coursesEnrolled
		getCoursesEnrolled().clear();

		// reset stats
		resetStats();
	}

	// resetStats method
	public void resetStats() {
		setStamina(75);
		setSchool(75);
		setSocial(75);
	}
	
	// printStats method
	public void printStats() {
		int barLength = 50;

		// round the stats down to fit within barLength
		int staminaRound = getStamina() / (100 / barLength);
		int schoolRound = getSchool() / (100 / barLength);
		int socialRound = getSocial() / (100 / barLength);

		String bar = "\u23D0";
		char block = '\u2585';
		String space = "\u0020";

		// each char in the array accounts for 1 "unit" of the stat
		// (unit size determined by the bar length)
		char[] staminaBar = new char[barLength];
		char[] schoolBar = new char[barLength];
		char[] socialBar = new char[barLength];

		String staminaStr = bar + " ";
		String schoolStr = bar + " ";
		String socialStr = bar + " ";

		// fill arrays with blocks
		for (int i = 0; i < staminaRound; i++) {
			staminaBar[i] = block;
		}

		for (int i = 0; i < schoolRound; i++) {
			schoolBar[i] = block;
		}

		for (int i = 0; i < socialRound; i++) {
			socialBar[i] = block;
		}

		// convert arrays to Strings
		for (int i = 0; i < staminaBar.length; i++) {
			if (staminaBar[i] == '\000') {
				// dot = '\2024'
				staminaStr += space;
			}
			else {
				staminaStr += staminaBar[i];
			}
		}
		staminaStr += " " + bar;

		for (int i = 0; i < schoolBar.length; i++) {
			if (schoolBar[i] == '\000') {
				schoolStr += space;
			} 
			else {
				schoolStr += schoolBar[i];
			}
		}
		schoolStr += " " + bar;

		for (int i = 0; i < socialBar.length; i++) {
			if (socialBar[i] == '\000') {
				socialStr += space;
			} 
			else {
				socialStr += socialBar[i];
			}
		}
		socialStr += " " + bar;

		// format Strings
		System.out.printf("%13s %s %s\n", "Stamina", staminaStr, getStamina());
		System.out.printf("%13s %s %s\n", "School", schoolStr, getSchool());
		System.out.printf("%13s %s %s\n", "Social", socialStr, getSocial());
		System.out.printf("%13s \u23D0 %s\n", "Money", getMoney());
		System.out.printf("%13s \u23D0 %s\n", "Credits Taken", getCreditsTaken());
		System.out.printf("%13s \u23D0 %s\n", "Credits Left", getCreditsLeft());
		System.out.printf("%13s \u23D0 %s\n", "Location", 
				getCurrentLocation().getName());
		Course c1 = getCoursesEnrolled().get(0);
		Course c2 = getCoursesEnrolled().get(1);
		System.out.printf("%13s \u23D0 %s\n", c1.getName(), c1.getGrade());
		System.out.printf("%13s \u23D0 %s\n", c2.getName(), c2.getGrade());
		System.out.println();

	}

	public static void main(String[] args) {
		Player player = new Player();
		Course c1 = new Course("c1", "", 0, 0, 0, new int[] {0, 0, 0, 0, 0});
		Course c2 = new Course("c2", "", 0, 0, 0, new int[] {0, 0, 0, 0, 0});
		Course c3 = new Course("c3", "", 0, 0, 0, new int[] {0, 0, 0, 0, 0});

		// ----------------------------
		System.out.println("1");

		player.getCoursesAvailable().add(c1);
		player.getCoursesAvailable().add(c2);
		player.getCoursesAvailable().add(c3);

		System.out.println("Available courses");
		for (int i = 0; i < player.getCoursesAvailable().size(); i++) {
			System.out.println("\t" + player.getCoursesAvailable().get(i));
		}
		System.out.println("Enrolled courses");
		for (int i = 0; i < player.getCoursesEnrolled().size(); i++) {
			System.out.println("\t" + player.getCoursesEnrolled().get(i));
		}
		System.out.println("Taken courses");
		for (int i = 0; i < player.getCoursesTaken().size(); i++) {
			System.out.println("\t" + player.getCoursesTaken().get(i));
		}

		// ----------------------------
		System.out.println("2");

		player.enroll(c1);
		player.enroll(c2);

		System.out.println("Available courses");
		for (int i = 0; i < player.getCoursesAvailable().size(); i++) {
			System.out.println("\t" + player.getCoursesAvailable().get(i));
		}
		System.out.println("Enrolled courses");
		for (int i = 0; i < player.getCoursesEnrolled().size(); i++) {
			System.out.println("\t" + player.getCoursesEnrolled().get(i));
		}
		System.out.println("Taken courses");
		for (int i = 0; i < player.getCoursesTaken().size(); i++) {
			System.out.println("\t" + player.getCoursesTaken().get(i));
		}

		// ----------------------------
		System.out.println("3");

		c1.calcRunningAvg(80);
		c2.calcRunningAvg(94);
		player.endOfSemester();

		System.out.println("Available courses");
		for (int i = 0; i < player.getCoursesAvailable().size(); i++) {
			System.out.println("\t" + player.getCoursesAvailable().get(i));
		}
		System.out.println("Enrolled courses");
		for (int i = 0; i < player.getCoursesEnrolled().size(); i++) {
			System.out.println("\t" + player.getCoursesEnrolled().get(i));
		}
		System.out.println("Taken courses");
		for (int i = 0; i < player.getCoursesTaken().size(); i++) {
			System.out.println("\t" + player.getCoursesTaken().get(i));
		}

	}
}

