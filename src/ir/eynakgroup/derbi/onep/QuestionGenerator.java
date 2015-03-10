package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.DatabaseHandler;
import ir.eynakgroup.derbi.util.Player;
import ir.eynakgroup.derbi.util.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import android.app.Activity;

public class QuestionGenerator {

	Activity activity;
	ArrayList<Player> players;
	int team;

	/**
	 * 
	 * @param activity
	 *            : used for invoking database and other accesses
	 */
	public QuestionGenerator(Activity activity) {
		this.activity = activity;
		DatabaseHandler db = new DatabaseHandler(activity);
		// TODO players = db.getTeamPlayers(db.getTeam());
		team = 1;
		players = db.getTeamPlayers(team);
		System.out.println("-------nhbfvjv-----fdg-------dfghjk---");

		db.close();
	}

	/**
	 * 
	 * @param level
	 *            : the level of questions that must be generated
	 * @return an ArrayList of Strings. every string array contains 5 element.
	 *         the first is the question , the second is the answer and the rest
	 *         is the false answers
	 */
	public ArrayList<String[]> getQuestions(int level) {
		ArrayList<String[]> temp = new ArrayList<String[]>();
		return temp;
	}

	public ArrayList<Question> getQuestionsObjects(int level) {
		ArrayList<Question> temp = new ArrayList<Question>();
		Random r = new Random();
		while (temp.size() < 11) {
			int c = r.nextInt(99) + 1;
			if (c > 0 && c < 12)
				temp.add(getAgeQ(r.nextInt(10) - 5, team));
			else if (c > 11 && c < 23)
				temp.add(getEntranceQ(r.nextInt(10) - 5, team));
			else if (c > 22 && c < 34)
				temp.add(getGoalQ(r.nextInt(10) - 5, team));
			else if (c > 33 && c < 45)
				temp.add(getHeightQ(r.nextInt(10) - 5));
			else if (c > 44 && c < 56)
				temp.add(getMatchQ(r.nextInt(10) - 5, team));
			else if (c > 55 && c < 67)
				temp.add(getNationalGoalQ(r.nextInt(10) - 5));
			else if (c > 66 && c < 78)
				temp.add(getNationalMatchQ(r.nextInt(10) - 5));
			else if (c > 77 && c < 80)
				temp.add(getPositionQ(r.nextInt(10) - 5));
		}
		return temp;
	}

