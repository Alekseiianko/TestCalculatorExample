package com.example.testcalculatorexample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.testcalculatorexample.databinding.ActivityMainBindingImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBindingImpl binding;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final String REGEX = "(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)";

    private char CURRENT_ACTION;

    private long then = 0;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bindingMethod();

    }

    private void bindingMethod() {
        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.openBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "(");
            }
        });

        binding.closeBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + ")");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.editText.setText(decimalFormat.format(valueOne) + "+");
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.editText.setText(decimalFormat.format(valueOne) + "-");
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.editText.setText(decimalFormat.format(valueOne) + "*");
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.editText.setText(decimalFormat.format(valueOne) + "/");
            }
        });

        binding.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
                binding.editText.setText("");
                binding.infoTextView.setText("");
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                String result = decimalFormat.format(valueOne);
                binding.infoTextView.setText(result);
                binding.editText.setText("");
                double resultDouble = parseDouble(result);
                valueOne = resultDouble;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonEqual.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = System.currentTimeMillis();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if ((System.currentTimeMillis() - then) > 4000) {
                        valueOne = Double.NaN;
                        valueTwo = Double.NaN;
                        binding.editText.setText("");
                        binding.infoTextView.setText("");
                        Toast.makeText(getApplicationContext(), "Enter 123 for change activity in 5 seconds"
                                , Toast.LENGTH_LONG).show();
                        changeActivity();
                        return true;
                    }
                }
                return false;
            }
        });


        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }
            }
        });
    }

    private void computeCalculation() {
        String expression = binding.editText.getText().toString();
        String[] split = expression.split(REGEX);
        List<String> splitList = new ArrayList<>();
        splitList = Arrays.asList(split);
        StringBuilder sb = new StringBuilder();
        if (!Double.isNaN(valueOne)) {
            for (int i = 0; i < splitList.size(); i++) {
                if (splitList.get(i).equals("*") || splitList.get(i).equals("/") ||
                        splitList.get(i).equals("-") || splitList.get(i).equals("+")) {
                    for (int j = i + 1; j < splitList.size(); j++) {
                        sb.append(splitList.get(j));
                    }
                    break;
                }
            }

            valueTwo = parseDouble(sb.toString());
            calculate();
        } else {
            try {
                if (!splitList.isEmpty()) {
                    for (String str : splitList) {
                        sb.append(str);
                    }
                    String secondValue = sb.toString();
                    valueOne = parseDouble(secondValue);
                } else {
                    valueOne = parseDouble(binding.editText.getText().toString());
                }
            } catch (Exception e) {
            }
        }
    }

    private void calculate() {
        if (CURRENT_ACTION == ADDITION)
            valueOne = this.valueOne + valueTwo;
        else if (CURRENT_ACTION == SUBTRACTION)
            valueOne = this.valueOne - valueTwo;
        else if (CURRENT_ACTION == MULTIPLICATION)
            valueOne = this.valueOne * valueTwo;
        else if (CURRENT_ACTION == DIVISION)
            valueOne = this.valueOne / valueTwo;
    }

    private double parseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;
            }
        } else return valueOne;
    }

    private void changeActivity() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);

                    String text = binding.editText.getText().toString();

                    if (text.equals("123")) {
                        Intent intent = new Intent(MainActivity.this, SecretActivity.class);
                        startActivity(intent);
                    } else {
                        valueOne = Double.NaN;
                        binding.editText.setText("");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
