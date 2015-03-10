package ir.eynakgroup.derbi;

import ir.eynakgroup.derbi.onep.OnePlayerActivity;
import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	Button onePlayerGame;
	Button twoPlayerGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.home);

		onePlayerGame = (Button) findViewById(R.id.one_player_button);
		twoPlayerGame = (Button) findViewById(R.id.two_player_button);

		onePlayerGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						OnePlayerActivity.class);
				startActivity(intent);

			}
		});
		super.onCreate(savedInstanceState);
	}
}
