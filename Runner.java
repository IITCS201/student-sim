import java.util.Scanner;
import java.util.List;

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
		Location home = new Location("Home", new int[] {0, 0}, new double[] {20, 5, 5, 0}, false, false);

		Location library = new Location("Library", new int[] {0, 0}, new double[] {5, 10, 5, 0}, false, false);
		Location tutor = new Location("Tutor", new int[] {0, 0}, new double[] {5, 20, 5, 25}, false, false);

		Location studentCenter = new Location("Student Center", new int[] {0, 0}, new double[] {5, 5, 10, 0}, false, false);
		Location bar = new Location("Bar", new int[] {0, 0}, new double[] {5, 5, 20, 25}, false, false);

		Location classroom = new Location("Classroom", new int[] {0, 0}, new double[] {0, 0, 0, 0}, true, false);

		Location work = new Location("Work", new int[] {0, 0}, new double[] {0, 0, 0, 100}, true, false);

		String majorCSV = "major.csv";
			/* 0: number
			 * 1: name
			 * 2: credits
			 * 3-end: courses
			 * */
		String courseCSV = "course.csv";
			/* 0: name
			 * 1: fullName
			 * 2: numCredits
			 * 3: year to be taken
			 * 4: semester to be taken
			 * 5-9: gradingScale
			 * */

		// create list of majors
		List<List<String>> majorsList = csv.toList(majorCSV);

		// create list of courses
		List<List<String>> coursesList = csv.toList(courseCSV);

		// ------------------------------------ //
		// SETUP
		// ------------------------------------ //

		// ask player for major
		System.out.println("Choose a major");
		System.out.printf("%-30s %-30s %-30s\n",
				"[1] Aerospace Engineering",
				"[2] Architecture",
				"[3] Biology");
		System.out.printf("%-30s %-30s %-30s\n",
				"[4] Chemistry",
				"[5] Communication",
				"[6] Computer Science");
		System.out.printf("%-30s %-30s %-30s\n",
				"[7] Humanities",
				"[8] Mechanical Engineering",
				"[9] Physics");
		System.out.print("> ");
		int num = scan.nextInt() - 1;

		// select chosen major list and store to temp variable
		List<String> temp = majorsList.get(num);
		
		// convert list to array to simplify use
		String[] chosenMajor = temp.toArray(new String[0]);

		// isolate name, credits required, and list of courses
		String majorName = chosenMajor[0];
		int creditsReq = Integer.parseInt(chosenMajor[1]);
		Course[] courses = new Course[chosenMajor.length - 5];

		for (int i = 0; i < courses.length; i++) {
			// search for course in courseList using
			// binary search algorithm
			int startIndex = 0;	
			int endIndex = coursesList.size();
			int middleIndex = (startIndex + endIndex) / 2;
			String current = coursesList.get(middleIndex).get(0);

			while (! current.equals(chosenMajor[i + 2])) {
				if (current.compareTo(chosenMajor[i + 2]) < 0) {
					startIndex = middleIndex;
					middleIndex = (startIndex + endIndex) / 2;
					current = coursesList.get(middleIndex).get(0);
				}
				else {
					endIndex = middleIndex;
					middleIndex = (startIndex + endIndex) / 2;
					current = coursesList.get(middleIndex).get(0);
				}
			}

			List<String> tmp = coursesList.get(middleIndex);
			String[] chosenCourse = tmp.toArray(new String[0]);

			String courseName = chosenCourse[0];
			String courseFullName = chosenCourse[1];
			int numCredits = Integer.parseInt(chosenCourse[2]);
			int offerYear = Integer.parseInt(chosenCourse[3]);
			int offerSemester = Integer.parseInt(chosenCourse[4]);
			int[] gradingScale = new int[5];
			for (int j = 0; j < gradingScale.length; j++) {
				gradingScale[j] = Integer.parseInt(chosenCourse[j + 3]);
			}
			
			courses[i] = new Course(courseName, courseFullName, numCredits, offerYear, offerSemester, gradingScale);
		}

		Major major = new Major(majorName, courses, creditsReq);
		
		player.setMajor(major);
		player.setCoursesAvailable(major.getCourses());
		player.setCreditsLeft(major.getCreditsReq());
		player.setCurrentLocation(home);
		//System.out.println(major.toString());

		// ------------------------------------ //
		// PLAY
		// ------------------------------------ //
		boolean gameOver = false;

		// Timing System:
		// each turn is 1 hour
		// 6 hours in a day
		// 5 days in a week
		// 4 weeks in a semester
		// 2 semesters in a year
		int year = 1;
		int semester = 1;
		int week = 1;
		int day = 1;
		int hour = 1;

		final int SEMESTERS_PER_YEAR = 2;
		final int WEEKS_PER_SEMESTER = 2;
		final int DAYS_PER_WEEK = 5;
		final int HOURS_PER_DAY = 6;

		while (! gameOver) {
			// if beginning of semester, ask user to pick classes
			if ((week == 1) && (day == 1) && (hour == 1)) {
				System.out.println("Pick two classes");
				for (int i = 0; i < player.getCoursesAvailable().size(); i++) {
					if ((player.getCoursesAvailable().get(i).getOfferYear() == year) 
							&& (player.getCoursesAvailable().get(i).getOfferSemester() == semester)) {
						System.out.println("[" + (i + 1) + "] " 
								+ player.getCoursesAvailable().get(i));
					}
				}

				System.out.print("Class 1 > ");
				int class1Index = scan.nextInt() - 1;
				Course class1 = player.getCoursesAvailable().get(class1Index);
				System.out.println(class1);
				System.out.print("Class 2 > ");
				int class2Index = scan.nextInt() - 1;
				Course class2 = player.getCoursesAvailable().get(class2Index);
				
				player.getCoursesEnrolled().add(class1);
				player.getCoursesEnrolled().add(class2);

				System.out.println("You are now enrolled in ");
				for (int i = 0; i < player.getCoursesEnrolled().size(); i++) {
					System.out.println(player.getCoursesEnrolled().get(i));
				}
			}

			// ask what the player wants to do
			// 	options:
			// 		see stats
			// 		see schedule
			// 		move
			// 		sleep
			// 		study
			// 		socialize
			int choose = 0;
			while (true) {
				System.out.println("You are at the " + player.getCurrentLocation().getName() + ". What would you like to do?");
				System.out.printf("%-16s %-16s %-16s\n%-16s %-16s %-16s\n",
						"[1] See stats", "[2] See schedule", "[3] Move",
						"[4] Sleep", "[5] Study", "[6] Socialize");
				choose = scan.nextInt();

				if (choose == 1) {
				// print stats at the beginning of each turn
					System.out.printf("%14s\u23D0 %-4s %-2s \u23D0 %-8s %-2s \u23D0 %-4s %-2s \u23D0 %-3s %-2s \u23D0 %-4s %-2s \u23D0\n", 
							"", 
							"Year", year,
							"Semester", semester,
							"Week", week,
							"Day", day,
							"Hour", hour);
					player.printStats();
				}
				else if (choose == 2) {
					System.out.println("TODO: print schedule");
				}
				else if (choose == 3) {
					System.out.println("Where would you like to go?");
					// home library tutor student center bar
					System.out.printf("%-18s\n%-18s %-18s\n%-18s %-18s\n",
							"[1] Home",
							"[2] Library", "[3] Tutor",
							"[3] Student Center", "[4] Bar");
				}
				else if (choose == 4) {
					break;
				}
				else if (choose == 5) {
					break;
				}
				else if (choose == 6) {
					break;
				}	
			}
			





			System.out.print("Enter an key");
			String ugh = scan.next();
			// time advancement
			hour++;
			if (hour == HOURS_PER_DAY + 1) {
				day++;
				hour = 1;
			}
			if (day == DAYS_PER_WEEK + 1) {
				week++;
				day = 1;
			}
			if (week == WEEKS_PER_SEMESTER + 1) {
				semester++;
				week = 1;
			}
			if (semester == SEMESTERS_PER_YEAR + 1) {
				year++;
				semester = 1;
			}
			if (year == 4) {
				gameOver = true;
			}
		}

	}
}

