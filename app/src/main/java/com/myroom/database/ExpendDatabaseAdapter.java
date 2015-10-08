package com.myroom.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jobin on Jul 16.
 */
public class ExpendDatabaseAdapter {
    public PurchaseHelper purchaseHelper;

    ExpendDatabaseAdapter(Context context){
        purchaseHelper=new PurchaseHelper(context);
    }
    /** purchase local database operations  */
    public class PurchaseHelper extends SQLiteOpenHelper {
        public static final String TABLE_PURCHASE = "purchases";
        public static final String PURCHASE_ID = "_id";
        public static final String ITEM_NAME = "name";
        public static final String ITEM_PRICE = "price";
        public static final String ITEM_QUANTITY = "quantity";
        public static final String PIC_PATH = "pic_path";
        public static final String PURCHASE_DATE = "date";
        public static final String MEMBER_ID = "member_id";
        public static final String STATUS = "status";
        public static final String DATE_MODIFIED = "modified";
        private static final String DATABASE_NAME = "roomassist.db";
        private static final int DATABASE_VERSION = 1;

        // Database creation sql statement
        private static final String PURCHASE_CREATE = "create table "
                + TABLE_PURCHASE + "(" + PURCHASE_ID + " integer primary key, "
                + ITEM_NAME + " text not null,"
                + ITEM_PRICE + " text not null,"
                + ITEM_QUANTITY + " text not null,"
                + PIC_PATH + " text,"
                + PURCHASE_DATE + "text,"
                + MEMBER_ID + "text,"
                + STATUS + "text,"
                + DATE_MODIFIED + "text);";

        public PurchaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(PURCHASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int i, int i1) {
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE);
            onCreate(database);
        }
        public void loadAllData(){

        }
        public void syncData(){

        }
    }
    /** End of purchase local database   */

    /** Session local database   */
    public class SesssionHelper extends SQLiteOpenHelper {
        public SesssionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    /** End of Session local database   */

    /** Messenger local database   */
    public class MessengerHelper extends SQLiteOpenHelper {
        public MessengerHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    /** End of Messenger local database   */

    /** Statistic local database   */
    public class SatisticsHelper extends SQLiteOpenHelper {
        public SatisticsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    /** End of Satistics local database   */

    /** Members local database   */
    public class MembersHelper extends SQLiteOpenHelper {
        public MembersHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    /** End of Members local database   */
}
