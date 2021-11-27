package com.example.baseconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

    Spinner inputbase;
    Spinner outputbase;
    EditText input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inputbase = findViewById(R.id.inputbase);
        this.outputbase = findViewById(R.id.outputbase);
        this.input = findViewById(R.id.input);
        this.output = findViewById(R.id.output);

        this.inputbase.setOnItemSelectedListener(this);
        this.outputbase.setOnItemSelectedListener(this);
        this.input.setOnEditorActionListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.calculate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.calculate();
        return false;
    }

    public void calculate() {
        int inputbase = this.getBase(this.inputbase.getSelectedItem().toString());
        int outputbase = this.getBase(this.outputbase.getSelectedItem().toString());
        String inputnum = this.input.getText().toString();
        if (this.isNumeric(inputnum))
        {
            String result = this.baseConvert(inputnum, inputbase, outputbase);
            this.output.setText(result);
        }

    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public int getBase(String str) {
        int base = 10;
        switch (str) {
            case "HEX": base = 16;break;
            case "OCT": base = 8;break;
            case "BIN": base = 2;break;
        }
        return base;
    }

    public String baseConvert(String number, int inputbase, int outputbase)
    {
        try {
            return Integer.toString(
                    Integer.parseInt(number, inputbase), outputbase);
        }
        catch (NumberFormatException e) {
            return "-";
        }
    }
}