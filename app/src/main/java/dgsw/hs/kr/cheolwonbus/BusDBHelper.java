package dgsw.hs.kr.cheolwonbus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BusDBHelper extends SQLiteOpenHelper {

    public BusDBHelper(@Nullable Context context,@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,
                       int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table bus ( routeId varchar(20), isFavorite boolean)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table bus";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(String routeId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("routeId", routeId);
        value.put("isFavorite", false);
        return db.insert("bus", null, value);
    }

    public HashMap<String, Boolean> getAll(){
        HashMap<String, Boolean> result = new HashMap<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("bus", null, null, null, null, null, null);

        while(cursor.moveToNext()){
            result.put(cursor.getString(cursor.getColumnIndex("routeId")), (cursor.getInt(cursor.getColumnIndex("isFavorite")) > 0));
        }

        return result;
    }

    public ArrayList<String> getFavorite(){
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, Boolean> data = this.getAll();

        Iterator<String> keys = data.keySet().iterator();

        while(keys.hasNext()){
            String key = keys.next();
            if(data.get(key)){
                result.add(key);
            }
        }

        return result;
    }

    public long update(String routeId, boolean isFavorite){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("isFavorite", isFavorite);

        return db.update("bus", values, "routeId=?" , new String[] { routeId });
    }
}
