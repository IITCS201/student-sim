public class Location {
	// Attributes
	String name;
	int[] pos;
	double[] modifiers;
	boolean isWork;

	// constructor
	public Location(String name, int[] pos, double[] modifiers, boolean isWork) {
		setName(name);
		setPos(pos);
		setModifiers(modifiers);
		setIsWork(isWork);
	}

	// accessor name
	public String getName() {
		return name;
	}

	// accessor pos
	public int[] getPos() {
		return pos;
	}

	// accessor modifiers
	public double[] getModifiers() {
		return modifiers;
	}

	// accessor isWork
	public boolean getIsWork() {
		return isWork;
	}

	// mutator name
	public Location setName(String name) {
		this.name = name;
		return this;
	}

	// mutator pos
	public Location setPos(int[] pos) {
		this.pos = pos;
		return this;
	}

	// mutator modifiers
	public Location setModifiers(double[] modifiers) {
		this.modifiers = modifiers;
		return this;
	}

	// mutator isWork
	public Location setIsWork(boolean isWork) {
		this.isWork = isWork;
		return this;
	}

	// prompt method
	public void prompt() {
		System.out.println("Choose an action");
		if (getIsWork()) {
			System.out.printf("%-10s %-10s %-10s %-10s\n",
					"[1] Work", "[2] Sleep", "[3] Study", "[4] Socialize");
		}
		else {
			System.out.printf("%-10s %-10s %-10s\n",
					"[1] Sleep", "[2] Study", "[3] Socialize");
		}
	}

	// sleep method
	
	// study method
	
	// socialize method
	
	// work method
	
	public static void main(String[] args) {
		Location l = new Location("L", new int[] {0,0}, new double[] {0,0,0,0}, true);
		l.prompt();
	}
}
