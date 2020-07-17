package org.maktab.calculator.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.maktab.calculator.R;
import org.maktab.calculator.model.Calculator;

public class CalculatorActivity extends AppCompatActivity {

    public static final String TAG = "alitag";
    TextView mTextViewResult;
    Button mbutton0;
    Button mbutton1;
    Button mbutton2;
    Button mbutton3;
    Button mbutton4;
    Button mbutton5;
    Button mbutton6;
    Button mbutton7;
    Button mbutton8;
    Button mbutton9;
    Button mbuttonAdd;
    Button mbuttonSub;
    Button mbuttonMul;
    Button mbuttonDiv;
    Button mbuttonPoint;
    Button mbuttonEqual;
    Button mbuttonDelete;

    private String mCalculations = "";
    private boolean isOpratorClicked = false;
    private boolean isNumberClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViews();
        setOnClickListener();
    }

    public void findViews() {
        mTextViewResult = findViewById(R.id.textViewResult);
        mbutton0 = findViewById(R.id.button0);
        mbutton1 = findViewById(R.id.button1);
        mbutton2 = findViewById(R.id.button2);
        mbutton3 = findViewById(R.id.button3);
        mbutton4 = findViewById(R.id.button4);
        mbutton5 = findViewById(R.id.button5);
        mbutton6 = findViewById(R.id.button6);
        mbutton7 = findViewById(R.id.button7);
        mbutton8 = findViewById(R.id.button8);
        mbutton9 = findViewById(R.id.button9);
        mbuttonAdd = findViewById(R.id.buttonAdd);
        mbuttonSub = findViewById(R.id.buttonSub);
        mbuttonMul = findViewById(R.id.buttonMul);
        mbuttonDiv = findViewById(R.id.buttonDiv);
        mbuttonPoint = findViewById(R.id.buttonPoint);
        mbuttonEqual = findViewById(R.id.buttonEqual);
        mbuttonDelete = findViewById(R.id.buttonDelete);
    }

    public void setOnClickListener() {
        setOnNumberClickListener('0', mbutton0);
        setOnNumberClickListener('1', mbutton1);
        setOnNumberClickListener('2', mbutton2);
        setOnNumberClickListener('3', mbutton3);
        setOnNumberClickListener('4', mbutton4);
        setOnNumberClickListener('5', mbutton5);
        setOnNumberClickListener('6', mbutton6);
        setOnNumberClickListener('7', mbutton7);
        setOnNumberClickListener('8', mbutton8);
        setOnNumberClickListener('9', mbutton9);
        setOnNumberClickListener('.', mbuttonPoint);

        setOnOperatorClickListener('+', mbuttonAdd);
        setOnOperatorClickListener('-', mbuttonSub);
        setOnOperatorClickListener('*', mbuttonMul);
        setOnOperatorClickListener('/', mbuttonDiv);

        mbuttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCalculations.length() != 0) {
                    //Log.d(TAG, "" + mCalculations.length());
                    char lastCharcter = mCalculations.charAt(mCalculations.length() - 1);
                    if (lastCharcter == ' ') {
                        mCalculations = mCalculations.substring(0, mCalculations.length() - 1);
                        lastCharcter = mCalculations.charAt(mCalculations.length() - 1);
                        isNumberClicked = !isNumberClicked;
                        isOpratorClicked = !isOpratorClicked;
                    }
                    mCalculations = mCalculations.substring(0, mCalculations.length() - 1);
                    mTextViewResult.setText(mCalculations);
                }
            }
        });

        mbuttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                claculate(mCalculations);
            }
        });

    }


    public void setOnNumberClickListener(char number, Button button) {
        final char numberFinal = number;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNumberClicked = true;
                if (isOpratorClicked) {
                    mCalculations += " ";
                    isOpratorClicked = false;
                }
                mCalculations = mCalculations + numberFinal;
                mTextViewResult.setText(mCalculations);
            }
        });
    }

    public void setOnOperatorClickListener(char operator, Button button) {
        final char operatorFinal = operator;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNumberClicked) {
                    isOpratorClicked = true;
                    mCalculations += " ";
                    isNumberClicked = false;
                    mCalculations = mCalculations + operatorFinal;
                } else {
                    mCalculations = mCalculations.substring(0, mCalculations.length() - 1) + operatorFinal;
                }
                mTextViewResult.setText(mCalculations);
            }
        });
    }

    private void claculate(String calculations) {
        Calculator calculator = new Calculator();
        int firstIndex = 0;
        int secondIndex = 0;
        char operator ='+';
        while (true) {
            secondIndex = mCalculations.indexOf(' ',firstIndex+1);
            //Log.d(TAG,"secondIndex "+secondIndex);
            if(secondIndex==-1){
                secondIndex = mCalculations.length();
            }
            String numberStr = mCalculations.substring(firstIndex,secondIndex);
            double number = Double.parseDouble(numberStr);
            calculator.calculate(number,operator);
            Log.d(TAG,""+calculator.getResult());
            firstIndex = mCalculations.indexOf(' ',secondIndex+1);
            //Log.d(TAG,"firstIndex "+firstIndex);
            if(firstIndex==-1){
                break;
            }
            firstIndex++;
            operator = mCalculations.charAt(secondIndex+1);
            //Log.d(TAG,"char "+operator);
        }
        //Log.d(TAG,"result "+calculator.getResult());
        mCalculations = String.valueOf(calculator.getResult());
        mTextViewResult.setText(mCalculations);
    }

}