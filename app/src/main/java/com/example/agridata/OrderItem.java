// src/main/java/com/example/agridata/OrderItem.java
package com.example.agridata; // πακέτο com.example.agridata

public class OrderItem { // δημόσια κλάση OrderItem
    private String name; // ιδιωτικό πεδίο name
    private int quantity; // ιδιωτικό πεδίο quantity
    private String unit; // ιδιωτικό πεδίο unit
    private boolean isCompleted; // ιδιωτικό πεδίο isCompleted

    public OrderItem(String name, int quantity, String unit, boolean isCompleted) { // δημόσιος κατασκευαστής OrderItem που δέχεται name, quantity, unit, isCompleted
        this.name = name; // αρχικοποίηση πεδίου name
        this.quantity = quantity; // αρχικοποίηση πεδίου quantity
        this.unit = unit; // αρχικοποίηση πεδίου unit
        this.isCompleted = isCompleted; // αρχικοποίηση πεδίου isCompleted
    }

    public String getName() { return name; } // μέθοδος λήψης name
    public int getQuantity() { return quantity; } // μέθοδος λήψης quantity
    public String getUnit() { return unit; } // μέθοδος λήψης unit
    public boolean isCompleted() { return isCompleted; } // μέθοδος λήψης isCompleted

    public void setCompleted(boolean completed) { // μέθοδος ρύθμισης isCompleted
        isCompleted = completed; // ρύθμιση της κατάστασης ολοκλήρωσης
    }

    @Override
    public String toString() { // υπερκαλυμμένη μέθοδος toString
        return name + " " + quantity + " " + unit; // επιστροφή συμβολοσειράς που περιέχει name, quantity και unit
    }
}
