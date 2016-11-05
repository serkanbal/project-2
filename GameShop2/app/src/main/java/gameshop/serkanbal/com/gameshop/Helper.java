package gameshop.serkanbal.com.gameshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serkan on 31/10/16.
 */

public class Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gamedb";
    public static final String INFO_TABLE_NAME = "generalinfo";
    public static final String COL_ID_INFO = "id_info";
    public static final String COL_ID_INFO2 = "id_info2";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_COMPANY = "company";
    public static final String COL_RATING = "rating";

    private static final String INFO_TABLE_CREATE = "CREATE TABLE " +
            INFO_TABLE_NAME + " (" +
            COL_ID_INFO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT," +
            COL_DESCRIPTION + " TEXT," +
            COL_COMPANY + " TEXT," +
            COL_RATING + " REAL )";

    private static final String INFO_TABLE_DELETE = "DROP TABLE IF EXISTS " +
            INFO_TABLE_NAME;

    public static final String DETAIL_TABLE_NAME = "detail";
    public static final String COL_ID_DETAIL = "id_detail";
    public static final String COL_PLATFORM = "platform";
    public static final String COL_AVAILABILITY = "availability";
    public static final String COL_PRICE = "price";

    private static final String DETAIL_TABLE_CREATE = "CREATE TABLE " +
            DETAIL_TABLE_NAME + " (" +
            COL_ID_DETAIL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_ID_INFO2 + " INTEGER," +
            COL_PLATFORM + " TEXT," +
            COL_AVAILABILITY + " TEXT," +
            COL_PRICE + " REAL )";

    private static final String DETAIL_TABLE_DELETE = "DROP TABLE IF EXISTS " +
            DETAIL_TABLE_NAME;

    private static Helper sInstance;

    public static Helper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new Helper(context.getApplicationContext());
        return sInstance;
    }

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(INFO_TABLE_CREATE);
        sqLiteDatabase.execSQL(DETAIL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(INFO_TABLE_DELETE);
        sqLiteDatabase.execSQL(DETAIL_TABLE_DELETE);
        onCreate(sqLiteDatabase);
    }

    public List<Game> getAllGames() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2;

        Cursor cursor = db.rawQuery(query,null);

        List<Game> game = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                game.add(new Game(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))));
                cursor.moveToNext();
            }cursor.close();
        }
        return game;
    }

    public List<Game> getAllGamesFilteredByPrice() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " ORDER BY " + COL_PRICE;

        Cursor cursor = db.rawQuery(query,null);

        List<Game> game = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                game.add(new Game(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))));
                cursor.moveToNext();
            }cursor.close();
        }
        return game;
    }

    public List<Game> getAllGamesFilteredByPlatform() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " ORDER BY " + COL_PLATFORM;

        Cursor cursor = db.rawQuery(query,null);

        List<Game> game = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                game.add(new Game(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))));
                cursor.moveToNext();
            }cursor.close();
        }
        return game;
    }

    public List<Game> getAllGamesFilteredByRating() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " ORDER BY " + COL_RATING;

        Cursor cursor = db.rawQuery(query,null);

        List<Game> game = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                game.add(new Game(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))));
                cursor.moveToNext();
            }cursor.close();
        }
        return game;
    }

    public List<Game> itemSearchForNameOrType(String query) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " WHERE " + COL_NAME + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_COMPANY + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_PLATFORM + " LIKE " + "'%" + query + "%'",null);

        List<Game> itemName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Game item = new Game(cursor.getString(cursor.getColumnIndex(COL_NAME)), null,
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))
                        );
                itemName.add(item);
                cursor.moveToNext();
            }
        }cursor.close();
        return itemName;
    }

    public List<Game> itemSearchForNameOrTypeFilterByPrice(String query) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " WHERE " + COL_NAME + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_COMPANY + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_PLATFORM + " LIKE " + "'%" + query + "%'" + " ORDER BY " + COL_PRICE,null);

        List<Game> itemName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Game item = new Game(cursor.getString(cursor.getColumnIndex(COL_NAME)), null,
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))
                );
                itemName.add(item);
                cursor.moveToNext();
            }
        }cursor.close();
        return itemName;
    }

    public List<Game> itemSearchForNameOrTypeFilterByRating(String query) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " WHERE " + COL_NAME + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_COMPANY + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_PLATFORM + " LIKE " + "'%" + query + "%'" + " ORDER BY " + COL_RATING,null);

        List<Game> itemName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Game item = new Game(cursor.getString(cursor.getColumnIndex(COL_NAME)), null,
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))
                );
                itemName.add(item);
                cursor.moveToNext();
            }
        }cursor.close();
        return itemName;
    }

    public List<Game> itemSearchForNameOrTypeFilterByPlatform(String query) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " WHERE " + COL_NAME + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_COMPANY + " LIKE " + "'%" + query + "%'" +
                " OR " + COL_PLATFORM + " LIKE " + "'%" + query + "%'" + " ORDER BY " + COL_PLATFORM,null);

        List<Game> itemName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Game item = new Game(cursor.getString(cursor.getColumnIndex(COL_NAME)), null,
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))
                );
                itemName.add(item);
                cursor.moveToNext();
            }
        }cursor.close();
        return itemName;
    }

    public List<Game> getGameById(int detailId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + ", " + COL_ID_DETAIL + ", " + COL_DESCRIPTION + ", " +
                COL_COMPANY + ", " + COL_RATING + ", " + COL_ID_DETAIL + ", " + COL_AVAILABILITY +
                ", " + COL_PLATFORM + ", " + COL_PRICE + " FROM " + INFO_TABLE_NAME + " JOIN " +
                DETAIL_TABLE_NAME + " ON " + INFO_TABLE_NAME + "." + COL_ID_INFO + " = " +
                DETAIL_TABLE_NAME + "." + COL_ID_INFO2 + " WHERE " + COL_ID_DETAIL + " = " + detailId;

        Cursor cursor = db.rawQuery(query,null);

        List<Game> game = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                game.add(new Game(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COL_PLATFORM)),
                        cursor.getString(cursor.getColumnIndex(COL_AVAILABILITY)),
                        cursor.getDouble(cursor.getColumnIndex(COL_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COL_ID_DETAIL))));
                cursor.moveToNext();
            }cursor.close();
        }
        return game;
    }

}