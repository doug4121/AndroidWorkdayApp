package doug.workdaysapp;


import android.content.Context;
//import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "workday";
	public static final String[] TABLE_COLUMNS = new String[]{
		"_id",  
		"date",
		"shift",
		"job", 
		"foremanName", 
		"hours", 
		"overtimeHours", 
		"payScale",
		"overtimePayScale",  
		"comments"
		};
	
	private static final String DATABASE_NAME = "workday.db";
	private static final int DATABASE_VERSION = 1;
	
	private static String DATABASE_CREATE = "";
	
	// constructor
	public MySQLiteHelper(Context context){
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		DATABASE_CREATE = generateCreationSQL();
	}
	
	private static final String generateCreationSQL()
	{
		String sql = "create table " + TABLE_NAME + "(";
		
		for (int i = 0; i < TABLE_COLUMNS.length; i++)
		{
			//id location
			if (i == 0)
			{
				sql += TABLE_COLUMNS[i] + " integer primary key autoincrement";
			}
			else if(i == 1)
			{
				sql += ", " + TABLE_COLUMNS[i] + " integer not null";
			}
			else if(i >= 5 && i <= 8)
			{
				sql += ", " + TABLE_COLUMNS[i] + " double not null";
			}
			else
			{
				sql += ", " + TABLE_COLUMNS[i] + " text not null";
			}
		}
		
		sql += ");";
		
		return sql;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
