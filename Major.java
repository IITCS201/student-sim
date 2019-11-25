import java.util.Scanner;

public class Major {
	// Attributes
	String name;
	Course[] courses;
	int creditsReq;

	// constructor
	public Major(String name, Course[] courses, int creditsReq) {
		setName(name);
		setCourses(courses);
		setCreditsReq(creditsReq);
	}

	// accessor name
	public String getName() {
		return name;
	}

	// accessor courses
	public Course[] getCourses() {
		return courses;
	}

	// accessor creditsReq
	public int getCreditsReq() {
		return creditsReq;
	}

	// mutator name
	public Major setName(String name) {
		this.name = name;
		return this;
	}

	// mutator courses
	public Major setCourses(Course[] courses) {
		this.courses = courses;
		return this;
	}

	// mutator creditsReq
	public Major setCreditsReq(int creditsReq) {
		this.creditsReq = creditsReq;
		return this;
	}

	// toString method
	@Override
	public String toString() {
		String str = new String();
		str += "Major name: " + getName() + "\n";
		str += "Courses: \n";
		for (int i = 0; i < courses.length; i++) {
			str += "\t" + getCourses()[i].getName() + "\n";
		}
		str += "Credits Required: " + getCreditsReq() + "\n";

		return str;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReadCSV read = new ReadCSV();

		String file = "major.csv";
		
		System.out.println("Enter major");
		String search = scan.next();

		String[] s = read.search(file, search);

		String majorName = s[0];

		int majorCredits = Integer.parseInt(s[1]);

		Course[] courses = new Course[s.length - 2];
		for (int i = 0; i < courses.length; i++) {
			String courseName = s[i + 2];
			String[] courseInfo = read.search("course.csv", courseName);
			courseName = courseInfo[0];
			int courseCredits = Integer.parseInt(courseInfo[1]);
			courses[i] = new Course(courseName, courseCredits);
		}


		Major m = new Major(majorName, courses, majorCredits);
		System.out.println(m.toString());


	}
}