	/**
	 * this function generate a question by player national goals count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getNationalGoalQ(int dir) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getNationalGoals() - rhs.getNationalGoals();
			}
		});
		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getNationalGoals() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getNationalGoals() > players.get(i)
						.getNationalGoals())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن گل ملی بیشتری زده است؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getNationalGoals() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getNationalGoals() < players.get(i)
						.getNationalGoals())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن گل ملی کمتری زده است؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}
	}

	private int getRandom(int nulls, int size) {
		double f = Math.random();
		f = f * (size - nulls) + nulls;
		return (int) Math.ceil(f);
	}

	/**
	 * this function generate a question by player national matches count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getNationalMatchQ(int dir) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getNationalMatch() - rhs.getNationalMatch();
			}
		});

		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getNationalMatch() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getNationalMatch() > players.get(i)
						.getNationalMatch())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن بازی ملی بیشتری دارد؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getNationalMatch() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getNationalMatch() < players.get(i)
						.getNationalMatch())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن بازی ملی کمتری دارد؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}
	}

	/**
	 * this function generate a question by age of player
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getAgeQ(int dir, int team) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				String[] lage = lhs.getBirthDate().split("-");
				String[] rage = rhs.getBirthDate().split("-");
				if (Integer.parseInt(lage[0]) != Integer.parseInt(rage[0]))
					return Integer.parseInt(lage[0])
							- Integer.parseInt(rage[0]);
				else if (Integer.parseInt(lage[1]) != Integer.parseInt(rage[1]))
					return Integer.parseInt(lage[1])
							- Integer.parseInt(rage[1]);
				else
					return Integer.parseInt(lage[2])
							- Integer.parseInt(rage[2]);
			}
		});
		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getBirthDate().length() != 10)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getAge() > players.get(i).getAge())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String question = "کدام بازیکن جوان‌تر است؟";
			String player = players.get(pivot).getName();
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getBirthDate().length() != 10)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getAge() < players.get(i).getAge())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن پیرتر است؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}

	}

	/**
	 * this function generate a question by height of player
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getHeightQ(int dir) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getHeight() - rhs.getHeight();
			}
		});

		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getHeight() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getHeight() > players.get(i).getHeight())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن قد بلندتری دارد؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getHeight() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getHeight() < players.get(i).getHeight())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "کدام بازیکن قد کوتاه‌تری دارد؟";
			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}

	}

	/**
	 * this function generate a question by player club goals count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getGoalQ(int dir, int team) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getGoalCount() - rhs.getGoalCount();
			}
		});

		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getGoalCount() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getGoalCount() > players.get(i)
						.getGoalCount())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن گل بیشتری برای استقلال زده است؟";
			else if (team == 1)
				question = "کدام بازیکن گل بیشتری برای پرسپولیس زده است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getGoalCount() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getGoalCount() < players.get(i)
						.getGoalCount())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن گل کمتری برای استقلال زده است؟";
			else if (team == 1)
				question = "کدام بازیکن گل کمتری برای پرسپولیس زده است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}
	}

	/**
	 * this function generate a question by player club match count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getMatchQ(int dir, int team) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getMatchCount() - rhs.getMatchCount();
			}
		});

		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getMatchCount() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getMatchCount() > players.get(i)
						.getMatchCount())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن گل بیشتری برای استقلال زده است؟";
			else if (team == 1)
				question = "کدام بازیکن گل بیشتری برای پرسپولیس زده است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getMatchCount() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getMatchCount() < players.get(i)
						.getMatchCount())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن گل کمتری برای استقلال زده است؟";
			else if (team == 1)
				question = "کدام بازیکن گل کمتری برای پرسپولیس زده است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}
	}

	/**
	 * this function generate a question by player entrance
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private Question getEntranceQ(int dir, int team) {
		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player lhs, Player rhs) {
				return lhs.getEntranceYear() - rhs.getEntranceYear();
			}
		});

		int nulls = 0;
		if (dir > 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getEntranceYear() < 1)
					nulls++;
			}
			int pivot = getRandom(nulls + 8, players.size());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = nulls; i < pivot; i++) {
				if (players.get(pivot).getEntranceYear() > players.get(i)
						.getEntranceYear())
					temp.add(i);
			}
			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن زودتر به استقلال پیوسته است؟";
			else if (team == 1)
				question = "کدام بازیکن زودتر به پرسپولیس پیوسته است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		} else {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getEntranceYear() < 0)
					nulls++;
			}
			int pivot = getRandom(nulls, players.size() - 8);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = pivot; i < players.size(); i++) {
				if (players.get(pivot).getEntranceYear() < players.get(i)
						.getEntranceYear())
					temp.add(i);
			}

			Collections.shuffle(temp);
			String player = players.get(pivot).getName();
			String question = "";
			if (team == 2)
				question = "کدام بازیکن دیرتر به استقلال پیوسته است؟";
			else if (team == 1)
				question = "کدام بازیکن دیرتر به پرسپولیس پیوسته است؟";

			String[] falses = { players.get(temp.get(0)).getName(),
					players.get(temp.get(1)).getName(),
					players.get(temp.get(2)).getName() };
			Question q = new Question(question, player, falses);
			return q;
		}
	}

	/**
	 * this function generate a question by position of player
	 * 
	 * @param indicator
	 *            : it shows that whether the answer has a unique position (1)
	 *            or the position of the the player is the answer (0)
	 */
	private Question getPositionQ(int indicator) {
		int random = (int) Math.ceil(Math.random() * 4);
		String[] positions = activity.getResources().getStringArray(
				R.array.positions);
		if (indicator == 0) {
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPosition(random);
			db.close();
			String question = " پست " + player + "چیست؟ ";
			String[] qandA = { question, positions[random - 1],
					positions[(random) % 4], positions[(random + 1) % 4],
					positions[(random + 2) % 4] };
			return new Question(qandA);
		} else if (indicator == 1) {
			String question = " کدام بازیکن " + positions[random - 1]
					+ " است؟ ";
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPosition(random);
			ArrayList<String> falseChoices = db
					.getRandomPlayersByPositionN(random);
			db.close();
			String[] qandA = { question, player, falseChoices.get(0),
					falseChoices.get(1), falseChoices.get(2) };

			return new Question(qandA);
		} else if (indicator == 2) {
			String question = "کدام بازیکن " + positions[random - 1] + " نیست؟";
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPositionN(random);
			ArrayList<String> falseChoices = db
					.getRandomPlayersByPosition(random);
			db.close();
			String[] qandA = { question, player, falseChoices.get(0),
					falseChoices.get(1), falseChoices.get(2) };

			return new Question(qandA);
		} else {
			return null;
		}
	}
}
