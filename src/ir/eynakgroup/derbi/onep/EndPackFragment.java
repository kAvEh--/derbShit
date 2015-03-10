package ir.eynakgroup.derbi.onep;

import android.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndPackFragment extends DialogFragment {

	private int userScore;
	private TextView score;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_end_pack, container,
				false);
		score = (TextView) rootView.findViewById(R.id.fragment_endpack_title);

		Button start = (Button) rootView
				.findViewById(R.id.fragment_endpack_continue);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((GameActivity) getActivity()).nextPackage();
				EndPackFragment.this.dismiss();
			}
		});

		score.setText(userScore + "");
		return rootView;
	}

	public void setScore(int s) {
		userScore = s;
	}

}