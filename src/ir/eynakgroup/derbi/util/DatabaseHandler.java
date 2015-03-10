package ir.eynakgroup.derbi.util;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHandler extends SQLiteAssetHelper {

	// database version
	private static final int DATABASE_VERSION = 1;

	// database name
	private static final String DATABASE_NAME = "derbi.db";

	// tables name
	private static final String TABLE_USER_INFO = "userInfo";
	private static final String TABLE_PLAYERS = "players";
	private static final String TABLE_LEAGUE = "league";

	// user Info columns
	private static final String KEY_USER_NAME = "name";
	private static final String KEY_USER_TEAM = "team";
	private static final String KEY_USER_COINS = "coins";
	private static final String KEY_USER_GCM_ID = "gcmId";

	// players columns
	private static final String KEY_PLAYER_BIRTH_DATE = "birth_date";
	private static final String KEY_PLAYER_NATIONAL_GOALS = "national_goals";
	private static final String KEY_PLAYER_NATIONAL_MATCH = "national_match";
	private static final String KEY_PLAYER_BIRTH_PLACE = "birth_date";
	private static final String KEY_PLAYER_HEIGHT = "height";
	private static final String KEY_PLAYER_GOAL_COUNT = "goal_count";
	private static final String KEY_PLAYER_MATCH_COUNT = "match_count";
	private static final String KEY_PLAYER_ENTERANCE_YEAR = "entrance_year";
	private static final String KEY_PLAYER_POSITION = "position";
	private static final String KEY_PLAYER_NAME = "name";
	private static final String KEY_PLAYER_TEAM = "team";
	private static final String KEY_PLAYER_CAPTAIN = "captain";

	// league columns
	private static final String KEY_LEAGUE_SEASON = "season";
	private static final String KEY_LEAGUE_TEAM = "team";
	private static final String KEY_LEAGUE_POSITION = "position";
	private static final String KEY_LEAGUE_POINTS = "points";

	// constructor
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		setForcedUpgrade(1);
	}

	// //////////////////// USER FUNCTIONS /////////////////////////
	// //////////////////////////////////////////////////////////////

	/**
	 * this function reads user team from database
	 * 
	 * @return a number which indicates user team
	 */
	public int getTeam() {
		String query = "SELECT " + KEY_USER_TEAM + " FROM " + TABLE_USER_INFO;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int coins = c.getInt(c.getColumnIndex(KEY_USER_TEAM));
			c.close();
			db.close();
			return coins;
		} else {
			c.close();
			db.close();
			return -1;
		}
	}

	/**
	 * this function reads user coins from database
	 * 
	 * @return the number of user coins
	 */
	public int getCoins() {
		String query = "SELECT " + KEY_USER_COINS + " FROM " + TABLE_USER_INFO;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int coins = c.getInt(c.getColumnIndex(KEY_USER_COINS));
			c.close();
			db.close();
			return coins;
		} else {
			c.close();
			db.close();
			return -1;
		}
	}

	/**
	 * this method updates the user coins
	 * 
	 * @param change
	 *            : the number of coins that should be added or removed
	 */
	public void updateCoins(int change) {
		String query = "UPDATE " + TABLE_USER_INFO + " SET " + KEY_USER_COINS
				+ " = " + KEY_USER_COINS + "+" + change;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	/**
	 * this method updates the user's team
	 * 
	 * @param team
	 *            : the number that indicates user's team
	 */
	public void updateTeam(int team) {
		String query = "UPDATE " + TABLE_USER_INFO + " SET " + KEY_USER_TEAM
				+ " = " + team;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	/**
	 * this method updates the gcmId that used for GCM service
	 * 
	 * @param gcmId
	 *            : the gcmId of user
	 */
	public void updateGCM(String gcmId) {
		String query = "UPDATE " + TABLE_USER_INFO + " SET " + KEY_USER_GCM_ID
				+ " = " + gcmId;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	/**
	 * this method updates the name of user
	 * 
	 * @param name
	 *            : the name of user
	 */
	public void updateName(String name) {
		String query = "UPDATE " + TABLE_USER_INFO + " SET " + KEY_USER_NAME
				+ " = " + name;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	// //////////////////// PLAYER FUNCTIONS /////////////////////////
	// ///////////////////////////////////////////////////////////////

	/**
	 * this method returns a player name from database at random
	 * 
	 * @param position
	 * @return the name of player with the position
	 */
	public String getRandomPlayerByPosition(int position) {
		String query = "SELECT " + KEY_PLAYER_NAME + " FROM " + TABLE_PLAYERS
				+ " WHERE " + KEY_PLAYER_POSITION + " = " + position + " AND "
				+ KEY_PLAYER_TEAM + " = " + 1 // TODO getTeam()
				+ " ORDER BY RANDOM() LIMIT 1";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			String playerName = c.getString(c.getColumnIndex(KEY_PLAYER_NAME));
			c.close();
			db.close();
			return playerName;
		} else {
			c.close();
			db.close();
			return "";
		}
	}

	/**
	 * this method returns a player name from database at random
	 * 
	 * @param position
	 * @return the name of player with the position that is not equals to input
	 */
	public String getRandomPlayerByPositionN(int position) {
		String query = "SELECT " + KEY_PLAYER_NAME + " FROM " + TABLE_PLAYERS
				+ " WHERE " + KEY_PLAYER_POSITION + " != " + position + " AND "
				+ KEY_PLAYER_TEAM + " = " + 1 // TODO getTeam()
				+ " ORDER BY RANDOM() LIMIT 1";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			String playerName = c.getString(c.getColumnIndex(KEY_PLAYER_NAME));
			c.close();
			db.close();
			return playerName;
		} else {
			c.close();
			db.close();
			return "";
		}
	}

	/**
	 * 
	 * @param position
	 *            : the position of player that must be equal to result position
	 * @return
	 */
	public ArrayList<String> getRandomPlayersByPosition(int position) {
		String query = "SELECT " + KEY_PLAYER_NAME + " FROM " + TABLE_PLAYERS
				+ " WHERE " + KEY_PLAYER_POSITION + " = " + position + " AND "
				+ KEY_PLAYER_TEAM + " = " + 1 // TODO getTeam()
				+ " ORDER BY RANDOM() LIMIT 3";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		ArrayList<String> temp = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				temp.add(c.getString(c.getColumnIndex(KEY_PLAYER_NAME)));
			} while (c.moveToNext());
			c.close();
			db.close();
			return temp;
		} else {
			c.close();
			db.close();
			return null;
		}
	}

	/**
	 * 
	 * @param position
	 *            : the position of player that must not be equal to result
	 *            position
	 * @return
	 */
	public ArrayList<String> getRandomPlayersByPositionN(int position) {
		String query = "SELECT " + KEY_PLAYER_NAME + " FROM " + TABLE_PLAYERS
				+ " WHERE " + KEY_PLAYER_POSITION + " != " + position + " AND "
				+ KEY_PLAYER_TEAM + " = " + 1 // TODO getTeam()
				+ " ORDER BY RANDOM() LIMIT 3";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		ArrayList<String> temp = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				temp.add(c.getString(c.getColumnIndex(KEY_PLAYER_NAME)));
			} while (c.moveToNext());
			c.close();
			db.close();
			return temp;
		} else {
			c.close();
			db.close();
			return null;
		}
	}

	public ArrayList<Player> getTeamPlayers(int team) {

		ArrayList<Player> temp = new ArrayList<Player>();
		String str = "SELECT * FROM " + TABLE_PLAYERS + " WHERE "
				+ KEY_PLAYER_TEAM + " = " + team;
		System.out.println(str);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(str, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String name = c.getString(c.getColumnIndex(KEY_PLAYER_NAME));
				String birthDate = c.getString(c
						.getColumnIndex(KEY_PLAYER_BIRTH_DATE));
				String birthPlace = c.getString(c
						.getColumnIndex(KEY_PLAYER_BIRTH_PLACE));
				int position = c.getInt(c.getColumnIndex(KEY_PLAYER_POSITION));
				int entranceYear = c.getInt(c
						.getColumnIndex(KEY_PLAYER_ENTERANCE_YEAR));
				int matchCount = c.getInt(c
						.getColumnIndex(KEY_PLAYER_MATCH_COUNT));
				int goalCount = c.getInt(c
						.getColumnIndex(KEY_PLAYER_GOAL_COUNT));
				int nationalMatch = c.getInt(c
						.getColumnIndex(KEY_PLAYER_NATIONAL_MATCH));
				int nationalGoal = c.getInt(c
						.getColumnIndex(KEY_PLAYER_NATIONAL_GOALS));
				int height = c.getInt(c.getColumnIndex(KEY_PLAYER_HEIGHT));
				int captain = c.getInt(c.getColumnIndex(KEY_PLAYER_CAPTAIN));
				temp.add(new Player(name, birthDate, team, position,
						entranceYear, matchCount, goalCount, height,
						nationalMatch, nationalGoal, birthPlace, captain));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return temp;
	}

	// //////////////////// LEAGUE FUNCTIONS /////////////////////////
	// ///////////////////////////////////////////////////////////////

	/**
	 * this function reads data of a team in league
	 * 
	 * @param team
	 * @return ArrayList of League objects
	 */
	public ArrayList<League> getTeamLeague(int team) {

		ArrayList<League> temp = new ArrayList<League>();
		String str = "SELECT * FROM " + TABLE_LEAGUE + " WHERE "
				+ KEY_LEAGUE_TEAM + " = " + team;
		System.out.println(str);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(str, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String season = c
						.getString(c.getColumnIndex(KEY_LEAGUE_SEASON));
				int position = c.getInt(c.getColumnIndex(KEY_LEAGUE_POSITION));
				int points = c.getInt(c.getColumnIndex(KEY_LEAGUE_POINTS));
				temp.add(new League(season, team, position, points));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return temp;
	}

}
