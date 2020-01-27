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

public class MainActivity extends AppCompatActivity {

    private Button butCalcTip ;
    private EditText editBilledAmount;
    private EditText editTipPercent;
    private EditText editBillTotal;
    private EditText editAmountDue;
    private EditText editTipAmount;
    private int partyCount;
    private double amountBilled;
    private double tipPercentage;
    Toast toastDecError = Toast.makeText(this,"Please enter a valid decimal number.", Toast.LENGTH_LONG);
    Toast toastIntError = Toast.makeText(this, "Please enter a valid integer.", Toast.LENGTH_LONG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butCalcTip = findViewById(R.id.butCalcTip);
        editBilledAmount = findViewById(R.id.editBilledAmount);
        editTipPercent = findViewById(R.id.editTipPercent);
        editBillTotal = findViewById(R.id.editBillTotal);
        editAmountDue = findViewById(R.id.editAmountDue);
        editTipAmount = findViewById(R.id.editTipAmount);
        partyCount = 1;
    }

    private double getDouble(EditText editText){
        return Double.parseDouble(editText.getText().toString().trim());
    }

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

    public void butCalcTip(View view){
        if(!TextUtils.isEmpty(editBilledAmount.getText())){
            amountBilled = getDouble(editBilledAmount);
        }else{
            toastDecError.show();
        }

        if(!editTipPercent.getText().toString().isEmpty()){
            tipPercentage = getDouble(editTipPercent);
        }else{
            toastIntError.show();
        }

        double tipTotal = amountBilled * tipPercentage;
        double billTotal = amountBilled + tipTotal;
        double perPerson = billTotal / partyCount;
        editTipAmount.setText(tipTotal+"");
        editBillTotal.setText(billTotal+"");
        editAmountDue.setText(perPerson+"");
    }

}
