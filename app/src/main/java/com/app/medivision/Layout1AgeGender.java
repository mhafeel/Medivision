package com.app.medivision;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tinut on 29-12-2016.
 */
public class Layout1AgeGender extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerAge;
    Button male,female,back,next;
    TextView cLang,age,gender;
    LinearLayout english,malayalam;
    RelativeLayout mainlayouttt,changeLang;
    public static String ageValue,ageValuespin,Gender,lang="E";
    boolean showingFirst=true;
int i=0,j=0,arr;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout1_agegender);
        male=(Button)findViewById(R.id.button_male);
        female=(Button)findViewById(R.id.button_female);
        back=(Button)findViewById(R.id.button__backl1);
        next=(Button)findViewById(R.id.button_nextl1);
        cLang=(TextView)findViewById(R.id.changelangAge);
       english=(LinearLayout)findViewById(R.id.englishL1age);
       malayalam=(LinearLayout)findViewById(R.id.malayalamL1age);
        mainlayouttt=(RelativeLayout)findViewById(R.id.changelang_layoutage);
        changeLang=(RelativeLayout)findViewById(R.id.RLChangeLang);
        age=(TextView)findViewById(R.id.TextView_age);
        gender=(TextView)findViewById(R.id.Textview_gender);
       // SharedPreferences preferences = getSharedPreferences("Lang", MODE_PRIVATE);

male.setOnClickListener(this);
        female.setOnClickListener(this);
        next.setOnClickListener(this);

        final int array2 = R.array.age;
        fun(array2);
        final int arrM=R.array.ageM;

        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingFirst) {
                    mainlayouttt.setVisibility(View.VISIBLE);
                    showingFirst=false;
                }
                else{
                    mainlayouttt.setVisibility(View.INVISIBLE);
                    showingFirst=true;
                }

            }
        });


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayouttt.setVisibility(View.INVISIBLE);
                fun(array2);
                lang = "E";
                cLang.setText("English");
                age.setText("Select your age");
                gender.setText("Select your gender ");
                male.setText("Male");
                female.setText("Female");
                back.setText("Back");
                next.setText("Next");
            }
        });

        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang = "M";
                fun(arrM);
                cLang.setText("മലയാളം");
                mainlayouttt.setVisibility(View.INVISIBLE);
                age.setText(" നിങ്ങളുടെ പ്രായം തിരഞ്ഞെടുക്കുക");
                gender.setText(" നിങ്ങളുടെ ലിംഗഭേദം തിരഞ്ഞെടുക്കുക");
                male.setText(" ആൺ");
                female.setText("പെണ്");
                back.setText("തിരികെ");
                next.setText("അടുത്തത്");

            }
        });







        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(Layout1AgeGender.this,ShareWith.class);
                b.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(b);
            }
        });

    }





    @Override
    public void onClick(View view) {
        switch (view.getId())
        {


            case R.id.button_male:


                Gender="Male";
                i++;
                male.setBackgroundResource(R.drawable.curveborderblack);
                female.setBackgroundResource(R.drawable.curveborder);
                male.setTextColor(Color.WHITE);
                female.setTextColor(Color.parseColor("#999999"));
                break;


            case R.id.button_female:

                Gender="Female";
                i++;
                male.setTextColor(Color.parseColor("#999999"));
                female.setBackgroundResource(R.drawable.curveborderblack);
                male.setBackgroundResource(R.drawable.curveborder);
                female.setTextColor(Color.WHITE);
                break;


            case R.id.button_nextl1:


                if (i==0 || spinnerAge.getSelectedItem().equals("Select") ||spinnerAge.getSelectedItem().equals("തെരഞ്ഞെടുക്കുക"))
                {
                    if (lang.equals("M"))
                    {
                        Toast.makeText(Layout1AgeGender.this, "എല്ലാ ഫീൽഡുകളും നൽകുക", Toast.LENGTH_SHORT).show();
                    }
                    else
                    Toast.makeText(Layout1AgeGender.this,"Enter all fields",Toast.LENGTH_LONG).show();

                }
               else
                {
                    Intent i = new Intent(Layout1AgeGender.this, Layout2CheckBox.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                }


                break;

          











        }
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Layout1AgeGender.this, ShareWith.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void fun(int a)
    {
        spinnerAge=(Spinner)findViewById(R.id.spinnerAge);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getApplicationContext(), a, R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinnerdrop);
        spinnerAge.setAdapter(adapter1);
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ageValuespin = spinnerAge.getItemAtPosition(position).toString();

                if (ageValuespin.equals("20–34")) {
                    ageValue = "22";

                } else if (ageValuespin.equals("35–39")) {
                    ageValue = "36";

                } else if (ageValuespin.equals("40–44")) {
                    ageValue = "41";

                } else if (ageValuespin.equals("45–49")) {
                    ageValue = "46";

                } else if (ageValuespin.equals("50–54")) {
                    ageValue = "51";

                } else if (ageValuespin.equals("55–59")) {
                    ageValue = "56";

                } else if (ageValuespin.equals("60–64")) {
                    ageValue = "61";

                } else if (ageValuespin.equals("65–69")) {
                    ageValue = "66";

                } else if (ageValuespin.equals("70–74")) {
                    ageValue = "71";

                } else if (ageValuespin.equals("75–79")) {
                    ageValue = "76";

                }

                spinnerAge.setSelection(position);

                Log.e("Age", ageValuespin);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }
}
