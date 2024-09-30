// src/main/java/com/example/agridata/OrdersActivity.java
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

public class OrdersActivity extends AppCompatActivity {

    private OrderDatabaseHelper db;
    private ArrayList<OrderItem> pendingOrders;
    private ArrayList<OrderItem> completedOrders;
    private ArrayAdapter<OrderItem> pendingAdapter;
    private ArrayAdapter<OrderItem> completedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        db = new OrderDatabaseHelper(this);
        EditText editOrderName = findViewById(R.id.edit_order_name);
        EditText editOrderQuantity = findViewById(R.id.edit_order_quantity);
        Spinner spinnerOrderUnit = findViewById(R.id.spinner_order_unit);
        Button buttonAddOrder = findViewById(R.id.button_add_order);
        ListView listPendingOrders = findViewById(R.id.list_pending_orders);
        ListView listCompletedOrders = findViewById(R.id.list_completed_orders);
        Button buttonClearOrders = findViewById(R.id.button_clear_orders);

        pendingOrders = db.getOrderItems(false);
        completedOrders = db.getOrderItems(true);

        pendingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingOrders);
        listPendingOrders.setAdapter(pendingAdapter);

        completedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, completedOrders);
        listCompletedOrders.setAdapter(completedAdapter);

        buttonAddOrder.setOnClickListener(v -> {
            String name = editOrderName.getText().toString();
            String quantityStr = editOrderQuantity.getText().toString();
            String unit = spinnerOrderUnit.getSelectedItem().toString();

            if (name.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(OrdersActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            OrderItem item = new OrderItem(name, quantity, unit, false);
            db.addOrderItem(item);
            pendingOrders.add(item);
            pendingAdapter.notifyDataSetChanged();

            editOrderName.setText("");
            editOrderQuantity.setText("");
            spinnerOrderUnit.setSelection(0);
        });

        buttonClearOrders.setOnClickListener(v -> {
            new AlertDialog.Builder(OrdersActivity.this)
                    .setTitle("Clear All Orders")
                    .setMessage("Are you sure you want to clear all orders?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteAllOrderItems();
                        pendingOrders.clear();
                        completedOrders.clear();
                        pendingAdapter.notifyDataSetChanged();
                        completedAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        listPendingOrders.setOnItemClickListener((parent, view, position, id) -> {
            OrderItem item = pendingOrders.get(position);
            item.setCompleted(true);
            db.updateOrderItem(item);
            pendingOrders.remove(position);
            pendingAdapter.notifyDataSetChanged();
            completedOrders.add(item);
            completedAdapter.notifyDataSetChanged();
        });

        listCompletedOrders.setOnItemLongClickListener((parent, view, position, id) -> {
            OrderItem item = completedOrders.get(position);
            completedOrders.remove(position);
            db.deleteOrderItem(item.getName());
            completedAdapter.notifyDataSetChanged();
            return true;
        });
    }
}
