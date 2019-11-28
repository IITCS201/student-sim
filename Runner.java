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
		int high = 15;
		int medium = 8;
		int low = 3;

		Location home = new Location("Home", new int[] {7, 8}, high, low, low, 0);

		Location library = new Location("Library", new int[] {3, 5}, low, medium, low, 0);
		Location tutor = new Location("Tutor", new int[] {2, 1}, low, high, low, 25);

		Location studentCenter = new Location("Student Center", new int[] {6, 7}, low, low, medium, 0);
		Location bar = new Location("Bar", new int[] {15, 5}, low, low, high, 25);

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
		// PLAY
		// ------------------------------------ //
		boolean gameOver = false;
		boolean newGame = true;

		final int SEMESTERS_PER_YEAR = 2;
		final int WEEKS_PER_SEMESTER = 2;
		final int DAYS_PER_WEEK = 5;
		final int HOURS_PER_DAY = 4;

		final int LOW_THRESHOLD = 15;

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

		// welcome message, the first thing the user sees
		System.out.println("Welcome to Illinois Tech!");
		System.out.println("rules rules rules\n rules rules\nrules rules rules");
		System.out.println("Are you ready to pick a major?");
		System.out.printf("%-10s %-10s\n", "[1] Yes", "[2] No");
		System.out.print("> ");
		int yn = scan.nextInt();

		if (yn == 2) {
			gameOver = true;
		}

		while (! gameOver) {
			// CHECKPOINT CHOOSE MAJOR
			// if new game, set up major
			if (newGame) {
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
						gradingScale[j] = Integer.parseInt(chosenCourse[j + 5]);
					}
					
					courses[i] = new Course(courseName, courseFullName, numCredits, 
							offerYear, offerSemester, gradingScale);
				}

				Major major = new Major(majorName, courses, creditsReq);
				
				player.setMajor(major);
				player.setCoursesAvailable(major.getCourses());
				player.setCreditsLeft(major.getCreditsReq());
				player.setCurrentLocation(home);

				newGame = false;
			}

			// CHECKPOINT CHOOSE CLASSES
			// if beginning of semester, ask user to pick classes
			if ((week == 1) && (day == 1) && (hour == 1)) {
				System.out.println("It is the beginning of a new semester.");
				System.out.println("Pick two classes");
				for (int i = 0; i < player.getCoursesAvailable().size(); i++) {
					/*
					if ((player.getCoursesAvailable().get(i).getOfferYear() == year) 
							&& (player.getCoursesAvailable().get(i).getOfferSemester() == semester)) {
						System.out.println("[" + (i + 1) + "] " 
								+ player.getCoursesAvailable().get(i));
					}
					*/
					System.out.println("[" + (i + 1) + "]"
							+ player.getCoursesAvailable().get(i));
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

			// CHECKPOINT LOW BARS
			// if any bars low, recommend play take action
			if (player.getStamina() < LOW_THRESHOLD) {
				System.out.println("You look tired. Maybe you should sleep.");
			}

			if (player.getSchool() < LOW_THRESHOLD) {
				System.out.println("Do you even know what's going on in class? Maybe you should study.");
			}

			if (player.getSocial() < LOW_THRESHOLD) {
				System.out.println("Your friends are forgetting you exist. Maybe you should socialize.");
			}

			// create String to print a more helpful version of the hour to the screen
			// have to be careful with this... while the number of hours in a day is a variable,
			// this essentially hard-codes it to be 4
			String timeStr = new String();
			switch (hour) {
				case 1: timeStr = "12:00 AM";
						break;
				case 2: timeStr = "6:00 AM";
						break;
				case 3: timeStr = "12:00 PM";
						break;
				case 4: timeStr = "6:00 PM";
						break;
				default: break;

			}
		
			// CHECKPOINT CHOOSE ACTION
			// only sleeping, studying, socializing, going to work, or going to class updates the clock
			int choose = 0;

			Course currentCourse = player.getCoursesEnrolled().get(player.getSchedule()[day - 1]);

			while ((choose < 4) && (! gameOver)) {
				// update player with time and location, then ask for action
				System.out.println("It is " + timeStr  + ". "
						+ "You are at the " + player.getCurrentLocation().getName() 
						+ ". What would you like to do?");

				// if class is available
				if (hour == 3) {
					System.out.printf("%-16s %-16s %-16s\n%-16s %-16s %-16s\n%-16s %-16s\n",
							"[1] See stats", "[2] See schedule", "[3] Move",
							"[4] Sleep", "[5] Study", "[6] Socialize",
							"[7] Go to work", "[8] Go to class");
					System.out.print("> ");
					choose = scan.nextInt();
				}
				// if work is available
				else if ((hour > 1) && (hour < HOURS_PER_DAY)) {
					System.out.printf("%-16s %-16s %-16s\n%-16s %-16s %-16s\n%-16s\n",
							"[1] See stats", "[2] See schedule", "[3] Move",
							"[4] Sleep", "[5] Study", "[6] Socialize",
							"[7] Go to work");
					System.out.print("> ");
					choose = scan.nextInt();
				}
				// otherwise
				else {
					System.out.printf("%-16s %-16s %-16s\n%-16s %-16s %-16s\n",
							"[1] See stats", "[2] See schedule", "[3] Move",
							"[4] Sleep", "[5] Study", "[6] Socialize");
					System.out.print("> ");
					choose = scan.nextInt();
				}

				switch (choose) {
					// see stats
					case 1: System.out.printf("%14s\u23D0 %-4s %-2s \u23D0 %-8s %-2s \u23D0 %-4s %-2s \u23D0 %-3s %-2s \u23D0 %-4s %-2s \u23D0\n", 
									"", 
									"Year", year,
									"Semester", semester,
									"Week", week,
									"Day", day,
									"Hour", hour);
							player.printStats();
							break;
					// see schedule
					case 2: System.out.println("TODO: print schedule");
							break;
					// move
					case 3: System.out.println("Where would you like to go?"); 
							System.out.printf("%-18s\n%-18s %-18s\n%-18s %-18s\n", 
									"[1] Home",
									"[2] Library", "[3] Tutor",
									"[4] Student Center", "[5] Bar");
							System.out.print("> ");
							int moveTo = scan.nextInt();

							Location currentLocation = null;
							switch (moveTo) {
								case 1: player.move(home);
										break;
								case 2: player.move(library);
										break;
								case 3: player.move(tutor);
										break;
								case 4: player.move(studentCenter);
										 break;
								case 5: player.move(bar);
										break;
								default: break;
							}	
							if (player.getStamina() <= 0) {
								System.out.println("You ran out of stamina.");
								System.out.println("Game over.");
								gameOver = true;
							}	
							break;
					// sleep
					case 4: player.sleep();
							// if hour 3 and player sleep, they are skipping class
							if (hour == 3)
								player.goToClass(currentCourse, false);
							break;
					// study
					case 5: player.study();
							if (hour == 3) 
								player.goToClass(currentCourse, false);
							break;
					// socialize
					case 6: player.socialize();
							if (hour == 3)
								player.goToClass(currentCourse, false);
							break;
					// work
					case 7: player.goToWork();
							player.setCurrentLocation(home);
							if (hour == 3)
								player.goToClass(currentCourse, false);
							break;
					case 8: player.goToClass(currentCourse, true);
							player.setCurrentLocation(home);
							break;
					default: break;
				}
				// if player runs out of stamina, school, or social, end game
				if (player.getStamina() <= 0) {
					System.out.println("You ran out of stamina.");
					System.out.println("Game over.");
					gameOver = true;
				}
				else if (player.getSchool() <= 0) {
					System.out.println("You ran out of school.");
					System.out.println("Game over.");
					gameOver = true;
				}
				else if (player.getSocial() <= 0) {
					System.out.println("You ran out of social.");
					System.out.println("Game over");
					gameOver = true;
				}
			}
			
			// once out of the while loop...
			System.out.println();
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
				// several things can happen at the end of the semester

				System.out.println("It's the end of the semester!");
				System.out.println("Let's see how you did.");
				player.getGrades();

				player.endOfSemester();

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
	} // end main 
} // end Runner.java

