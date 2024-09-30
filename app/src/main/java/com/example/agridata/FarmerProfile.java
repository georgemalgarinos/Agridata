// src/main/java/com/example/agridata/FarmerProfile.java
package com.example.agridata; // πακέτο com.example.agridata

public class FarmerProfile { // δημόσια κλάση FarmerProfile
    private String name; // ιδιωτικό πεδίο name
    private int age; // ιδιωτικό πεδίο age
    private String location; // ιδιωτικό πεδίο location
    private String farmName; // ιδιωτικό πεδίο farmName
    private int farmSize; // ιδιωτικό πεδίο farmSize
    private String crops; // ιδιωτικό πεδίο crops
    private String livestock; // ιδιωτικό πεδίο livestock

    public FarmerProfile(String name, int age, String location, String farmName, int farmSize, String crops, String livestock) { // δημόσιος κατασκευαστής FarmerProfile που δέχεται name, age, location, farmName, farmSize, crops, livestock
        this.name = name; // αρχικοποίηση πεδίου name
        this.age = age; // αρχικοποίηση πεδίου age
        this.location = location; // αρχικοποίηση πεδίου location
        this.farmName = farmName; // αρχικοποίηση πεδίου farmName
        this.farmSize = farmSize; // αρχικοποίηση πεδίου farmSize
        this.crops = crops; // αρχικοποίηση πεδίου crops
        this.livestock = livestock; // αρχικοποίηση πεδίου livestock
    }

    // Μέθοδοι λήψης και ρύθμισης για κάθε πεδίο
    public String getName() { return name; } // μέθοδος λήψης name
    public int getAge() { return age; } // μέθοδος λήψης age
    public String getLocation() { return location; } // μέθοδος λήψης location
    public String getFarmName() { return farmName; } // μέθοδος λήψης farmName
    public int getFarmSize() { return farmSize; } // μέθοδος λήψης farmSize
    public String getCrops() { return crops; } // μέθοδος λήψης crops
    public String getLivestock() { return livestock; } // μέθοδος λήψης livestock
}
