package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.PersianReshape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private TextView score;
	private TextView heartNumber;
	private TextView question;
	private ProgressBar timer;
	private Button[] choices;
	private int questionNum = 0;
	private boolean popupIsOn = false;

	private ArrayList<String[]> questions;
	private String fonts = "BNazanin.ttf";

	// CountDownTimer counter;
	long currentTime;
	int time = 5;
	int minimumTime = 3;
	CountDownTimer counter;

	int lastScore;
	int hearts = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.one_player_question);
		lastScore = 0;
		questions = new ArrayList<String[]>();
		Typeface face = Typeface.createFromAsset(getAssets(), "font/" + fonts
				+ "");

		// Initializing activity elements
		questionNumber = (TextView) findViewById(R.id.tv_onep_q_number);
		score = (TextView) findViewById(R.id.tv_onp_score);
		heartNumber = (TextView) findViewById(R.id.tv_onep_hearts);
		question = (TextView) findViewById(R.id.tv_onep_question);
		timer = (ProgressBar) findViewById(R.id.pb_onep_timer);

		choices = new Button[4];
		choices[0] = (Button) findViewById(R.id.btn_choice1);
		choices[1] = (Button) findViewById(R.id.btn_choice2);
		choices[2] = (Button) findViewById(R.id.btn_choice3);
		choices[3] = (Button) findViewById(R.id.btn_choice4);

		questionNumber.setText(PersianReshape.reshape(questionNum + "/"
				+ ((questionNum / 10) + 1) * 10));

		questionNumber.setTypeface(face);
		heartNumber.setTypeface(face);
		score.setTypeface(face);

		timer.setMax(time * 10);
		timer.setProgress(timer.getMax());
		// runTimer(time);

		setNextQuestion();
		super.onCreate(savedInstanceState);
	}

	synchronized public void onChoiceClick(View v) {
		System.out.println("onChoiceClick invoked");
		if (!popupIsOn) {
			int tag = Integer.parseInt(v.getTag().toString());
			// counter.cancel();
			switch (tag) {
			case 1:
				// TODO the right answer
				updateScore(1);
				updateHearts(0);
				break;

			case 0:
				// TODO the wrong answer
				timer.setProgress(0);
				updateHearts(-1);
				break;
			default:
				break;

			}
		}
		System.out.println("onChoiceClick finished");
	}

	/**
	 * this function set the "1" tag to the right choice button and "0" to
	 * others
	 * 
	 * @param rightChoice
	 *            : the number of the right choice
	 */
	synchronized protected void setAnswer(int rightChoice) {
		System.out.println("setAnswer invoked");
		choices[0].setTag(0);
		choices[1].setTag(0);
		choices[2].setTag(0);
		choices[3].setTag(0);
		choices[rightChoice].setTag(1);
		System.out.println("setAnswer finished");
	}

	/**
	 * this function runs a timer for calculating score of user
	 * 
	 * @param time
	 *            : the time that should be considered for question time
	 */
	synchronized public void runTimer(long time) {
		System.out.println("runTimer invoked");
		if (!popupIsOn) {
			currentTime = time;
			counter = new CountDownTimer(time * 1000, 1) {

				@Override
				public void onTick(long milli) {
					timer.setProgress((int) milli / 100);
					currentTime = milli;
				}

				@Override
				public void onFinish() {
					// counter.cancel();
					timer.setProgress(0);
					updateHearts(-1);
				}
			};
			counter.start();
		}
		System.out.println("runTimer finished");
	}

	/**
	 * this function updates user number of hearts
	 * 
	 * @param change
	 *            : the change that should be applied to user hearts number
	 * @return true if resuming game is available for user and false otherwise
	 */
	synchronized public void updateHearts(int change) {
		System.out.println("updateHearts invoked");
		if (!popupIsOn) {
			hearts = hearts + change;
			heartNumber.setText(hearts + "");
			showState();
			if (hearts < 1) {
				stopGame();
				System.out.println("IF");
				showEndDialog();
			} else {
				System.out.println("ELSE");
				setNextQuestion();
			}
		}
		System.out.println("updateHearts finished");
	}

	/**
	 * this function updates user score
	 * 
	 * @param change
	 *            : the change that should be applied to the score of user
	 */
	synchronized public void updateScore(int change) {
		System.out.println("updateScore invoked");
		if (!popupIsOn) {
			int currentScore = Integer.parseInt(score.getText().toString());
			currentScore = currentScore + change;
			score.setText(currentScore + "");
			lastScore = currentScore;
		}
		System.out.println("updateScore finished");
	}

	/**
	 * this method sets the question and choices
	 * 
	 * @param qAndAs
	 *            : the array of string which contains the question and the
	 *            choices.the question should be in the first index. the correct
	 *            answer should be in the second index of array. the choices
	 *            will be set at random.
	 */
	synchronized public void setQuestion(String[] qAndAs) {
		System.out.println("setQuestion invoked");
		if (!popupIsOn) {
			question.setText(qAndAs[0]);

			List<Integer> numbers = new ArrayList<Integer>();
			numbers.add(1);
			numbers.add(2);
			numbers.add(3);
			numbers.add(4);
			Collections.shuffle(numbers);
			for (int i = 0; i < numbers.size(); i++) {
				choices[i].setText(qAndAs[numbers.get(i)]);
				if (numbers.get(i) == 1) {
					setAnswer(i);
				}
			}
		}
		System.out.println("setQuestion finished");
	}

	/**
	 * this function sets the next question from the questions ArrayList
	 */
	synchronized public boolean setNextQuestion() {
		System.out.println("setNextQuestion invoked");
		if (!popupIsOn) {
			if (questions.isEmpty()) {
				// TODO read new questions
				String[] temp = { "???", "O", "X", "X", "X" };
				questions.add(temp);
			}
			if (counter != null) {
				System.out.println(">>>>>>>>>>>>>>>>>");
				System.out.println(counter);
				counter.cancel();
				counter = null;
				System.out.println(counter);
				System.out.println("<<<<<<<<<<<<<<<<<");
			}
			setQuestion(questions.get(0));
			questions.remove(0);
			questionNum++;
			questionNumber.setText(questionNum + "/" + ((questionNum / 10) + 1)
					* 10);
			if (questionNum % 10 == 0) {
				updateHearts(1);
				time = Math.max(--time, minimumTime);
			}
			timer.setMax(time * 10);
			timer.setProgress(timer.getMax());
			runTimer(time);
			return true;
		}
		System.out.println("setNextQuestion finished");
		return false;
	}

	/**
	 * this method shows end game dialog to user
	 */
	synchronized protected void showEndDialog() {
		// TODO
		System.out.println("showEndDialog invoked");
		stopGame();
		popupIsOn = true;
		EndGameFragment endScreen = new EndGameFragment();
		endScreen.setScore(lastScore);
		endScreen.setStyle(DialogFragment.STYLE_NO_TITLE,
				R.style.FullscreenTheme);
		endScreen.show(getSupportFragmentManager(), "Hi");
		System.out.println("showEndDialog finished");
	}

	public void countinueGame() {
		// TODO Auto-generated method stub
		hearts = 1;
		popupIsOn = false;
		updateHearts(0);
	}

	public void stopGame() {
		// counter.cancel();
	}

	public void stopGame(View v) {
		// counter.cancel();
	}

	public void showState() {
		System.out.println("SCORE:" + score + ",HEARTS:" + hearts + ",QNUM:"
				+ questionNum);
	}
}
