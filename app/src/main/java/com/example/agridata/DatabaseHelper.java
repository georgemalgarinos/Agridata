// src/main/java/com/example/agridata/DatabaseHelper.java
package com.example.agridata; // πακέτο com.example.agridata

import android.annotation.SuppressLint;
import android.content.ContentValues; // εισαγωγή android.content.ContentValues
import android.content.Context; // εισαγωγή android.content.Context
import android.database.Cursor; // εισαγωγή android.database.Cursor
import android.database.sqlite.SQLiteDatabase; // εισαγωγή android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper; // εισαγωγή android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList; // εισαγωγή java.util.ArrayList

public class DatabaseHelper extends SQLiteOpenHelper { // δημόσια κλάση DatabaseHelper που επεκτείνει το SQLiteOpenHelper

    private static final String DATABASE_NAME = "inventory.db"; // σταθερή DATABASE_NAME = "inventory.db"
    private static final int DATABASE_VERSION = 1; // σταθερή DATABASE_VERSION = 1

    private static final String TABLE_INVENTORY = "inventory"; // σταθερή TABLE_INVENTORY = "inventory"
    private static final String COLUMN_ID = "id"; // σταθερή COLUMN_ID = "id"
    private static final String COLUMN_NAME = "name"; // σταθερή COLUMN_NAME = "name"
    private static final String COLUMN_QUANTITY = "quantity"; // σταθερή COLUMN_QUANTITY = "quantity"
    private static final String COLUMN_UNIT = "unit"; // σταθερή COLUMN_UNIT = "unit"

    public DatabaseHelper(Context context) { // δημόσιος κατασκευαστής DatabaseHelper που δέχεται ένα Context
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // κλήση του υπερκατασκευαστή με context, DATABASE_NAME, null, DATABASE_VERSION
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // υπερκαλυμμένη μέθοδος onCreate που δέχεται ένα SQLiteDatabase
        String CREATE_TABLE = "CREATE TABLE " + TABLE_INVENTORY + "(" // δημιουργία συμβολοσειράς CREATE_TABLE
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // σύνδεση του COLUMN_ID με τύπο INTEGER και PRIMARY KEY AUTOINCREMENT
                + COLUMN_NAME + " TEXT," // σύνδεση του COLUMN_NAME με τύπο TEXT
                + COLUMN_QUANTITY + " INTEGER," // σύνδεση του COLUMN_QUANTITY με τύπο INTEGER
                + COLUMN_UNIT + " TEXT" // σύνδεση του COLUMN_UNIT με τύπο TEXT
                + ")"; // κλείσιμο της δημιουργίας πίνακα
        db.execSQL(CREATE_TABLE); // εκτέλεση της εντολής CREATE_TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // υπερκαλυμμένη μέθοδος onUpgrade που δέχεται ένα SQLiteDatabase, oldVersion και newVersion
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY); // εκτέλεση εντολής για διαγραφή πίνακα αν υπάρχει
        onCreate(db); // κλήση της onCreate για επαναδημιουργία του πίνακα
    }

    public void addInventoryItem(InventoryItem item) { // δημόσια μέθοδος addInventoryItem που δέχεται ένα InventoryItem
        SQLiteDatabase db = this.getWritableDatabase(); // απόκτηση του γράψιμου SQLiteDatabase
        ContentValues values = new ContentValues(); // δημιουργία νέου ContentValues
        values.put(COLUMN_NAME, item.getName()); // εισαγωγή του ονόματος στο ContentValues
        values.put(COLUMN_QUANTITY, item.getQuantity()); // εισαγωγή της ποσότητας στο ContentValues
        values.put(COLUMN_UNIT, item.getUnit()); // εισαγωγή της μονάδας στο ContentValues

        db.insert(TABLE_INVENTORY, null, values); // εισαγωγή των δεδομένων στον πίνακα TABLE_INVENTORY
        db.close(); // κλείσιμο της βάσης δεδομένων
    }

    public ArrayList<InventoryItem> getAllInventoryItems() { // δημόσια μέθοδος getAllInventoryItems που επιστρέφει ArrayList<InventoryItem>
        ArrayList<InventoryItem> itemList = new ArrayList<>(); // δημιουργία νέας λίστας αντικειμένων InventoryItem
        SQLiteDatabase db = this.getReadableDatabase(); // απόκτηση του αναγνώσιμου SQLiteDatabase
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTORY, null); // εκτέλεση ερώτησης για όλα τα στοιχεία του TABLE_INVENTORY

        if (cursor.moveToFirst()) { // έλεγχος αν ο cursor έχει τουλάχιστον ένα στοιχείο
            do {
                @SuppressLint("Range") InventoryItem item = new InventoryItem( // δημιουργία νέου InventoryItem
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), // απόκτηση ονόματος από τον cursor
                        cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)), // απόκτηση ποσότητας από τον cursor
                        cursor.getString(cursor.getColumnIndex(COLUMN_UNIT)) // απόκτηση μονάδας από τον cursor
                );
                itemList.add(item); // προσθήκη του item στη λίστα
            } while (cursor.moveToNext()); // επανάληψη για όλα τα στοιχεία
        }

        cursor.close(); // κλείσιμο του cursor
        db.close(); // κλείσιμο της βάσης δεδομένων
        return itemList; // επιστροφή της λίστας αντικειμένων
    }

    public void deleteInventoryItem(String name) { // δημόσια μέθοδος deleteInventoryItem που δέχεται ένα όνομα
        SQLiteDatabase db = this.getWritableDatabase(); // απόκτηση του γράψιμου SQLiteDatabase
        db.delete(TABLE_INVENTORY, COLUMN_NAME + " = ?", new String[]{name}); // διαγραφή στοιχείου από τον TABLE_INVENTORY όπου το όνομα είναι ίσο με το όνομα που δόθηκε
        db.close(); // κλείσιμο της βάσης δεδομένων
    }

    public void deleteAllInventoryItems() { // δημόσια μέθοδος deleteAllInventoryItems
        SQLiteDatabase db = this.getWritableDatabase(); // απόκτηση του γράψιμου SQLiteDatabase
        db.execSQL("DELETE FROM " + TABLE_INVENTORY); // διαγραφή όλων των στοιχείων από τον TABLE_INVENTORY
        db.close(); // κλείσιμο της βάσης δεδομένων
    }
}
