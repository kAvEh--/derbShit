package ir.eynakgroup.derbi.onep;

import ir.eynakgroup.derbi.R;
import ir.eynakgroup.derbi.util.AppController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class OPLeaderBoardActivity extends FragmentActivity {
	private ViewPager pager;
	private MyPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opleader_board);
		pager = (ViewPager) findViewById(R.id.onep_leader_pager);

		adapter = new MyPagerAdapter(getSupportFragmentManager());

		// TODO reading api key from database
		getOnlineLeaderBoards("ba20d7787442bcfe22d3dc7bdbff9e93");

		pager.setOffscreenPageLimit(3);

	}

	private void getOnlineLeaderBoards(final String apiKey) {
		// TODO Auto-generated method stub
		String url = "http://eynakgroup.ir/derbi/v1/index.php/records";
		String tag_string_req = "string_req";
		StringRequest strReq = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					public void onResponse(String response) {
						ArrayList<ArrayList<String[]>> data = new ArrayList<ArrayList<String[]>>();
						ArrayList<String[]> leaders = new ArrayList<String[]>();
						ArrayList<String[]> leaders1 = new ArrayList<String[]>();
						ArrayList<String[]> leaders2 = new ArrayList<String[]>();

						try {
							JSONObject serverResult = new JSONObject(response);
							JSONArray array = serverResult
									.getJSONArray("all_time");
							for (int i = 0; i < array.length(); i++) {
								JSONObject temp = new JSONObject(array.get(i)
										.toString());
								String[] tempArray = {
										temp.getString("username"),
										temp.getString("score"),
										temp.getString("team") };
								leaders.add(tempArray);

							}
							data.add(leaders);
//							adapter.setData(0, leaders);
							JSONArray array1 = serverResult
									.getJSONArray("this_week");
							for (int i = 0; i < array1.length(); i++) {
								JSONObject temp = new JSONObject(array1.get(i)
										.toString());
								String[] tempArray = {
										temp.getString("username"),
										temp.getString("score"),
										temp.getString("team") };
								leaders1.add(tempArray);

							}
							data.add(leaders1);
//							adapter.setData(1, leaders1);
							leaders.clear();
							JSONArray array2 = serverResult
									.getJSONArray("prev_week");
							for (int i = 0; i < array2.length(); i++) {
								JSONObject temp = new JSONObject(array2.get(i)
										.toString());
								String[] tempArray = {
										temp.getString("username"),
										temp.getString("score"),
										temp.getString("team") };
								leaders2.add(tempArray);

							}
							data.add(leaders2);
//							adapter.setData(2, leaders2);
							writeData(data);
							// writeLeaders(leaders);

							// return leaders;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					public void onErrorResponse(VolleyError error) {
						// TODO
						System.out.println("Some Error in updateUserLog");
						System.out.println(error);
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Authorization", apiKey);
				return headers;
			}
		};
		AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

	}

	private void writeData(ArrayList<ArrayList<String[]>> data) {
		System.out.println("adapter" + adapter);
		System.out.println("data" + data);
		adapter.setData(0, data.get(0));
		adapter.setData(1, data.get(1));
		adapter.setData(2, data.get(2));
		pager.setAdapter(adapter);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		SparseArray<LeaderFragment> registeredFragments = new SparseArray<LeaderFragment>();
		private final String[] TITLES = { "کلی", "این هفته", "هفته پیش" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public void setData(int position, ArrayList<String[]> data) {
			getFragment(position).setData(data);
			if (position < 1)
				System.out.println(data.size());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return 3;
		}

		public LeaderFragment getFragment(int position) {
			return registeredFragments.get(position);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				LeaderFragment allTime = new LeaderFragment();
				allTime.flag = true;
				registeredFragments.append(0, allTime);
				return allTime;
			case 1:
				LeaderFragment thisWeek = new LeaderFragment();
				thisWeek.flag = false;
				registeredFragments.append(1, thisWeek);
				return thisWeek;
			case 2:
				LeaderFragment prevWeek = new LeaderFragment();
				prevWeek.flag = false;
				registeredFragments.append(2, prevWeek);
				return prevWeek;
			}
			return null;
		}

	}

}
