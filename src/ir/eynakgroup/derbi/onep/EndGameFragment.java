package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.util.DatabaseHandler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndGameFragment extends DialogFragment {

	private int userScore;
	private TextView score;
	private Button main;
	private Button buyHeart;
	private DatabaseHandler db;
	private int HeartCost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		db = new DatabaseHandler(getActivity());
		HeartCost = calculateCost();
		View rootView = inflater.inflate(R.layout.one_player_end, container,
				false);
		score = (TextView) rootView.findViewById(R.id.tv_onep_end_score);
		main = (Button) rootView.findViewById(R.id.btn_onep_main_menu);
		buyHeart = (Button) rootView.findViewById(R.id.btn_onep_buyHeart);

		main.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						OnePlayerActivity.class);
				startActivity(intent);
			}
		});

		buyHeart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO increasing hearts of user and continuing game
				int coins = db.getCoins();
				System.out.println("COINS: -- " + coins + "$");
				if (coins < HeartCost) {
					// TODO show user dialog for buying coins
				} else {
					GameActivity act = (GameActivity) getActivity();
					db.updateCoins(-HeartCost);
					act.countinueGame();
					dismiss();
				}
			}
		});
		score.setText(userScore + "");
		return rootView;
	}

	private int calculateCost() {
		// TODO Auto-generated method stub
		return 30;
	}

	public void setScore(int lastScore) {
		userScore = lastScore;
	}

}