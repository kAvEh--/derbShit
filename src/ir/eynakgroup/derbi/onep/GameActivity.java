package ir.eynakgroup.derbi.onep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends FragmentActivity {
	private TextView questionNumber;
	private TextView _score_layout;
	private TextView _heart_layout;
	private TextView _question_body;
	private ProgressBar timer;
	private ArrayList<Button> choices;

	private ArrayList<String[]> questions;
	private String fonts = "BNazanin.ttf";

	// CountDownTimer counter;
	long currentTime;
	int time = 10;
	int time_step = 100;
	int minimumTime = 3;
	CountDownTimer counter;

	int hearts = 5;

	boolean gameFlag = true;

	int _question_counter = -1;

	int _total_score = 0;

	int _PACKAGE_NUM = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.one_player_question);
		questions = new ArrayList<String[]>();
		String[] temp1 = { "???1", "O", "X1", "X1", "X1" };
		String[] temp2 = { "???2", "O", "X2", "X2", "X2" };
		String[] temp3 = { "???3", "O", "X3", "X3", "X3" };
		String[] temp4 = { "???4", "O", "X4", "X4", "X4" };
		String[] temp5 = { "???5", "O", "X5", "X5", "X5" };
		String[] temp6 = { "???6", "O", "X6", "X6", "X6" };
		String[] temp7 = { "???7", "O", "X7", "X7", "X7" };
		String[] temp8 = { "???8", "O", "X8", "X8", "X8" };
		String[] temp9 = { "???9", "O", "X9", "X9", "X9" };
		String[] temp10 = { "???10", "O", "X10", "X10", "X10" };
		questions.add(temp1);
		questions.add(temp2);
		questions.add(temp3);
		questions.add(temp4);
		questions.add(temp5);
		questions.add(temp6);
		questions.add(temp7);
		questions.add(temp8);
		questions.add(temp9);
		questions.add(temp10);

		Typeface face = Typeface.createFromAsset(getAssets(), "font/" + fonts
				+ "");

		// Initializing activity elements
		questionNumber = (TextView) findViewById(R.id.tv_onep_q_number);
		_score_layout = (TextView) findViewById(R.id.tv_onp_score);
		_heart_layout = (TextView) findViewById(R.id.tv_onep_hearts);
		_question_body = (TextView) findViewById(R.id.tv_onep_question);
		timer = (ProgressBar) findViewById(R.id.pb_onep_timer);

		choices = new ArrayList<Button>();
		choices.add((Button) findViewById(R.id.btn_choice1));
		choices.add((Button) findViewById(R.id.btn_choice2));
		choices.add((Button) findViewById(R.id.btn_choice3));
		choices.add((Button) findViewById(R.id.btn_choice4));

		questionNumber.setTypeface(face);
		_heart_layout.setTypeface(face);
		_score_layout.setTypeface(face);

		setQuestion();
		super.onCreate(savedInstanceState);
	}

	/**
	 * this function sets the next _question_body from the questions ArrayList
	 */
	public boolean setQuestion() {

		gameFlag = true;

		_question_counter++;

		_question_body.setText(questions.get(_question_counter)[0]);

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		Collections.shuffle(numbers);
		for (int i = 0; i < numbers.size(); i++) {
			choices.get(i).setText(
					questions.get(_question_counter)[numbers.get(i)]);
			if (numbers.get(i) == 1) {
				choices.get(i).setTag(1);
			} else {
				choices.get(i).setTag(0);
			}
		}

		questionNumber.setText(_question_counter + "/10");

		timer.setMax(time_step);
		timer.setProgress(timer.getMax());
		runTimer(time);
		return true;
	}

	public void setNextQuestion() {
		if (_question_counter < _PACKAGE_NUM - 1) {
			coolDownCounter();
		} else {
			EndPackFragment endScreen = new EndPackFragment();
			endScreen.setScore(_total_score);
			endScreen.setStyle(DialogFragment.STYLE_NO_TITLE,
					R.style.FullscreenTheme);
			endScreen.show(getSupportFragmentManager(), "Hi");
		}
	}

	private void coolDownCounter() {
		counter = new CountDownTimer(1000, 10) {

			@Override
			public void onTick(long milli) {
			}

			@Override
			public void onFinish() {
				gameFlag = true;
				setQuestion();
			}
		};
		counter.start();
	}

	public void nextPackage() {

		_question_counter = -1;

		questions = new ArrayList<String[]>();
		String[] temp1 = { "###1", "O", "G1", "G1", "G1" };
		String[] temp2 = { "###2", "O", "G2", "G2", "G2" };
		String[] temp3 = { "###3", "O", "G3", "G3", "G3" };
		String[] temp4 = { "###4", "O", "G4", "G4", "G4" };
		String[] temp5 = { "###5", "O", "G5", "G5", "G5" };
		String[] temp6 = { "###6", "O", "G6", "G6", "G6" };
		String[] temp7 = { "###7", "O", "G7", "G7", "G7" };
		String[] temp8 = { "###8", "O", "G8", "G8", "G8" };
		String[] temp9 = { "###9", "O", "G9", "G9", "G9" };
		String[] temp10 = { "###10", "O", "G10", "G10", "G10" };
		questions.add(temp1);
		questions.add(temp2);
		questions.add(temp3);
		questions.add(temp4);
		questions.add(temp5);
		questions.add(temp6);
		questions.add(temp7);
		questions.add(temp8);
		questions.add(temp9);
		questions.add(temp10);

		setQuestion();
	}

	public void onChoiceClick(View v) {
		if (gameFlag) {
			gameFlag = false;
			counter.cancel();
			int tag = Integer.parseInt(v.getTag().toString());
			switch (tag) {
			case 1:
				updateScore(1);
				updateHearts(0);
				break;

			case 0:
				timer.setProgress(0);
				updateHearts(-1);
				break;
			default:
				break;

			}
		}
	}

	/**
	 * this function runs a timer for calculating _score_layout of user
	 * 
	 * @param time
	 *            : the time that should be considered for _question_body time
	 */
	public void runTimer(long time) {
		currentTime = time;
		counter = new CountDownTimer(time * 1000, 10) {

			@Override
			public void onTick(long milli) {
				timer.setProgress((int) milli / 100);
				currentTime = milli;
			}

			@Override
			public void onFinish() {
				timer.setProgress(0);
				updateHearts(-1);
			}
		};
		counter.start();
	}

	/**
	 * this function updates user number of hearts
	 * 
	 * @param change
	 *            : the change that should be applied to user hearts number
	 * @return true if resuming game is available for user and false otherwise
	 */
	public void updateHearts(int change) {
		hearts += change;
		_heart_layout.setText(hearts + "");
		if (hearts < 1) {
			stopGame();
		} else {
			setNextQuestion();
		}
	}

	/**
	 * this function updates user _score_layout
	 * 
	 * @param change
	 *            : the change that should be applied to the _score_layout of
	 *            user
	 */
	public void updateScore(int change) {
		_total_score += change;
		_score_layout.setText(String.valueOf(_total_score));
	}

	/**
	 * this method shows end game dialog to user
	 */
	protected void showEndDialog() {
		gameFlag = false;
		EndGameFragment endScreen = new EndGameFragment();
		endScreen.setScore(_total_score);
		endScreen.setStyle(DialogFragment.STYLE_NO_TITLE,
				R.style.FullscreenTheme);
		endScreen.show(getSupportFragmentManager(), "Hi");
	}

	public void countinueGame() {
		gameFlag = true;
		updateHearts(1);
	}

	public void stopGame() {
		showEndDialog();
	}

	public void stopGame(View v) {
		// counter.cancel();
	}
}
