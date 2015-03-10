package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class OnePlayerActivity extends Activity {
	ImageButton newGame;
	ImageButton leaderBoard;

	TextView mainText;

	int _team;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		_team = db.getTeam();
		db.close();

		if (_team == getResources().getInteger(R.integer.team_pe)) {
			setContentView(R.layout.activity_onep_pe_main);
		} else if (_team == getResources().getInteger(R.integer.team_es)) {
			setContentView(R.layout.activity_onep_es_main);
		} else {
			setContentView(R.layout.activity_onep_es_main);
		}

		mainText = (TextView) findViewById(R.id.onep_main_text);

		String text1 = "حمید ملک";
		String text2 = " پرچم ";
		String text3 = "بارسا";
		String text4 = " رو بالا برده.";

		SpannableString spannable;
		if (_team == getResources().getInteger(R.integer.team_pe)) {
			spannable = new SpannableString(text1 + text2
					+ text3 + text4);
			// here we set the color
			spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.perspolis_red)), 0,
					text1.length(), 0);
			spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.perspolis_red)),
					text1.length() + text2.length(),
					text1.length() + text2.length() + text3.length(), 0);
		} else {
			spannable = new SpannableString(text1 + text2
					+ text3 + text4);
			// here we set the color
			spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.esteghlal_blue)), 0,
					text1.length(), 0);
			spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.esteghlal_blue)),
					text1.length() + text2.length(),
					text1.length() + text2.length() + text3.length(), 0);
		}
		mainText.setText(spannable);

		newGame = (ImageButton) findViewById(R.id.onep_start);
		// leaderBoard = (Button) findViewById(R.id.btn_onep_leader);

		newGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						GameActivity.class);
				startActivity(intent);
			}
		});
		//
		// leaderBoard.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(getApplicationContext(),
		// OPLeaderBoardActivity.class);
		// startActivity(intent);
		//
		// }
		// });

		super.onCreate(savedInstanceState);
	}
}
