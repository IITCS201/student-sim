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
	}
}

