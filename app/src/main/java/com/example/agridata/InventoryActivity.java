// src/main/java/com/example/agridata/InventoryActivity.java
package com.example.agridata;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ArrayList<InventoryItem> inventoryItems;
    private ArrayAdapter<InventoryItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        db = new DatabaseHelper(this);
        EditText editName = findViewById(R.id.edit_name);
        EditText editQuantity = findViewById(R.id.edit_quantity);
        Spinner spinnerUnit = findViewById(R.id.spinner_unit);
        Button buttonAdd = findViewById(R.id.button_add);
        ListView listInventory = findViewById(R.id.list_inventory);
        Button buttonClear = findViewById(R.id.button_clear);

        inventoryItems = db.getAllInventoryItems();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventoryItems);
        listInventory.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String quantityStr = editQuantity.getText().toString();
            String unit = spinnerUnit.getSelectedItem().toString();

            if (name.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(InventoryActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            InventoryItem item = new InventoryItem(name, quantity, unit);
            db.addInventoryItem(item);
            inventoryItems.add(item);
            adapter.notifyDataSetChanged();

            editName.setText("");
            editQuantity.setText("");
            spinnerUnit.setSelection(0);
        });

        buttonClear.setOnClickListener(v -> {
            new AlertDialog.Builder(InventoryActivity.this)
                    .setTitle("Clear All Items")
                    .setMessage("Are you sure you want to clear all inventory items?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteAllInventoryItems();
                        inventoryItems.clear();
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        listInventory.setOnItemLongClickListener((parent, view, position, id) -> {
            InventoryItem item = inventoryItems.get(position);
            db.deleteInventoryItem(item.getName());
            inventoryItems.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
