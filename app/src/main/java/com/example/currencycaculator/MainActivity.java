package com.example.currencycaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final float MAX_LONG= 9999999L;
    TextView type1, type2, note;
    int selected;
    TextView text1, text2;
    Spinner spinner1, spinner2;
    float resultBox;
    int money1, money2;
    boolean isfloat;
    float count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.num0).setOnClickListener(this);
        findViewById(R.id.num1).setOnClickListener(this);
        findViewById(R.id.num2).setOnClickListener(this);
        findViewById(R.id.num3).setOnClickListener(this);
        findViewById(R.id.num4).setOnClickListener(this);
        findViewById(R.id.num5).setOnClickListener(this);
        findViewById(R.id.num6).setOnClickListener(this);
        findViewById(R.id.num7).setOnClickListener(this);
        findViewById(R.id.num8).setOnClickListener(this);
        findViewById(R.id.num9).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.ce).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        text1 = (TextView) findViewById(R.id.number1);
        text2 = (TextView) findViewById(R.id.number2);
        type1 = (TextView) findViewById(R.id.type1);
        type2 = (TextView) findViewById(R.id.type2);
        note = (TextView) findViewById(R.id.note);

        text1.setText("");
        text2.setText("");
        type1.setText("GEL");
        type2.setText("GEL");
        note.setText("1 GEL = 1 GEL");
        resultBox = 0.0f;
        money1=0;
        money2=0;
        selected=0;
        isfloat=false;

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected!=1)
                {
                    resultBox=0;
                    count=0;
                }
                selected=1;
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected!=2)
                {
                    resultBox=0;
                    count=0;
                }
                selected=2;
            }
        });

        ArrayList<String> arrayCurrency = new ArrayList<String>();
        arrayCurrency.add("Georgia - Lari");
        arrayCurrency.add("Guinea - Franc");
        arrayCurrency.add("India - Rupe");
        arrayCurrency.add("Vietnam - Dong");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayCurrency);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                money1=position;
                currency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                money2=position;
                currency();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.num0:
                addDigit(0);
                break;
            case R.id.num1:
                addDigit(1);
                break;
            case R.id.num2:
                addDigit(2);
                break;
            case R.id.num3:
                addDigit(3);
                break;
            case R.id.num4:
                addDigit(4);
                break;
            case R.id.num5:
                addDigit(5);
                break;
            case R.id.num6:
                addDigit(6);
                break;
            case R.id.num7:
                addDigit(7);
                break;
            case R.id.num8:
                addDigit(8);
                break;
            case R.id.num9:
                addDigit(9);
                break;
            case R.id.del:
                delDigit();
                break;
            case R.id.point:
                count = 1;
                break;
            case R.id.ce:
                count = 0;
                resultBox=0;
                addDigit(0);
                break;
        }
    }

    public  void addDigit(int digit){
        if (resultBox*10+digit<=MAX_LONG)
        {
            if (count==0)
                resultBox= resultBox*10+digit;
            if (count!=0)
            {
                resultBox= (float) (resultBox+digit*Math.pow((int)10,(int)-count));
                count++;
            }

            if (selected==1)
                text1.setText(String.valueOf(resultBox));
            if (selected==2)  text2.setText(String.valueOf(resultBox));
            currency();
        }
    }
    public void delDigit() {

        if (selected==1) {
            String name = text1.getText().toString();
            if (name.charAt(name.length()-1)=='0')
            {
                count=0;
                if (name.length()==3) resultBox=0;
                else
                    resultBox= Float.parseFloat((String) name.subSequence(0, name.length()-3));
            }

            else resultBox= Float.parseFloat((String) name.subSequence(0, name.length()-1));
            text1.setText(String.valueOf(resultBox));
        }
        if (selected==2)
        {

            String name = text2.getText().toString();
            if (name.charAt(name.length()-1)=='0')
            {
                count=0;
                if (name.length()==3) resultBox=0;
                else
                    resultBox= Float.parseFloat((String) name.subSequence(0, name.length()-3));
            }

            else resultBox= Float.parseFloat((String) name.subSequence(0, name.length()-1));
            text2.setText(String.valueOf(resultBox));
        }
        currency();
    }

    public void display(float data){
        if (selected==1)
            text2.setText(String.valueOf(data));
        if (selected==2)  text1.setText(String.valueOf(data));
    }

    public void currency(){
        switch (money1){
            case 0:
                type1.setText("GEL");
                switch (money2){
                    case 0:
                        type2.setText("GEL");
                        note.setText("1 GEL = 1 GEL");
                        display(resultBox);
                        break;
                    case 1:
                        type2.setText("FG");
                        note.setText("1 GEL = 3004.2507 GNF");
                        display(resultBox* 3004.2507f);
                        break;
                    case 2:
                        type2.setText("INR");
                        note.setText("1 GEL = 24.3236 INR");
                        display(resultBox*24.23236f);
                        break;
                    case 3:
                        type2.setText("đ");
                        note.setText("1 GEL = 7501.3583 VND");
                        display(resultBox*7501.3583f);
                        break;

                }
                break;
            case 1:
                type1.setText("FG");
                switch (money2){
                    case 0:
                        type2.setText("GEL");
                        note.setText("1 GNF = 0.0003329 GEL");
                        display(resultBox*0.0003329f);
                        break;
                    case 1:
                        type2.setText("FG");
                        note.setText("1 GNF = 1 GNF");
                        display(resultBox);
                        break;
                    case 2:
                        type2.setText("INR");
                        note.setText("1 GNF = 0.008096 INR");
                        display(resultBox*0.008096f);
                        break;
                    case 3:
                        type2.setText("đ");
                        note.setText("1 GNF = 2.4969 VND");
                        display(resultBox*2.4969f);
                        break;

                }
                break;
            case 2:
                type1.setText("INR");
                switch (money2){
                    case 0:
                        type2.setText("GEL");
                        note.setText("1 INR = 0.04111 GEL");
                        display(resultBox*0.4111f);
                        break;
                    case 1:
                        type2.setText("FG");
                        note.setText("1 INR = 123.5119 GNF");
                        display(resultBox*123.5119f);
                        break;
                    case 2:
                        type2.setText("INR");
                        note.setText("1 INR = 1 INR");
                        display(resultBox);
                        break;
                    case 3:
                        type2.setText("đ");
                        note.setText("1 INR = 308.3988 VND");
                        display(resultBox*308.3988f);
                        break;

                }
                break;
            case 3:
                type1.setText("đ");
                switch (money2){
                    case 0:
                        type2.setText("GEL");
                        note.setText("1 VND = 0.0001333 GEL");
                        display(resultBox*0.0001333f);
                        break;
                    case 1:
                        type2.setText("FG");
                        note.setText("1 VND = 0.4005 GNF");
                        display(resultBox*0.4005f);
                        break;
                    case 2:
                        type2.setText("INR");
                        note.setText("1 VND = 0.003243 INR");
                        display(resultBox*0.003243f);
                        break;
                    case 3:
                        type2.setText("đ");
                        note.setText("1 VND =  1VND");
                        display(resultBox);
                        break;

                }
                break;

        }
    }
}
