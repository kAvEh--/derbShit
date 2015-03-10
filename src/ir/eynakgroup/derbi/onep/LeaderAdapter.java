package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.util.PersianReshape;

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class LeaderAdapter extends BaseAdapter {
	public String fonts = "BNazanin.ttf";
	private ArrayList<String[]> data;
	public LayoutInflater inflater;
	private Context context;
	boolean flag ;
	
	public LeaderAdapter(ArrayList<String[]> leadersData, Context context) {
		this.data = leadersData;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		TextView rank;
		TextView name;
		TextView score;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.leader_board_row, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.leader_name);
			viewHolder.rank = (TextView) convertView
					.findViewById(R.id.leader_rank);
			viewHolder.score = (TextView) convertView
					.findViewById(R.id.leader_score);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Typeface face = Typeface.createFromAsset(context.getAssets(), "font/"
				+ fonts + "");

		viewHolder.rank.setTypeface(face);
		viewHolder.rank.setText(PersianReshape.reshape(position + 1 + ""));
		viewHolder.name.setTypeface(face);
		if (data.get(position)[0] == "null")
			viewHolder.name.setText("-");
		else
			viewHolder.name
					.setText(PersianReshape.reshape(data.get(position)[0]));
		System.out.println(flag);
		if(flag)
			System.out.println("=="+data.get(position)[0]+" " +data.get(position)[1] + "   " + data.get(position)[2]);
		viewHolder.score.setTypeface(face);
		viewHolder.score.setText(PersianReshape.reshape(data.get(position)[1]));
		return convertView;
	}
	
	public void doSo(){
		System.out.println("SHIT");
		System.out.println(data.size());
		System.out.println("C"+getCount());
	}

}
