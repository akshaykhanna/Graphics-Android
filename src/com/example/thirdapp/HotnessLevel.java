package com.example.thirdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HotnessLevel 
{
  private static final String DB_NAME="HotOrNot";
  private static final int DB_VERSION=1;
  private static final String DB_TABLE="People";
  public static final String KEY_ROW="id";
  private static final String KEY_NAME="person name";
  private static final String KEY_HOT="person hotness";
  private dbClass myHelper;
  private Context myContext;
  private SQLiteDatabase myDB;
  /*Since we do not want to make this HotnessLevel(UI) class or thread bulky
   * therefore we use a new thread in this thread  that is to create a sub class for handling DB
   */
  private class dbClass extends SQLiteOpenHelper
  {

	public dbClass(Context context) {
		super(context,DB_NAME,null, DB_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(" CREATE TABLE"+ DB_TABLE +" ("
				+KEY_ROW + "INTEGER PRIMARY KEY AUTOINCREMENT"
				+KEY_NAME + "TEXT NOT NULL"
				+KEY_HOT + "TEXT NOT NULL)"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		
		db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE);
		onCreate(db);
	}
	  
  }//End of sub Class ie "dbClass"  
  
  public HotnessLevel(Context c)
  {
	  myContext=c;
  }
  public HotnessLevel write() throws SQLException
  {
	myHelper=new dbClass(myContext);
	myDB=myHelper.getWritableDatabase();
	  return null;
	  
  }
  public void close()
  {
	  myHelper.close();
  }
public long createEnteries(String name, String hot) {
	// TODO Auto-generated method stub
	ContentValues cv=new ContentValues();
	cv.put(KEY_NAME, name);
	cv.put(KEY_HOT, hot);
	return myDB.insert(DB_TABLE, null, cv);
	
}
public String getData() {
	
	String columns[]={KEY_ROW,KEY_NAME,KEY_HOT};
	
	//Cursor let  us to read from database
	Cursor c=myDB.query(DB_TABLE, columns, null, null,null, null, null);
	String result="";
	int iRow=c.getColumnIndex(KEY_ROW);
	int iName=c.getColumnIndex(KEY_NAME);
	int iHot=c.getColumnIndex(KEY_HOT);
	for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
	{
		result+=c.getString(iRow)+ " "+c.getShort(iName)+" "+c.getString(iHot)+"\n";
	}
	return result;
}
}//end of HotnessLevel
