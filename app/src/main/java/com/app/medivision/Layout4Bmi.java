package com.app.medivision;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tinut on 29-12-2016.
 */
public class Layout4Bmi extends AppCompatActivity {
    LinearLayout english,malayalam;
    RelativeLayout mainlayouttt,Chlang;
    Button Bnext,Bback;
    EditText weight,height;
    TextView cLang,Tweight,THeight;
    public static double var_BmiValue=0;
    boolean showingFirst=true;
    public  static String lan="E",w,h;
    static double valuew;
    static double valueh;
    static double total_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout4_bmi);
        Bnext = (Button) findViewById(R.id.button_nextBmi);
        Bback = (Button) findViewById(R.id.button__backBmi);
        weight = (EditText) findViewById(R.id.ETbmiWeight);
        height = (EditText) findViewById(R.id.ETbmiHeight);
        cLang = (TextView) findViewById(R.id.changelangBmi);
        english = (LinearLayout) findViewById(R.id.englishL1);
        Tweight=(TextView)findViewById(R.id.TVbmiWeight);
        THeight=(TextView)findViewById(R.id.TVbmiHeight);

        malayalam = (LinearLayout) findViewById(R.id.malayalamL1);
        mainlayouttt = (RelativeLayout) findViewById(R.id.changelang_layout);

        Chlang = (RelativeLayout) findViewById(R.id.RLChangeLang);


        Chlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst) {
                    mainlayouttt.setVisibility(View.VISIBLE);
                    showingFirst = false;
                } else {
                    mainlayouttt.setVisibility(View.INVISIBLE);
                    showingFirst = true;
                }

            }
        });


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayouttt.setVisibility(View.INVISIBLE);
                Bback.setText("Back");
                Bnext.setText("Next");
                cLang.setText("English");
                lan="E";
                THeight.setText("Please enter your weight in kgs");
                Tweight.setText("Please enter your height in cms");

            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lan="M";
                THeight.setText("ദയവായി കിലോഗ്രാം ലെ നിങ്ങളുടെ ഭാരം നൽകുക");
                Tweight.setText("ദയവായി സെ.മീ നിങ്ങളുടെ ഉയരം നൽകുക");
                cLang.setText("മലയാളം");
                Bback.setText("തിരികെ");
                Bnext.setText("അടുത്തത്");
                mainlayouttt.setVisibility(View.INVISIBLE);

            }
        });




        Bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(Layout4Bmi.this, Diabetes.class);
                b.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(b);
            }
        });

        Bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w = weight.getText().toString();
                h = height.getText().toString();


                if (w.equals("") || h.equals("")) {
                    if (lan.equals("M")) {
                        Toast.makeText(Layout4Bmi.this, "\n" +
                                "എല്ലാ ഫീൽഡുകളും നൽകുക", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Layout4Bmi.this, "Enter  all fields", Toast.LENGTH_SHORT).show();
                } else {
                    valuew = Double.parseDouble(w);
                    valueh = Double.parseDouble(h);
                    valueh = (double)valueh/100;
                    total_height = (double)valueh*(double)valueh;
                    var_BmiValue = (double)(valuew/total_height);


                    Intent i = new Intent(Layout4Bmi.this, Layout7Family.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                    Log.e("moHAMED",String.valueOf(var_BmiValue));
                }

            }
        });
    }

        @Override
        public void onBackPressed()
        {
            Intent intent = new Intent(Layout4Bmi.this, Diabetes.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }




    }

