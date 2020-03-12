package com.example.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private EditText edtText;
    private Button calcBtn, resetBtn;
    private TextView binaryView, decimalView, hexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Conversion Calculator");

        spinner1 = findViewById(R.id.spinner1);
        edtText = findViewById(R.id.edtTxt);
        calcBtn = findViewById(R.id.calcBtn);
        binaryView = findViewById(R.id.binaryView);
        decimalView = findViewById(R.id.decimalView);
        hexView = findViewById(R.id.hexView);
        resetBtn = findViewById(R.id.resetBtn);

        List<String> listOfConversionTypes = new ArrayList<String>();
        listOfConversionTypes.add("Select options...");
        listOfConversionTypes.add("Binary");
        listOfConversionTypes.add("Decimal");
        listOfConversionTypes.add("Hexa Decimal");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfConversionTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setText("");
                binaryView.setText("");
                decimalView.setText("");
                hexView.setText("");
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, itemSelected + " ", Toast.LENGTH_SHORT).show();

                switch (position){
                    case 1:
                        binaryView.setVisibility(View.GONE);
                        decimalView.setVisibility(View.VISIBLE);
                        hexView.setVisibility(View.VISIBLE);
                        calcBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String val = edtText.getText().toString();
                                BinaryToDecimal(Integer.parseInt(val));
                                hexView.setText(Integer.toHexString(Integer.parseInt(val)));
                            }
                        });
                        break;
                    case 2:
                        binaryView.setVisibility(View.VISIBLE);
                        decimalView.setVisibility(View.GONE);
                        hexView.setVisibility(View.VISIBLE);

                        calcBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int decimal = Integer.parseInt(edtText.getText().toString());
                                binaryView.setText(Integer.toBinaryString(decimal));
                                hexView.setText(Integer.toHexString(decimal));
                            }
                        });
                        break;
                    case 3:
                        binaryView.setVisibility(View.VISIBLE);
                        decimalView.setVisibility(View.VISIBLE);
                        hexView.setVisibility(View.GONE);
                        calcBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String hexNum = edtText.getText().toString();
                                HexToDecimal(hexNum);
                            }
                        });
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void BinaryToDecimal(int binaryNumber){

        int decimal = 0;
        int p = 0;
        while(true){
            if(binaryNumber == 0){
                break;
            } else {
                int temp = binaryNumber%10;
                decimal += temp*Math.pow(2, p);
                binaryNumber = binaryNumber/10;
                p++;
            }
        }
        String dec = Integer.toString(decimal);
        decimalView.setText(dec);
    }

    public void HexToDecimal(String hexnum){
        String hstring = "0123456789ABCDEF";
        hexnum = hexnum.toUpperCase();
        int num = 0;
        for (int i = 0; i < hexnum.length(); i++)
        {
            char ch = hexnum.charAt(i);
            int n = hstring.indexOf(ch);
            num = 16*num + n;
        }
        binaryView.setText(Integer.toBinaryString(num));
        String dec = Integer.toString(num);
        decimalView.setText(dec);
    }
}
