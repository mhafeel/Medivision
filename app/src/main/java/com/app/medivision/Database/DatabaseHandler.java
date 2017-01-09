package com.app.medivision.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digital on 12/12/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "Medivision1";

    // Contacts table name
    private static final String TABLE_CONTACTS ="Medivisiontable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_Email = "email";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_Date = "date";
    private static final String KEY_Gender = "gender";
    private static final String KEY_Age = "age";
    private static final String KEY_Smoke = "smoke";
    private static final String KEY_Systoru = "systoru";
    private static final String KEY_Sysvalue = "sysvalue";
    private static final String KEY_Diovalue = "diovalue";
    private static final String KEY_Totalch = "totalch";
    private static final String KEY_Hdlch = "hdlch";
    private static final String KEY_Diabetes = "diabetes";
    private static final String KEY_Hba1c = "hba1c";
    private static final String KEY_Bmivalue = "bmivalue";
    private static final String KEY_Familyhistory = "familyhistory";
    private static final String KEY_Finalrisk = "finalrisk";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_Email + " TEXT, " + KEY_Date + " TEXT, " + KEY_PH_NO + " INTEGER, " + KEY_Gender + " TEXT, " + KEY_Age + " TEXT, " + KEY_Smoke + " TEXT, " + KEY_Systoru + " TEXT, " + KEY_Sysvalue + " TEXT, " + " TEXT, " + KEY_Diovalue + " TEXT, " + KEY_Totalch + " TEXT, " + KEY_Hdlch + " TEXT, " + KEY_Diabetes + " TEXT, " + KEY_Hba1c + " TEXT, " + KEY_Bmivalue + " INTEGER, " + KEY_Familyhistory + " TEXT, " + KEY_Finalrisk + " TEXT);";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(DatabaseModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName());
        values.put(KEY_Email, contact.getEmail());// Contact Name
        values.put(KEY_PH_NO, contact.getPhone());
        values.put(KEY_Date, contact.getDate());
        values.put(KEY_Gender, contact.getGender());
        values.put(KEY_Age, contact.getAge());
        values.put(KEY_Smoke, contact.getSmoker());
        values.put(KEY_Systoru, contact.getSystoru());
        values.put(KEY_Sysvalue, contact.getSysvalue());

        values.put(KEY_Diovalue, contact.getDiovalue());
        values.put(KEY_Totalch, contact.getTotalch());
        values.put(KEY_Hdlch, contact.getHdlch());
        values.put(KEY_Diabetes, contact.getDiabetes());
        values.put(KEY_Hba1c, contact.getHba1c());
        values.put(KEY_Bmivalue, contact.getBmivalue());
        values.put(KEY_Familyhistory, contact.getFamilyhistory());
        values.put(KEY_Finalrisk, contact.getFinalrisk());


        // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    public List<DatabaseModel> getAllContacts() {
        List<DatabaseModel> contactList = new ArrayList<DatabaseModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseModel contact = new DatabaseModel();
contact.setId(Integer.valueOf(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setPhone(cursor.getString(4));
                contact.setGender(cursor.getString(5));
                contact.setAge(cursor.getString(6));
                contact.setSmoker(cursor.getString(7));
                contact.setSystoru(cursor.getString(8));
                contact.setSysvalue(cursor.getString(9));

                contact.setDiovalue(cursor.getString(10));
                contact.setTotalch(cursor.getString(11));
                contact.setHdlch(cursor.getString(12));
                contact.setDiabetes(cursor.getString(13));
                contact.setHba1c(cursor.getString(14));
                contact.setBmivalue(cursor.getDouble(15));
                contact.setFamilyhistory(cursor.getString(16));
                contact.setFinalrisk(cursor.getString(17));


            //    contact.setPhone(Long.parseLong(cursor.getString(2)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     //   cursor.close();

        // return count
        return cursor.getCount();
    }

    // Deleting single contact
    public void deleteContact(DatabaseModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}