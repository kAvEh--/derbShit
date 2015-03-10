package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
			setContentView(R.layout.activity_onep_pe_main);
		}
		
		mainText = (TextView) findViewById(R.id.onep_main_text);
		
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
