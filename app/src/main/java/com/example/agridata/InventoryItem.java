// src/main/java/com/example/agridata/InventoryItem.java
package com.example.agridata; // πακέτο com.example.agridata

public class InventoryItem { // δημόσια κλάση InventoryItem
    private String name; // ιδιωτικό πεδίο name
    private int quantity; // ιδιωτικό πεδίο quantity
    private String unit; // ιδιωτικό πεδίο unit

    public InventoryItem(String name, int quantity, String unit) { // δημόσιος κατασκευαστής InventoryItem που δέχεται name, quantity, unit
        this.name = name; // αρχικοποίηση πεδίου name
        this.quantity = quantity; // αρχικοποίηση πεδίου quantity
        this.unit = unit; // αρχικοποίηση πεδίου unit
    }

    public String getName() { return name; } // μέθοδος λήψης name
    public int getQuantity() { return quantity; } // μέθοδος λήψης quantity
    public String getUnit() { return unit; } // μέθοδος λήψης unit

    @Override
    public String toString() { // υπερκαλυμμένη μέθοδος toString
        return name + " " + quantity + " " + unit; // επιστροφή συμβολοσειράς που περιέχει name, quantity και unit
    }
}
