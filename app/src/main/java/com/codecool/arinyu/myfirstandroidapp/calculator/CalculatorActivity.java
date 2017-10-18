package com.codecool.arinyu.myfirstandroidapp.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.R;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mRentUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        mRentUserInput = (EditText) findViewById(R.id.editInput);
        final Button btnCalculate = (Button) findViewById(R.id.btnCalculate);

        mRentUserInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnCalculate.performClick();
                    return true;
                }
                return false;
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String costs = mRentUserInput.getText().toString();
                if (costs.isEmpty()) {
                    Toast.makeText(CalculatorActivity.this, "Please fill in the required field!", Toast.LENGTH_LONG).show();
                } else {
                    Integer bill = Integer.parseInt(costs);
                    Calculator calculator = new Calculator();
                    String result = calculator.calculateResults(bill);
                    Toast.makeText(CalculatorActivity.this, result, Toast.LENGTH_LONG).show();
                }
            }
        });
        final Button btnGoBack = (Button) findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}