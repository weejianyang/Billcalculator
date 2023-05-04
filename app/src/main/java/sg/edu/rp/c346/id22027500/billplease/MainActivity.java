package sg.edu.rp.c346.id22027500.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView billView;
    EditText billInput;
    TextView paxView;
    EditText paxInput;
    ToggleButton tSvs;
    ToggleButton tGst;
    TextView disView;
    EditText disInput;
    RadioGroup paymentView;
    RadioButton cash;
    RadioButton payNow;
    RadioButton Masterc;
    TextView totalBill;
    TextView eachBill;
    Button splitBill;
    Button resetBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billView = findViewById(R.id.billView);
        billInput = findViewById(R.id.billInput);
        paxView = findViewById(R.id.paxView);
        paxInput = findViewById(R.id.paxInput);
        tSvs = findViewById(R.id.tbSvs);
        tGst = findViewById(R.id.tbGst);
        disView = findViewById(R.id.discountView);
        disInput = findViewById(R.id.discountInput);
        paymentView = findViewById(R.id.paymentMethods);
        cash = findViewById(R.id.cashMethod);
        payNow = findViewById(R.id.paynowMethod);
        Masterc = findViewById(R.id.mastercMethod);
        totalBill = findViewById(R.id.totalBillview);
        eachBill = findViewById(R.id.eachPayview);
        splitBill = findViewById(R.id.splitButton);
        resetBill = findViewById(R.id.resetButton);


        if (billInput.getText().toString().trim().length() != 0) {
            double billAmount = Double.parseDouble((billInput.getText().toString()));
            if (tSvs.isChecked() && tGst.isChecked()) {
                billAmount = Double.parseDouble(billInput.getText().toString()) * 1.17;
            } else if (!tSvs.isChecked() && tGst.isChecked()) {
                billAmount = Double.parseDouble(billInput.getText().toString()) * 1.7;
            } else if (tSvs.isChecked() && !tGst.isChecked()) {
                billAmount = Double.parseDouble(billInput.getText().toString()) * 1.10;
            } else {
                billAmount = Double.parseDouble(billInput.getText().toString());
            }

            int paxAmount = Integer.parseInt(paxInput.getText().toString());
            if (paxAmount >1) {
                eachBill.setText("Each pay: $" + String.format("%.2f", billAmount / paxAmount));
            }
            else {
                eachBill.setText("Each pay: $" + String.format("%.2f", billAmount));
            }

            if (disInput.getText().toString().trim().length() != 0) {
                billAmount = 1 - Double.parseDouble(disInput.getText().toString()) / 100;
                totalBill.setText("Total bill: $" + String.format("%.2f", billAmount));
            }

            paymentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double cashPay = paymentView.getCheckedRadioButtonId();
                    double paynowPay = paymentView.getCheckedRadioButtonId();
                    double mcPay = paymentView.getCheckedRadioButtonId();
                    String methods = "";
                    if (paynowPay == R.id.paymentView) {
                        methods= " via PayNow from 8498 1613";
                    }
                    else if (mcPay == R.id.paymentView) {
                        methods = " via MasterCard from 8498 1613";
                    }
                    else {
                        cashPay = Double.parseDouble(cash.getText().toString());
                    }

                }
            });

            splitBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   totalBill.setText(String.format("Total bill: $%.2f"));double billAmount;
                   eachBill.setText(String.format("Each pay: $%.2f"));double paxAmount;
                }
            });

            resetBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    billInput.setText("");
                    paxInput.setText("");
                    totalBill.setText("");
                    eachBill.setText("");
                    tSvs.setChecked(false);
                    tGst.setChecked(false);
                    cash.setChecked(true);
                }
            });














        }
    }
}