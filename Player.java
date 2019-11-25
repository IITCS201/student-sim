import java.util.Random;
import java.util.Scanner;

public class Player {
	// Attributes
	Major major;

	Course[] coursesEnrolled;
	Course[] coursesTaken;

	Location currentLocation;

	int creditsTaken;
	int creditsLeft;

	int stamina;
	int school;
	int social;
	int money;

	int[] pos;

	boolean gameOver;

	// constructor
	public Player() {
		major = null;

		coursesEnrolled = null;
		coursesTaken = null;

		currentLocation = null;

		creditsTaken = 0;
		creditsLeft = 0;

		stamina = 75;
		school = 75;
		social = 75;
		money = 0;

		pos = new int[2];

		gameOver = false;
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

	// accessor gameOver
	public boolean getGameOver() {
		return gameOver;
	}

	// mutator major
	public Player setMajor(Major major) {
		this.major = major;
		return this;
	}

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

	// mutator gameOver
	public Player setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		return this;
	}

	// printStats method
	public void printStats() {
		int barLength = 50;

		// round the stats down to fit within barLength
		int staminaRound = getStamina() / (100 / barLength);
		int schoolRound = getSchool() / (100 / barLength);
		int socialRound = getSocial() / (100 / barLength);

		// each char in the array accounts for 1 "unit" of the stat
		// (unit size determined by the bar length)
		char[] staminaBar = new char[barLength];
		char[] schoolBar = new char[barLength];
		char[] socialBar = new char[barLength];

		String staminaStr = "\u23D0 ";
		String schoolStr = "\u23D0 ";
		String socialStr = "\u23D0 ";

		// fill arrays with blocks
		for (int i = 0; i < staminaRound; i++) {
			staminaBar[i] = '\u2585';
		}

		for (int i = 0; i < schoolRound; i++) {
			schoolBar[i] = '\u2585';
		}

		for (int i = 0; i < socialRound; i++) {
			socialBar[i] = '\u2585';
		}

		// convert arrays to Strings
		for (int i = 0; i < staminaBar.length; i++) {
			if (staminaBar[i] == '\000') {
				// dot = '\2024'
				staminaStr += "\u0020";
			}
			else {
				staminaStr += staminaBar[i];
			}
		}
		staminaStr += " \u23D0";

		for (int i = 0; i < schoolBar.length; i++) {
			if (schoolBar[i] == '\000') {
				schoolStr += "\u0020";
			} 
			else {
				schoolStr += schoolBar[i];
			}
		}
		schoolStr += " \u23D0";

		for (int i = 0; i < socialBar.length; i++) {
			if (socialBar[i] == '\000') {
				socialStr += "\u0020";
			} 
			else {
				socialStr += socialBar[i];
			}
		}
		socialStr += " \u23D0";

		// format Strings
		System.out.printf("%13s %s %s\n", "Stamina", staminaStr, getStamina());
		System.out.printf("%13s %s %s\n", "School", schoolStr, getSchool());
		System.out.printf("%13s %s %s\n", "Social", socialStr, getSocial());
		System.out.printf("%13s \u23D0 %s\n", "Money", getMoney());
		System.out.printf("%13s \u23D0 %s\n", "Credits Taken", getCreditsTaken());
		System.out.printf("%13s \u23D0 %s\n", "Credits Left", getCreditsLeft());
		System.out.printf("%13s \u23D0 %s\n", "Location", getCurrentLocation().getName());
		System.out.println();

	}

	public static void main(String[] args) {
		Player player = new Player();
		player.printStats();

	}
}

