package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.util.DatabaseHandler;
import ir.eynakgroup.derbi.util.Player;

import java.util.ArrayList;

import android.R;
import android.app.Activity;

public class QuestionGenerator {

	Activity activity;
	ArrayList<Player> players;
	/**
	 * 
	 * @param activity
	 *            : used for invoking database and other accesses
	 */
	public QuestionGenerator(Activity activity) {
		this.activity = activity;
		DatabaseHandler db = new DatabaseHandler(activity);
		players = db.getTeamPlayers(db.getTeam());
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

		return null;
	}

	private void getPlayersByAge() {

	}

	/**
	 * this function generate a question by player national goals count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getNationalGoalQ(int dir) {

	}

	/**
	 * this function generate a question by player national matches count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getNationalMatchQ(int dir) {

	}

	/**
	 * this function generate a question by age of player
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getAgeQ(int dir) {

	}

	/**
	 * this function generate a question by height of player
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getHeightQ(int dir) {

	}

	/**
	 * this function generate a question by player club goals count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getGoalQ(int dir) {

	}

	/**
	 * this function generate a question by player club match count
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getMatchQ(int dir) {

	}

	/**
	 * this function generate a question by player entrance
	 * 
	 * @param dir
	 *            : it shows that the answer is bigger or smaller , 1 means
	 *            bigger and 0 means smaller
	 */
	private void getEntranceQ(int dir) {

	}

	/**
	 * this function generate a question by position of player
	 * 
	 * @param indicator
	 *            : it shows that whether the answer has a unique position (1)
	 *            or the position of the the player is the answer (0)
	 */
	private String[] getPositionQ(int indicator) {
		int random = (int) Math.ceil(Math.random() * 4);
		String[] positions = activity.getResources().getStringArray(
				R.array.positions);
		if (indicator == 0) {
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPosition(random);
			db.close();
			String question = "چیست؟" + player + "پست ";
			String[] qandA = { question, positions[random - 1],
					positions[(random) % 4], positions[(random + 1) % 4],
					positions[(random + 2) % 4] };
			return qandA;
		} else if (indicator == 1) {
			String question = "است؟" + positions[random - 1] + "کدام بازیکن ";
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPosition(random);
			ArrayList<String> falseChoices = db
					.getRandomPlayersByPositionN(random);
			db.close();
			String[] qandA = { question, player, falseChoices.get(0),
					falseChoices.get(1), falseChoices.get(2) };

			return qandA;
		} else if (indicator == 2) {
			String question = "نیست؟" + positions[random - 1] + "کدام بازیکن ";
			DatabaseHandler db = new DatabaseHandler(activity);
			String player = db.getRandomPlayerByPositionN(random);
			ArrayList<String> falseChoices = db
					.getRandomPlayersByPosition(random);
			db.close();
			String[] qandA = { question, player, falseChoices.get(0),
					falseChoices.get(1), falseChoices.get(2) };

			return qandA;
		} else {
			return null;
		}
	}
}
