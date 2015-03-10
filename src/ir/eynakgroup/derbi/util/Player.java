package ir.eynakgroup.derbi.util;


public class Player {
	private String name;
	private String birthDate;
	private int team;
	private int position;
	private int entranceYear;
	private int matchCount;
	private int goalCount;
	private int height;
	private int nationalMatch;
	private int nationalGoals;
	private String birthPlace;

	public Player(String name, String birthDate, int team, int position,
			int entranceYear, int matchCount, int goalCount, int height,
			int nationalMatch, int nationalGoals, String birthPlace) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.team = team;
		this.position = position;
		this.entranceYear = entranceYear;
		this.matchCount = matchCount;
		this.goalCount = goalCount;
		this.height = height;
		this.nationalMatch = nationalMatch;
		this.nationalGoals = nationalGoals;
		this.birthPlace = birthPlace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getEntranceYear() {
		return entranceYear;
	}

	public void setEntranceYear(int entranceYear) {
		this.entranceYear = entranceYear;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public int getGoalCount() {
		return goalCount;
	}

	public void setGoalCount(int goalCount) {
		this.goalCount = goalCount;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNationalMatch() {
		return nationalMatch;
	}

	public void setNationalMatch(int nationalMatch) {
		this.nationalMatch = nationalMatch;
	}

	public int getNationalGoals() {
		return nationalGoals;
	}

	public void setNationalGoals(int nationalGoals) {
		this.nationalGoals = nationalGoals;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Override
	public String toString() {

		return name + ":" + birthDate + "|" + nationalMatch + "|"
				+ nationalGoals;
	}

	/**
	 * this function calculate the person's age by date of birth
	 * 
	 * @return : age of person in years
	 */
	public int getAge() {
		int birthYear = Integer.parseInt(getBirthDate().split("-")[0]);
		int birthMonth = Integer.parseInt(getBirthDate().split("-")[1]);
		int birthDay = Integer.parseInt(getBirthDate().split("-")[2]);

		return (birthYear * 365) + (birthMonth * 30) + (birthDay);
	}
}
