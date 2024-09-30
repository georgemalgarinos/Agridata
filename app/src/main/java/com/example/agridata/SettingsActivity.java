// src/main/java/com/example/agridata/SettingsActivity.java
package com.example.agridata; // πακέτο com.example.agridata

import android.content.SharedPreferences; // εισαγωγή android.content.SharedPreferences
import android.os.Bundle; // εισαγωγή android.os.Bundle
import androidx.appcompat.app.AppCompatActivity; // εισαγωγή androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate; // εισαγωγή androidx.appcompat.app.AppCompatDelegate
import android.widget.Button; // εισαγωγή android.widget.Button
import android.widget.RadioButton; // εισαγωγή android.widget.RadioButton
import android.widget.RadioGroup; // εισαγωγή android.widget.RadioGroup
import android.widget.Switch; // εισαγωγή android.widget.Switch
import android.widget.Toast; // εισαγωγή android.widget.Toast

public class SettingsActivity extends AppCompatActivity { // δημόσια κλάση SettingsActivity που επεκτείνει το AppCompatActivity

    private Switch switchDarkMode; // ιδιωτικό πεδίο switchDarkMode για το Switch
    private RadioGroup radioGroupFontSize; // ιδιωτικό πεδίο radioGroupFontSize για το RadioGroup
    private RadioButton radioFontSizeNormal; // ιδιωτικό πεδίο radioFontSizeNormal για το RadioButton
    private RadioButton radioFontSizeEnlarged; // ιδιωτικό πεδίο radioFontSizeEnlarged για το RadioButton
    private Button buttonApply; // ιδιωτικό πεδίο buttonApply για το Button

    private SharedPreferences sharedPreferences; // ιδιωτικό πεδίο sharedPreferences για το SharedPreferences
    private static final String PREFS_NAME = "settings_prefs"; // σταθερή PREFS_NAME = "settings_prefs"
    private static final String DARK_MODE_KEY = "dark_mode"; // σταθερή DARK_MODE_KEY = "dark_mode"
    private static final String FONT_SIZE_KEY = "font_size"; // σταθερή FONT_SIZE_KEY = "font_size"

    @Override
    protected void onCreate(Bundle savedInstanceState) { // υπερκαλυμμένη μέθοδος onCreate που δέχεται ένα Bundle
        super.onCreate(savedInstanceState); // κλήση της μεθόδου super για την αρχικοποίηση του state
        setContentView(R.layout.activity_settings); // ρύθμιση του περιεχομένου της προβολής στην activity_settings

        switchDarkMode = findViewById(R.id.switch_dark_mode); // σύνδεση του switchDarkMode με το switch_dark_mode
        radioGroupFontSize = findViewById(R.id.radio_group_font_size); // σύνδεση του radioGroupFontSize με το radio_group_font_size
        radioFontSizeNormal = findViewById(R.id.radio_font_size_normal); // σύνδεση του radioFontSizeNormal με το radio_font_size_normal
        radioFontSizeEnlarged = findViewById(R.id.radio_font_size_enlarged); // σύνδεση του radioFontSizeEnlarged με το radio_font_size_enlarged
        buttonApply = findViewById(R.id.button_apply); // σύνδεση του buttonApply με το button_apply

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE); // απόκτηση του SharedPreferences με το PREFS_NAME

        // Φόρτωση αποθηκευμένων προτιμήσεων
        loadPreferences(); // κλήση της μεθόδου loadPreferences

        buttonApply.setOnClickListener(v -> { // ορισμός click listener για το buttonApply
            boolean isDarkMode = switchDarkMode.isChecked(); // έλεγχος αν το switchDarkMode είναι ενεργοποιημένο
            int selectedFontSizeId = radioGroupFontSize.getCheckedRadioButtonId(); // απόκτηση του επιλεγμένου RadioButton από το radioGroupFontSize
            String fontSize = selectedFontSizeId == R.id.radio_font_size_normal ? "normal" : "enlarged"; // προσδιορισμός του μεγέθους γραμματοσειράς

            // Αποθήκευση προτιμήσεων
            savePreferences(isDarkMode, fontSize); // κλήση της μεθόδου savePreferences

            // Εφαρμογή Dark Mode
            if (isDarkMode) { // έλεγχος αν το Dark Mode είναι ενεργοποιημένο
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // ρύθμιση του Dark Mode
            } else { // διαφορετικά
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // απενεργοποίηση του Dark Mode
            }

            // Εφαρμογή Μεγέθους Γραμματοσειράς
            applyFontSize(fontSize); // κλήση της μεθόδου applyFontSize

            Toast.makeText(SettingsActivity.this, "Settings Applied", Toast.LENGTH_SHORT).show(); // εμφάνιση Toast με μήνυμα "Settings Applied"
        });
    }

    private void loadPreferences() { // ιδιωτική μέθοδος loadPreferences
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false); // απόκτηση της προτίμησης για το Dark Mode
        String fontSize = sharedPreferences.getString(FONT_SIZE_KEY, "normal"); // απόκτηση της προτίμησης για το μέγεθος γραμματοσειράς

        switchDarkMode.setChecked(isDarkMode); // ρύθμιση του switchDarkMode
        if ("normal".equals(fontSize)) { // έλεγχος αν το μέγεθος γραμματοσειράς είναι "normal"
            radioFontSizeNormal.setChecked(true); // ρύθμιση του radioFontSizeNormal ως επιλεγμένο
        } else { // διαφορετικά
            radioFontSizeEnlarged.setChecked(true); // ρύθμιση του radioFontSizeEnlarged ως επιλεγμένο
        }
    }

    private void savePreferences(boolean isDarkMode, String fontSize) { // ιδιωτική μέθοδος savePreferences που δέχεται isDarkMode και fontSize
        SharedPreferences.Editor editor = sharedPreferences.edit(); // απόκτηση του Editor για το SharedPreferences
        editor.putBoolean(DARK_MODE_KEY, isDarkMode); // αποθήκευση της προτίμησης για το Dark Mode
        editor.putString(FONT_SIZE_KEY, fontSize); // αποθήκευση της προτίμησης για το μέγεθος γραμματοσειράς
        editor.apply(); // εφαρμογή των αλλαγών
    }

    private void applyFontSize(String fontSize) { // ιδιωτική μέθοδος applyFontSize που δέχεται fontSize
        // Υλοποίηση αλλαγών στο μέγεθος γραμματοσειράς (π.χ., προσαρμογή μιας παγκόσμιας ρύθμισης μεγέθους κειμένου ή εφαρμογή στυλ)
        // Αυτό μπορεί να διαφέρει ανάλογα με τον τρόπο που θέλετε να χειριστείτε τα μεγέθη γραμματοσειράς στην εφαρμογή σας
        // Για απλότητα, θα εμφανίσουμε ένα μήνυμα toast
        if ("normal".equals(fontSize)) { // έλεγχος αν το μέγεθος γραμματοσειράς είναι "normal"
            // Εφαρμογή κανονικού μεγέθους γραμματοσειράς (Αυτό είναι μόνο ένα παράδειγμα)
            Toast.makeText(this, "Font size set to Normal", Toast.LENGTH_SHORT).show(); // εμφάνιση Toast με μήνυμα "Font size set to Normal"
        } else { // διαφορετικά
            // Εφαρμογή μεγαλύτερου μεγέθους γραμματοσειράς (Αυτό είναι μόνο ένα παράδειγμα)
            Toast.makeText(this, "Font size set to Enlarged", Toast.LENGTH_SHORT).show(); // εμφάνιση Toast με μήνυμα "Font size set to Enlarged"
        }
    }
}
