// src/main/java/com/example/agridata/ProfileActivity.java
package com.example.agridata; // πακέτο com.example.agridata

import android.annotation.SuppressLint;
import android.os.Bundle; // εισαγωγή android.os.Bundle
import androidx.appcompat.app.AppCompatActivity; // εισαγωγή androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView; // εισαγωγή android.widget.ImageView
import android.widget.TextView; // εισαγωγή android.widget.TextView

public class ProfileActivity extends AppCompatActivity { // δημόσια κλάση ProfileActivity που επεκτείνει το AppCompatActivity

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) { // υπερκαλυμμένη μέθοδος onCreate που δέχεται ένα Bundle
        super.onCreate(savedInstanceState); // κλήση της μεθόδου super για την αρχικοποίηση του state
        setContentView(R.layout.activity_profile); // ρύθμιση του περιεχομένου της προβολής στην activity_profile

        ImageView profilePicture = findViewById(R.id.profile_picture); // σύνδεση του profilePicture με το profile_picture
        TextView profileName = findViewById(R.id.profile_name); // σύνδεση του profileName με το profile_name
        TextView profileAge = findViewById(R.id.profile_age); // σύνδεση του profileAge με το profile_age
        TextView profileLocation = findViewById(R.id.profile_location); // σύνδεση του profileLocation με το profile_location
        TextView farmName = findViewById(R.id.farm_name); // σύνδεση του farmName με το farm_name
        TextView farmSize = findViewById(R.id.farm_size); // σύνδεση του farmSize με το farm_size
        TextView farmCrops = findViewById(R.id.farm_crops); // σύνδεση του farmCrops με το farm_crops
        TextView farmLivestock = findViewById(R.id.farm_livestock); // σύνδεση του farmLivestock με το farm_livestock

        // Δεδομένα υποδείγματος για το προφίλ του αγρότη
        FarmerProfile farmerProfile = new FarmerProfile( // δημιουργία νέου FarmerProfile
                "Xhin Zhao", // όνομα
                45, // ηλικία
                "Crete, Greece", // τοποθεσία
                "Creta Farms", // όνομα φάρμας
                150, // μέγεθος φάρμας
                "Rice, Corn, Cotton", // καλλιέργειες
                "55 Cows, 219 Chickens, 62 Sheep" // ζώα
        );

        profileName.setText("Name: " + farmerProfile.getName()); // ρύθμιση του κειμένου του profileName
        profileAge.setText("Age: " + farmerProfile.getAge()); // ρύθμιση του κειμένου του profileAge
        profileLocation.setText("Location: " + farmerProfile.getLocation()); // ρύθμιση του κειμένου του profileLocation
        farmName.setText("Farm Name: " + farmerProfile.getFarmName()); // ρύθμιση του κειμένου του farmName
        farmSize.setText("Farm Size: " + farmerProfile.getFarmSize() + " acres"); // ρύθμιση του κειμένου του farmSize
        farmCrops.setText("Crops: " + farmerProfile.getCrops()); // ρύθμιση του κειμένου του farmCrops
        farmLivestock.setText("Livestock: " + farmerProfile.getLivestock()); // ρύθμιση του κειμένου του farmLivestock

    }
}
