package gameshop.serkanbal.com.gameshop.Data;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Serkan on 31/10/16.
 */

public class AssetHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "gamedb";
    private static final int DATABASE_VERSION = 1;

    public AssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
