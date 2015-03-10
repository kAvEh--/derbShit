package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.Color;
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

	private ArrayList<Question> questions;
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

	QuestionGenerator _q_generator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_onep_question);
		questions = new ArrayList<Question>();
		_q_generator = new QuestionGenerator(GameActivity.this);
		ArrayList<Question> qz = _q_generator.getQuestionsObjects(0);
		for (int i = 0; i < qz.size(); i++) {
			questions.add(qz.get(i));
		}

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

		_question_body.setText(questions.get(_question_counter).getQuestion());

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		Collections.shuffle(numbers);
		for (int i = 0; i < numbers.size(); i++) {
			choices.get(i).setBackgroundColor(Color.GRAY);
			if (numbers.get(i) == 3) {
				choices.get(i).setText(
						questions.get(_question_counter).getAnswer());
				choices.get(i).setTag(1);
			} else {
				choices.get(i)
						.setText(
								questions.get(_question_counter)
										.getFalseChoice()[numbers.get(i)]);
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
		counter = new CountDownTimer(5000, 10) {

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

		ArrayList<Question> qz = _q_generator.getQuestionsObjects(0);
		for (int i = 0; i < qz.size(); i++) {
			questions.add(qz.get(i));
		}

		setQuestion();
	}

	public void onChoiceClick(View v) {
		if (gameFlag) {
			gameFlag = false;
			counter.cancel();
			int tag = Integer.parseInt(v.getTag().toString());
			switch (tag) {
			case 1:
				v.setBackgroundColor(Color.GREEN);
				updateScore(1);
				updateHearts(0);
				break;

			case 0:
				v.setBackgroundColor(Color.RED);
				timer.setProgress(0);
				updateHearts(-1);
				break;
			default:
				break;

			}
			
//			if((Integer) choices.get(0).getTag() == 1)
//				choices.get(0).setBackgroundColor(Color.GREEN);
//			else
//				choices.get(0).setBackgroundColor(Color.RED);
//			if((Integer) choices.get(1).getTag() == 1)
//				choices.get(1).setBackgroundColor(Color.GREEN);
//			else
//				choices.get(1).setBackgroundColor(Color.RED);
//			if((Integer) choices.get(2).getTag() == 1)
//				choices.get(2).setBackgroundColor(Color.GREEN);
//			else
//				choices.get(2).setBackgroundColor(Color.RED);
//			if((Integer) choices.get(3).getTag() == 1)
//				choices.get(3).setBackgroundColor(Color.GREEN);
//			else
//				choices.get(3).setBackgroundColor(Color.RED);
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
