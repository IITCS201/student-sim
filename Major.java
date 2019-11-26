import java.util.Scanner;
import java.util.List;

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
			str += "\t" + getCourses()[i].toString() + "\n";
		}
		str += "Credits Required: " + getCreditsReq() + "\n";

		return str;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReadCSV csv = new ReadCSV();
		
		List<List<String>> arr = csv.toList("major.csv");
		int num = scan.nextInt();
		List<String> temp = arr.get(num);
		String[] aae = temp.toArray(new String[0]);
		String name = aae[0];
		int credits = Integer.parseInt(aae[1]);
		Course[] courses = new Course[aae.length - 2];
		for (int i = 0; i < courses.length; i++) {
			String cn = aae[i + 2];
			courses[i] = new Course(cn, "", 0, new int[] {0,0,0,0,0});
		}
		Major major = new Major(name, courses, credits);
		System.out.println(major.toString());

	}
}

