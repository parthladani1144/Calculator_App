package com.example.parthladani.calculator_assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, plus, minus, multiply, divide, del, dot, eq;

    EditText numList;

    TextView result;

    float value1 = Float.NaN, value2;

    private static final ArrayList<Character> DIVIDERS = new ArrayList<Character>
            (Arrays.asList('*', '/', '-', '+'));
    private static final int RIGHT_DIRECTION = 1;
    private static final int LEFT_DIRECTION = -1;

    char current_action;

    boolean vPlus, vMinus, vDivide, vMultiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num0 = (Button) findViewById(R.id.num0);
        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        del = (Button) findViewById(R.id.del);
        dot = (Button) findViewById(R.id.dot);
        eq = (Button) findViewById(R.id.eq);

        result = (TextView) findViewById(R.id.result);

        numList = (EditText) findViewById(R.id.numList);

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "0");
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "1");
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "2");
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "3");
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "4");
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "5");
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "6");
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "7");
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "8");
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ "9");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ ".");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText()+ " + ");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText() + " - ");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText() + " * ");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numList.setText(numList.getText() + " / ");
            }
        });

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator();
                result.setText(result.getText().toString() + value1);
                value1 = Float.NaN;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numList.getText().length() > 0) {
                    CharSequence text = numList.getText();
                    numList.setText(text.subSequence(0, text.length() - 1));
                }
                else {
                    value1 = Float.NaN;
                    value2 = Float.NaN;
                    numList.setText("");
                    result.setText("");
                }
            }
        });

        del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                numList.setText("");
                result.setText("");
                return true;
            }
        });

    }

    private void calculator() {
        if(Float.isNaN(value1)) {
            try {
                value1=Float.parseFloat(calc(numList.getText().toString()));
                numList.setText(null);
            }
            catch(Exception e)
            { }
        }
        else {
            try {
                value1 = Float.valueOf(numList.getText().toString());

            }
            catch (Exception e){}
        }
    }

    public static String calc(String expression) {
        int pos = 0;
        System.out.println("Solving expression: "+expression);
        if (-1 != (pos = expression.indexOf("("))) {

            String subexp = extractExpressionFromBraces(expression,pos);
            expression = expression.replace("("+subexp+")", calc(subexp));

            return calc(expression);

        }

        else if (-1 != (pos = expression.indexOf("exp"))) {
            pos += 2;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("exp" + number,
                    Double.toString(Math.exp(Double.parseDouble(number))));

            return calc(expression);
        }

        else if (expression.indexOf("*") > 0 | expression.indexOf("/") > 0) {
            int multPos = expression.indexOf("*");
            int divPos = expression.indexOf("/");

            pos = Math.min(multPos, divPos);
            if (multPos < 0) pos = divPos; else if (divPos < 0) pos = multPos;

            char divider = expression.charAt(pos);

            String leftNum = extractNumber(expression, pos, LEFT_DIRECTION);
            String rightNum = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace(leftNum + divider + rightNum,
                    calcShortExpr(leftNum, rightNum, divider));

            return calc(expression);
        }

        else if (expression.indexOf("+") > 0 | expression.indexOf("-") > 0) {
            int summPos = expression.indexOf("+");
            int minusPos = expression.indexOf("-");

            pos = Math.min(summPos, minusPos);

            if (summPos < 0) pos = minusPos; else if (minusPos < 0) pos = summPos;

            char divider = expression.charAt(pos);

            String leftNum = extractNumber(expression, pos, LEFT_DIRECTION);
            String rightNum = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace(leftNum + divider + rightNum,
                    calcShortExpr(leftNum, rightNum, divider));

            return calc(expression);
        }

        else return expression;
    }

    public static String extractExpressionFromBraces(String expression, int pos) {
        int braceDepth = 1;
        String subexp="";

        for (int i = pos+1; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    braceDepth++;
                    subexp += "(";
                    break;
                case ')':
                    braceDepth--;
                    if (braceDepth != 0) subexp += ")";
                    break;
                default:
                    if (braceDepth > 0) subexp += expression.charAt(i);

            }
            if (braceDepth == 0 && !subexp.equals("")) return subexp;
        }
        return "Failure!";
    }

    public static String extractNumber(String expression, int pos, int direction) {
        String resultNumber = "";
        int currPos = pos + direction;


        if (expression.charAt(currPos) == '-') {
            resultNumber+=expression.charAt(currPos);
            currPos+=direction;
        }

        for (; currPos >= 0 &&
                currPos < expression.length() &&
                !DIVIDERS.contains(expression.charAt(currPos));
             currPos += direction) {
            resultNumber += expression.charAt(currPos);
        }

        if (direction==LEFT_DIRECTION) resultNumber = new
                StringBuilder(resultNumber).reverse().toString();

        return resultNumber;
    }

    public static String calcShortExpr(String leftNum, String rightNum, char divider) {
        switch (divider) {
            case '*':
                return Float.toString(Float.parseFloat(leftNum) *
                        Float.parseFloat(rightNum));

            case '/':
                return Float.toString(Float.parseFloat(leftNum) /
                        Float.parseFloat(rightNum));

            case '+':
                return Float.toString(Float.parseFloat(leftNum) +
                        Float.parseFloat(rightNum));

            case '-':
                return Float.toString(Float.parseFloat(leftNum) -
                        Float.parseFloat(rightNum));

            default:
                return "0";
        }

    }

}
