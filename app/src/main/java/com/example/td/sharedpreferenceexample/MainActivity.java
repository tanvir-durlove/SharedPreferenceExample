package com.example.td.sharedpreferenceexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number_value;
    Button deposit, current_balance, withdraw;

    double num1, num2;

    public static final String Deposit = "depositkey";

    private String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deposit = (Button) findViewById(R.id.deposit);
        current_balance = (Button) findViewById(R.id.current_balance);
        withdraw = (Button) findViewById(R.id.withdraw);

        number_value = (EditText) findViewById(R.id.number_value);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        deposit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (number_value != null && number_value.length() > 0) {

                    try {
                        num2 = Double.parseDouble(number_value.getText().toString());
                        // Storing string
                        balance = String.valueOf(num1 + num2);
                        editor.putString(Deposit, balance);
                        editor.apply();
                        Toast.makeText(MainActivity.this, "You Successfully Deposit :" + num2, Toast.LENGTH_LONG).show();

                        num1 = Double.parseDouble(balance);
                    } catch (Exception e) {
                    }
                } else {
                    Toast.makeText
                            (MainActivity.this, "Please Input You're Amount", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                num2 = Double.parseDouble(number_value.getText().toString());
                if (num1 > num2) {
                    try {
                        // Storing string
                        balance = String.valueOf(num1 - num2);
                        editor.putString(Deposit, balance);
                        editor.apply();
                        Toast.makeText(MainActivity.this, "You Successfully Withdraw :" + num2, Toast.LENGTH_LONG).show();
                        num1 = Double.parseDouble(balance);
                    } catch (Exception e) {
                    }
                } else if (num2 > num1) {
                    Toast.makeText
                            (MainActivity.this, "Sorry You don't have sufficient balance for withdraw", Toast.LENGTH_SHORT)
                            .show();
                } else if (number_value.length() > 0) {
                    Toast.makeText
                            (MainActivity.this, "Please Input You're Amount", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        current_balance.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String blance = pref.getString(Deposit, null);
                Toast.makeText(MainActivity.this, "You're Current Balance is:" + blance, Toast.LENGTH_LONG).show();
            }
        });
    }
}
