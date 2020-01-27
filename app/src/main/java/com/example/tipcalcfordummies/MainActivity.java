package com.example.tipcalcfordummies;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Private variables for the activity
    private Button butCalcTip ;
    private EditText editBilledAmount;
    private EditText editTipPercent;
    private EditText editBillTotal;
    private EditText editAmountDue;
    private EditText editTipAmount;
    private int partyCount;
    private double amountBilled;
    private double tipPercentage;
    Toast toastDecError, toastIntError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate UI elements to their respective handlers.
        butCalcTip = findViewById(R.id.butCalcTip);
        editBilledAmount = findViewById(R.id.editBilledAmount);
        editTipPercent = findViewById(R.id.editTipPercent);
        editBillTotal = findViewById(R.id.editBillTotal);
        editAmountDue = findViewById(R.id.editAmountDue);
        editTipAmount = findViewById(R.id.editTipAmount);
        partyCount = 1;
    }

    //parses and returns double from EditText
    private double getDouble(EditText editText){
        return Double.parseDouble(editText.getText().toString().trim());
    }

    //Detects radio button activity and updates party count variable accordingly
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.radioButton:
                if(checked)
                    partyCount = 1;
                break;
            case R.id.radioButton2:
                if(checked)
                    partyCount = 2;
                break;
            case R.id.radioButton3:
                if(checked)
                    partyCount = 3;
                break;
            case R.id.radioButton4:
                if(checked)
                    partyCount = 4;
                break;
            case R.id.radioButton5:
                if(checked)
                    partyCount = 5;
                break;
            case R.id.radioButton6:
                if(checked)
                    partyCount = 6;
                break;
        }
    }

    //method to format decimal numbers to two places after the decimal
    double roundTwoDecimal(double d){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    //onclick handler for the magical tip calculation
    //checks for empty inputs and throws toast messages if they're empty.
    public void butCalcTip(View view){
        if(!TextUtils.isEmpty(editBilledAmount.getText())){
            amountBilled = getDouble(editBilledAmount);
        }else{
            toastDecError = Toast.makeText(this,"Please enter a valid decimal number.", Toast.LENGTH_LONG);
            toastDecError.show();
        }

        if(!editTipPercent.getText().toString().isEmpty()){
            tipPercentage = getDouble(editTipPercent);
        }else{
            toastIntError = Toast.makeText(this, "Please enter a valid integer.", Toast.LENGTH_LONG);
            toastIntError.show();
        }

        double tipTotal = amountBilled * tipPercentage/100;
        double billTotal = amountBilled + tipTotal;
        double perPerson = billTotal / partyCount;
        tipTotal = roundTwoDecimal(tipTotal);
        billTotal = roundTwoDecimal(billTotal);
        perPerson = roundTwoDecimal(perPerson);
        editTipAmount.setText(tipTotal+"");
        editBillTotal.setText(billTotal+"");
        editAmountDue.setText(perPerson+"");
    }

}
