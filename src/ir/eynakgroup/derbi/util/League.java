package ir.eynakgroup.derbi.util;

public class League {
	private String season;
	private int team;
	private int position;
	private int points;

	public League(String season, int team, int position, int points) {
		super();
		this.season = season;
		this.team = team;
		this.position = position;
		this.points = points;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}


	@Override
	public String toString() {

		return season + ":" + team + "|" + points + "|" + position;
	}
}
