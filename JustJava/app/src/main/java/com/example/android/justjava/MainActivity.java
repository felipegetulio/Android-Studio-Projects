package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    Toast currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
    }

    private String createOrderSummary(String name, boolean hasWhippedCream, boolean hasChocolate){
        String summary = "Order Summary\n\nName: " + name + "\n";
        summary += "Add whipped cream? " + (hasWhippedCream? "Yes":"No") + "\n";
        summary += "Add chocolate? " + (hasChocolate? "Yes":"No") + "\n";
        summary += "Quantity: " + quantity + "\n";
        summary += "Total: R$" + calculatePrice(hasWhippedCream, hasChocolate) + "\n";
        summary += "Thank you!\n";
        return summary;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int perCupValue = CoffeePrices.CUP.getValue();

        if(addWhippedCream){
            perCupValue += CoffeePrices.WHIPPED_CREAM.getValue();
        }
        if(addChocolate){
            perCupValue += CoffeePrices.CHOCOLATE.getValue();
        }
        return quantity * perCupValue;
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = findViewById(R.id.checkbox_whipped_cream);
        CheckBox chocolate = findViewById(R.id.checkbox_chocolate);
        EditText name = findViewById(R.id.name_text_input);
        String message = createOrderSummary(name.getText().toString() , whippedCream.isChecked(), chocolate.isChecked());
        createEmailIntent("Coffee Order", message);
    }

    private void showToast(String message){
        currentToast.setText(message);
        currentToast.show();
    }

    public void createEmailIntent(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view){
        if(quantity == 100){
            showToast(getString(R.string.warning_more_than_100));
            return;
        }
        display(++quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            showToast(getString(R.string.warning_less_than_1));
            return;
        }
        display(--quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }
}

enum CoffeePrices{
    CUP(5), WHIPPED_CREAM(1), CHOCOLATE(2);
    private int value;

    CoffeePrices(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}