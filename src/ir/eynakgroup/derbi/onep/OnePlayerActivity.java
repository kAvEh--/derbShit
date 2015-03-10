package ir.eynakgroup.derbi.onep;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OnePlayerActivity extends Activity {
	Button newGame;
	Button leaderBoard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.one_player_main);
		newGame = (Button) findViewById(R.id.one_player_start);
		leaderBoard = (Button) findViewById(R.id.btn_onep_leader);

		newGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						GameActivity.class);
				startActivity(intent);
			}
		});

		leaderBoard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						OPLeaderBoardActivity.class);
				startActivity(intent);

			}
		});

		super.onCreate(savedInstanceState);
	}
}
