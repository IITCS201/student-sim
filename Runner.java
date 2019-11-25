import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		// ------------------------------------ //
		// PRE-SETUP
		// ------------------------------------ //
		ReadCSV csv = new ReadCSV();
		Scanner scan = new Scanner(System.in);

		// create Player object
		Player player = new Player();

		// create all seven Location objects
		Location home = new Location("Home", new int[] {0, 0}, new double[] {20, 5, 5, 0}, false);

		Location library = new Location("Library", new int[] {0, 0}, new double[] {5, 10, 5, 0}, false);
		Location tutor = new Location("Tutor", new int[] {0, 0}, new double[] {5, 20, 5, 25}, false);

		Location studentCenter = new Location("Student Center", new int[] {0, 0}, new double[] {5, 5, 10, 0}, false);
		Location bar = new Location("Bar", new int[] {0, 0}, new double[] {5, 5, 20, 25}, false);

		Location classroom = new Location("Classroom", new int[] {0, 0}, new double[] {0, 0, 0, 0}, false);

		Location work = new Location("Work", new int[] {0, 0}, new double[] {0, 0, 0, 100}, true);

		String majorCSV = "major.csv";
		String courseCSV = "course.csv";
			/* 0: name
			 * 1: numCredits
			 * 2-6: gradingScale
			 * */

		// ------------------------------------ //
		// SETUP
		// ------------------------------------ //

		// ask player for major
		System.out.println("Choose a major");
		System.out.printf("%-20s %-20s %-20s\n", 
				"[1] Engineering",
				"[2] Biology",
				"[3] English");
		System.out.printf("%-20s %-20s %-20s\n",
				"[4] Chemistry", 
				"[5] Computer Science",
				"[6] Communications");
		System.out.print("> ");
		String num = scan.next();

		// populate Major object
		String[] majorLine = csv.search(majorCSV, num);

		String majorName = majorLine[1];

		int majorCredits = Integer.parseInt(majorLine[2]);

		// populate courses
		Course[] majorCourses = new Course[majorLine.length - 3];
		for (int i = 0; i < majorCourses.length; i++) {
			String courseName = majorLine[i + 3];
			String[] courseLine = csv.search(courseCSV, courseName);
			int courseCredits = Integer.parseInt(courseLine[1]);
			int[] gradingScale = {Integer.parseInt(courseLine[2]), 
				Integer.parseInt(courseLine[3]), 
				Integer.parseInt(courseLine[4]),
				Integer.parseInt(courseLine[5]), 
				Integer.parseInt(courseLine[6])};
			majorCourses[i] = new Course(courseName, courseCredits, gradingScale);
		}

		Major major = new Major(majorName, majorCourses, majorCredits);

		// initialize player
		player.setMajor(major);
		player.setCreditsLeft(major.getCreditsReq());
		player.setCreditsTaken(0);
		player.setCurrentLocation(home);

		// ----------------------------------- //
		// PLAY
		// ----------------------------------- //

		int year;
		int semester;
		int week;
		int day;
		int hour;

		// each turn is an hour
		// there are 6 hours in a day
		// 5 days in a week
		// 4 weeks in a semester
		// 2 semesters in a year

		player.printStats();
		System.out.println("You are currently at " + player.getCurrentLocation().getName());
		System.out.println("What would you like to do?");
		player.getCurrentLocation().prompt();

	}
}

