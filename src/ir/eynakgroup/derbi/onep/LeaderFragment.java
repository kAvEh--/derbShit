package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LeaderFragment extends Fragment {
	ArrayList<String[]> data;
	ListView leaderBoard;
	boolean flag = false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_leaders, container,
				false);
		leaderBoard = (ListView) rootView.findViewById(R.id.leaders_list);
		return rootView;
	}

	public void setData(ArrayList<String[]> data) {
		this.data = data;
		LeaderAdapter adapter = new LeaderAdapter(data, getActivity());
		System.out.println("--"+flag);
		if(flag){
			adapter.flag = true;
			System.out.println("flag is ok");
			System.out.println("))"+data.size());
			System.out.println(adapter.flag);
			adapter.doSo();
		}else{
			adapter.flag = false;
		}
		System.out.println(adapter.getView(3, null, null));
		leaderBoard.setAdapter(adapter);
	}

}
