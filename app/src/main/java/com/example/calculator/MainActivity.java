package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

import javax.xml.xpath.XPathExpressionException;

public class MainActivity extends AppCompatActivity {

    private EditText display_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display_text = findViewById(R.id.display_text);
        display_text.setShowSoftInputOnFocus(false);

        display_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.show).equals(display_text.getText().toString())) {
                    display_text.setText(" ");
                }
            }
        });
    }

    private void updateText(String stringToAdd) {
        // Getting the expression at the Edit text.
        String oldString = display_text.getText().toString();
        //Getting the cursor position to add the newString after that.
        int cursorPosition = display_text.getSelectionStart();
        // Dividing the strings in two parts.
        String left = oldString.substring(0, cursorPosition);
        String right = oldString.substring(cursorPosition);

        //Check if the text view contains the default string.
        if(getString(R.string.show).equals(display_text.getText().toString())) {
            display_text.setText(stringToAdd);
        } else {
            display_text.setText(String.format("%s%s%s", left, stringToAdd, right));
        }
        display_text.setSelection(cursorPosition + stringToAdd.length());

    }


//    Creating the OnClick Listener of all the components.
    public void bEqual(View view){
        String expression = display_text.getText().toString();
        expression = expression.replaceAll("÷", "/");
        expression = expression.replaceAll("x", "*");
        expression = expression.replaceAll("Sin\\(", "sin((180*22)/7");
        expression = expression.replaceAll("Cos\\(", "cos(toRadians(");
        expression = expression.replaceAll("Tan\\(", "tan(toRadians(");

//        expression = expression.replaceAll("Log(", "log(");

        Expression exp = new Expression(expression);
        String result = String.valueOf(exp.calculate());

        display_text.setText(result);
        display_text.setSelection(result.length());
    }
    public void bBack(View view) {
        int cursorPosition = display_text.getSelectionStart();
        int length = display_text.getText().length();

        if(cursorPosition != 0 && length != 0) {
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) display_text.getText();
            spannableStringBuilder.replace(cursorPosition-1, cursorPosition, "");
            display_text.setText(spannableStringBuilder);
            display_text.setText(cursorPosition-1);

        }
    }
    public void bClear(View view) {
        display_text.setText("");
    }
    public void bZero(View view) {
        updateText("0");
    }
    public void bOne(View view) {
        updateText("1");
    }
    public void bTwo(View view) {
        updateText("2");
    }
    public void bThree(View view) {
        updateText("3");
    }
    public void bFour(View view) {
        updateText("4");
    }
    public void bFive(View view) {
        updateText("5");
    }
    public void bSix(View view) {
        updateText("6");
    }
    public void bSeven(View view) {
        updateText("7");
    }
    public void bEight(View view) {
        updateText("8");
    }
    public void bNine(View view) {
        updateText("9");
    }
    public void bDivide(View view) {
        updateText("÷");
    }
    public void bProduct(View view) {
        updateText("x");
    }
    public void bSubtract(View view) {
        updateText("-");
    }
    public void bAdd(View view) {
        updateText("+");
    }
    public void bDot(View view) {
        updateText(".");
    }
    public void bSin(View view) {
        updateText("Sin(");
    }
    public void bCos(View view) {
        updateText("Cos(");
    }
    public void bTan(View view){
        updateText("Tan(");
    }
    public void bLog(View view) {
        updateText("Log(");
    }
    public void bPow(View view) {
        updateText("^");
    }
    public void bBrackets(View view) {

        int cursorPosition = display_text.getSelectionStart();
        int openBracket = 0;
        int closeBracket = 0;
        int length = display_text.getText().length();

        for(int i=0; i<cursorPosition; i++) {
            if(display_text.getText().toString().substring(i, i+1).equals("(")){
                openBracket++;
            }
            if(display_text.getText().toString().substring(i, i+1).equals(")")){
                closeBracket++;
            }
        }

        if((openBracket == closeBracket) || display_text.getText().toString().substring(length-1, length).equals("(")) {
            updateText("(");
            display_text.setSelection(cursorPosition+1);
        }
        if((openBracket > closeBracket) && !display_text.getText().toString().substring(length-1, length).equals("(")) {
            updateText(")");
            display_text.setSelection(cursorPosition+1);
        }

    }


}