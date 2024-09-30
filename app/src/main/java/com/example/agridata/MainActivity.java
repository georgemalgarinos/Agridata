// src/main/java/com/example/agridata/MainActivity.java
package com.example.agridata; // πακέτο com.example.agridata

import android.content.Intent; // εισαγωγή android.content.Intent
import android.os.Bundle; // εισαγωγή android.os.Bundle
import androidx.appcompat.app.AppCompatActivity; // εισαγωγή androidx.appcompat.app.AppCompatActivity
import android.widget.Button; // εισαγωγή android.widget.Button

public class MainActivity extends AppCompatActivity { // δημόσια κλάση MainActivity που επεκτείνει το AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) { // υπερκαλυμμένη μέθοδος onCreate που δέχεται ένα Bundle
        super.onCreate(savedInstanceState); // κλήση της μεθόδου super για την αρχικοποίηση του state
        setContentView(R.layout.activity_main); // ρύθμιση του περιεχομένου της προβολής στην activity_main

        Button btnInventory = findViewById(R.id.btn_inventory); // κουμπί btnInventory που συνδέεται με το btn_inventory
        Button btnOrders = findViewById(R.id.btn_orders); // κουμπί btnOrders που συνδέεται με το btn_orders
        Button btnProfile = findViewById(R.id.btn_profile); // κουμπί btnProfile που συνδέεται με το btn_profile
        Button btnSettings = findViewById(R.id.btn_settings); // κουμπί btnSettings που συνδέεται με το btn_settings

        btnInventory.setOnClickListener(v -> { // ορισμός click listener για το btnInventory
            Intent intent = new Intent(MainActivity.this, InventoryActivity.class); // δημιουργία intent για το InventoryActivity
            startActivity(intent); // έναρξη του InventoryActivity
        });

        btnOrders.setOnClickListener(v -> { // ορισμός click listener για το btnOrders
            Intent intent = new Intent(MainActivity.this, OrdersActivity.class); // δημιουργία intent για το OrdersActivity
            startActivity(intent); // έναρξη του OrdersActivity
        });

        btnProfile.setOnClickListener(v -> { // ορισμός click listener για το btnProfile
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class); // δημιουργία intent για το ProfileActivity
            startActivity(intent); // έναρξη του ProfileActivity
        });

        btnSettings.setOnClickListener(v -> { // ορισμός click listener για το btnSettings
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class); // δημιουργία intent για το SettingsActivity
            startActivity(intent); // έναρξη του SettingsActivity
        });
    }
}
